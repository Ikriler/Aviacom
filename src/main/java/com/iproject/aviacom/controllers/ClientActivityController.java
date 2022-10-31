package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.models.Employee;
import com.iproject.aviacom.repositories.ClientRepository;
import com.iproject.aviacom.repositories.EmployeeRepository;
import com.iproject.aviacom.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/activity")
public class ClientActivityController {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/profile")
    public String profile(Model model) {
        Client client = clientRepository.findByEmail(AuthService.getName());
        model.addAttribute("client", client);
        return "activity/profile";
    }

    @PostMapping("/profile/edit")
    public String profileEdit(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, @RequestParam(required = false) String new_password, Model model) {
        Employee dbEmployee = employeeRepository.findByLogin(client.getEmail());
        Client dbClient = clientRepository.findByEmail(client.getEmail());
        if((dbClient != null && dbClient.getId() != client.getId()) || dbEmployee != null) {
            model.addAttribute("message","Такая почта уже существует");
            return "activity/profile";
        }
        dbClient = clientRepository.findById(client.getId()).get();
        if(bindingResult.hasErrors()) {
            return "activity/profile";
        }
        if(new_password != null && new_password != "") {
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
        return "redirect:/activity/profile";
    }
}
