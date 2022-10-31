package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.models.Employee;
import com.iproject.aviacom.repositories.ClientRepository;
import com.iproject.aviacom.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public String registrationPage(@ModelAttribute("client") Client client) {
        return "registration";
    }

    @PostMapping("/client")
    public String registrationClient(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, Model model) {
        Client dbClient = clientRepository.findByEmail(client.getEmail());
        Employee dbEmployee = employeeRepository.findByLogin(client.getEmail());
        if(dbClient != null || dbEmployee != null) {
            model.addAttribute("message","Такая почта уже существует");
            return "registration/add";
        }
        if(bindingResult.hasErrors()) {
            return "registration/add";
        }
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        clientRepository.save(client);
        return "redirect:/login";
    }

}
