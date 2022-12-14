package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.models.Employee;
import com.iproject.aviacom.repositories.ClientRepository;
import com.iproject.aviacom.repositories.EmployeeRepository;
import com.iproject.aviacom.repositories.PostRepository;
import com.iproject.aviacom.services.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public String employeeList(@RequestParam(required = false) String searchName,
            @RequestParam(required = false) String searchSurname,
            @RequestParam(required = false) String searchPatronymic, Model model) {
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchSurname", searchSurname);
        model.addAttribute("searchPatronymic", searchPatronymic);

        if(searchName != "" || searchPatronymic != "" || searchSurname != "") {
            searchName = searchName == "" ? "_" : searchName;
            searchPatronymic = searchPatronymic == "" ? "_" : searchPatronymic;
            searchSurname =  searchSurname == "" ? "_" : searchSurname;
            model.addAttribute("employees", employeeRepository.findByNameContainsOrSurnameContainsOrPatronymicContains(searchName, searchSurname, searchPatronymic));
        }
        else {
            model.addAttribute("employees", employeeRepository.findAll());
        }
        return "employee/main";
    }

    @GetMapping("/add")
    public String employeeAddPage(@ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "employee/add";
    }

    @PostMapping("/add")
    public String employeeAdd(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model) {
        model.addAttribute("posts", postRepository.findAll());
        Client dbClient = clientRepository.findByEmail(employee.getLogin());
        Employee dbEmployee = employeeRepository.findByLogin(employee.getLogin());
        if(dbClient != null || dbEmployee != null) {
            model.addAttribute("message","?????????? ?????????? ?????? ????????????????????");
            return "employee/add";
        }
        if(bindingResult.hasErrors() ||
                !ValidatorService.checkNotExistsPhoneEmployee(model, employee.getPhone(), employeeRepository, "phoneError", employee) )
        {
            return "employee/add";
        }
        if(employee.getPassword().length() < 5) {
            model.addAttribute("passwordError","???????????? ???????????? ?????????? ???? ?????????? 5 ????????????????");
            return "employee/add";
        }
        employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @PostMapping("/delete")
    public String employeeDelete(@RequestParam long employee_id) {
        Employee employee = employeeRepository.findById(employee_id).get();
        if(employee != null) {
            employeeRepository.delete(employee);
        }
        return "redirect:/employee";
    }

    @GetMapping("/edit")
    public String employeeEditPage(@RequestParam long employee_id, Model model) {
        model.addAttribute("posts", postRepository.findAll());
        Employee employee = employeeRepository.findById(employee_id).get();
        if(employee == null) {
            return "redirect:/employee";
        }
        model.addAttribute("employee", employee);
        return "employee/edit";
    }

    @PostMapping("/edit")
    public String employeeEdit(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, @RequestParam(required = false) String new_password, Model model) {
        model.addAttribute("posts", postRepository.findAll());
        Employee dbEmployee = employeeRepository.findByLogin(employee.getLogin());
        Client dbClient = clientRepository.findByEmail(employee.getLogin());
        if((dbEmployee != null && dbEmployee.getId() != employee.getId()) || dbClient != null) {
            model.addAttribute("message","?????????? ?????????? ?????? ????????????????????");
            return "employee/edit";
        }
        dbEmployee = employeeRepository.findById(employee.getId()).get();
        if(bindingResult.hasErrors() ||
                !ValidatorService.checkNotExistsPhoneEmployee(model, employee.getPhone(), employeeRepository, "phoneError", employee) )
        {
            return "employee/edit";
        }
        if(new_password != null && new_password != "") {
            if(new_password.length() < 5) {
                model.addAttribute("passwordError","???????????? ???????????? ?????????? ???? ?????????? 5 ????????????????");
                return "employee/edit";
            }
            dbEmployee.setPassword(new BCryptPasswordEncoder().encode(new_password));
        }
        dbEmployee.setName(employee.getName());
        dbEmployee.setSurname(employee.getSurname());
        dbEmployee.setPatronymic(employee.getPatronymic());
        dbEmployee.setPhone(employee.getPhone());
        dbEmployee.setPost(employee.getPost());
        dbEmployee.setLogin(employee.getLogin());
        employeeRepository.save(dbEmployee);
        return "redirect:/employee";
    }

}
