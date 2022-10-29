package com.iproject.aviacom.controllers;

import com.iproject.aviacom.repositories.AirplaneRepository;
import com.iproject.aviacom.repositories.CityRepository;
import com.iproject.aviacom.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/voyage")
public class VoyageController {

    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    AirplaneRepository airplaneRepository;

    @GetMapping
    public String voyageList(Model model) {
        model.addAttribute("voyages", voyageRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        return "voyage/main";
    }
}
