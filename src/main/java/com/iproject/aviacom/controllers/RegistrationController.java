package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public String registrationPage(@ModelAttribute("client") Client client) {
        return "registration";
    }

    @PostMapping("/client")
    public String registrationClient(@ModelAttribute("client") Client client) {
        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
        clientRepository.save(client);
        return "redirect:/login";
    }

}
