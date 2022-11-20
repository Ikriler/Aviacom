package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Airplane;
import com.iproject.aviacom.models.Airport;
import com.iproject.aviacom.models.City;
import com.iproject.aviacom.repositories.AirportRepository;
import com.iproject.aviacom.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    AirportRepository airportRepository;
    @Autowired
    CityRepository cityRepository;

    @GetMapping
    public String airportList(@RequestParam(required = false) City city, Model model) {
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("city", city);
        if (city != null) {
            model.addAttribute("airports", airportRepository.findByCity(city));
        } else {
            model.addAttribute("airports", airportRepository.findAll());
        }
        return "airport/main";
    }

    @GetMapping("/add")
    public String airportAddPage(@ModelAttribute("airport") Airport airport, Model model) {
        model.addAttribute("cities", cityRepository.findAll());
        return "airport/add";
    }

    @PostMapping("/add")
    public String airportAdd(@ModelAttribute("airport") @Valid Airport airport, BindingResult bindingResult, Model model) {
        Airport dbAirport = airportRepository.findByCityAndName(airport.getCity(), airport.getName());
        model.addAttribute("cities", cityRepository.findAll());
        if(dbAirport != null) {
            model.addAttribute("message", "Аэропорт с таким именем и городом уже существует");
            return "airport/add";
        }
        if(bindingResult.hasErrors()) {
            return "airport/add";
        }
        airportRepository.save(airport);
        return "redirect:/airport";
    }

    @PostMapping("/delete")
    public String airportDelete(@RequestParam long airport_id) {
        Airport airport = airportRepository.findById(airport_id).get();
        if(airport != null) {
            airportRepository.delete(airport);
        }
        return "redirect:/airport";
    }

    @GetMapping("/edit")
    public String airportEditPage(@RequestParam long airport_id, Model model) {
        Airport airport = airportRepository.findById(airport_id).get();
        model.addAttribute("cities", cityRepository.findAll());
        if(airport == null) {
            return "redirect:/airport";
        }
        model.addAttribute("airport", airport);
        return "airport/edit";
    }

    @PostMapping("/edit")
    public String airportEdit(@ModelAttribute("airport") @Valid Airport airport, BindingResult bindingResult, Model model) {
        Airport dbAirport = airportRepository.findByCityAndName(airport.getCity(), airport.getName());
        model.addAttribute("cities", cityRepository.findAll());
        if(dbAirport != null && dbAirport.getId() != airport.getId()) {
            model.addAttribute("message", "Аэропорт с таким именем и городом уже существует");
            return "airport/edit";
        }
        if(bindingResult.hasErrors()) {
            return "airport/edit";
        }
        airportRepository.save(airport);
        return "redirect:/airport";
    }

}
