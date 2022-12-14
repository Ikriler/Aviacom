package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.models.Employee;
import com.iproject.aviacom.repositories.ClientRepository;
import com.iproject.aviacom.repositories.EmployeeRepository;
import com.iproject.aviacom.services.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public String clientList(@RequestParam(required = false) String searchName,
                           @RequestParam(required = false) String searchSurname,
                           @RequestParam(required = false) String searchPatronymic, Model model) {
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchSurname", searchSurname);
        model.addAttribute("searchPatronymic", searchPatronymic);

        if(searchName != "" || searchPatronymic != "" || searchSurname != "") {
            searchName = searchName == "" ? "_" : searchName;
            searchPatronymic = searchPatronymic == "" ? "_" : searchPatronymic;
            searchSurname =  searchSurname == "" ? "_" : searchSurname;
            model.addAttribute("clients", clientRepository.findByNameContainsOrSurnameContainsOrPatronymicContains(searchName, searchSurname, searchPatronymic));
        }
        else {
            model.addAttribute("clients", clientRepository.findAll());
        }
        return "client/main";
    }

    @GetMapping("/add")
    public String clientAddPage(@ModelAttribute("client") Client client, BindingResult bindingResult, Model model) {
        return "client/add";
    }

    @PostMapping("/add")
    public String clientAdd(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, Model model) {
        Client dbClient = clientRepository.findByEmail(client.getEmail());
        Employee dbEmployee = employeeRepository.findByLogin(client.getEmail());
        if(dbClient != null || dbEmployee != null) {
            model.addAttribute("message","?????????? ?????????? ?????? ????????????????????");
            return "client/add";
        }
        if(bindingResult.hasErrors() ||
                !ValidatorService.checkNotExistsPassport(model, client.getPassportSeries(), client.getPassportNumber(), clientRepository, "passportError", client) ||
                !ValidatorService.checkNotExistsPhoneClient(model, client.getPhone(), clientRepository, "phoneError", client) )
        {
            return "client/add";
        }
        if(client.getPassword().length() < 5) {
            model.addAttribute("passwordError","???????????? ???????????? ?????????? ???? ?????????? 5 ????????????????");
            return "client/add";
        }
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        clientRepository.save(client);
        return "redirect:/client";
    }

    @PostMapping("/delete")
    public String clientDelete(@RequestParam long client_id) {
        Client client = clientRepository.findById(client_id).get();
        if(client != null) {
            clientRepository.delete(client);
        }
        return "redirect:/client";
    }

    @GetMapping("/edit")
    public String clientEditPage(@RequestParam long client_id, Model model) {
        Client client = clientRepository.findById(client_id).get();
        if(client == null) {
            return "redirect:/client";
        }
        model.addAttribute("client", client);
        return "client/edit";
    }

    @PostMapping("/edit")
    public String clientEdit(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, @RequestParam(required = false) String new_password, Model model) {
        Employee dbEmployee = employeeRepository.findByLogin(client.getEmail());
        Client dbClient = clientRepository.findByEmail(client.getEmail());
        if((dbClient != null && dbClient.getId() != client.getId()) || dbEmployee != null) {
            model.addAttribute("message","?????????? ?????????? ?????? ????????????????????");
            return "client/edit";
        }
        dbClient = clientRepository.findById(client.getId()).get();
        if(bindingResult.hasErrors() ||
                !ValidatorService.checkNotExistsPassport(model, client.getPassportSeries(), client.getPassportNumber(), clientRepository, "passportError", client) ||
                !ValidatorService.checkNotExistsPhoneClient(model, client.getPhone(), clientRepository, "phoneError", client) )
        {
            return "client/edit";
        }
        if(new_password != null && new_password != "") {
            if(new_password.length() < 5) {
                model.addAttribute("passwordError","???????????? ???????????? ?????????? ???? ?????????? 5 ????????????????");
                return "client/edit";
            }
            dbClient.setPassword(new BCryptPasswordEncoder().encode(new_password));
        }
        dbClient.setName(client.getName());
        dbClient.setSurname(client.getSurname());
        dbClient.setPatronymic(client.getPatronymic());
        dbClient.setPhone(client.getPhone());
        dbClient.setEmail(client.getEmail());
        dbClient.setPassportNumber(client.getPassportNumber());
        dbClient.setPassportSeries(client.getPassportSeries());
        clientRepository.save(dbClient);
        return "redirect:/client";
    }

}
