package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.models.Employee;
import com.iproject.aviacom.repositories.ClientRepository;
import com.iproject.aviacom.repositories.EmployeeRepository;
import com.iproject.aviacom.repositories.PostRepository;
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
    public String employee(@RequestParam(required = false) String searchName,
            @RequestParam(required = false) String searchSurname,
            @RequestParam(required = false) String searchPatronymic, Model model) {
        if(searchName != null || searchPatronymic != null || searchSurname != null) {
            searchName = searchName == null ? "" : searchName;
            searchPatronymic = searchPatronymic == null ? "" : searchPatronymic;
            searchSurname = searchSurname == null ? "" : searchSurname;
            model.addAttribute("employees", employeeRepository.findByNameContainsOrSurnameContainsOrPatronymicContains(searchName, searchSurname, searchPatronymic));
        }
        else {
            model.addAttribute("employees", employeeRepository.findAll());
        }
        return "employee/main";
    }

    @GetMapping("/add")
    public String employeeAddPage(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "employee/add";
    }

    @PostMapping("/add")
    public String employeeAdd(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model) {
        model.addAttribute("posts", postRepository.findAll());
        Client dbClient = clientRepository.findByEmail(employee.getLogin());
        Employee dbEmployee = employeeRepository.findByLogin(employee.getLogin());
        if(dbClient != null || dbEmployee != null) {
            model.addAttribute("message","Такой логин уже существует");
            return "employee/add";
        }
        if(bindingResult.hasErrors()) {
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
        Employee dbEmployee = employeeRepository.findById(employee.getId()).get();
        if(bindingResult.hasErrors()) {
            return "employee/edit";
        }
        if(new_password != null && new_password != "") {
            dbEmployee.setPassword(new BCryptPasswordEncoder().encode(new_password));
        }
        dbEmployee.setName(employee.getName());
        dbEmployee.setSurname(employee.getSurname());
        dbEmployee.setPatronymic(employee.getPatronymic());
        dbEmployee.setPhone(employee.getPhone());
        dbEmployee.setPost(employee.getPost());
        employeeRepository.save(dbEmployee);
        return "redirect:/employee";
    }

}
