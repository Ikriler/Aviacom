package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.City;
import com.iproject.aviacom.models.Country;
import com.iproject.aviacom.repositories.CityRepository;
import com.iproject.aviacom.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @GetMapping
    public String cityList(@RequestParam(required = false) String search, Model model) {
        if(search != null && search != "") {
            model.addAttribute("cities", cityRepository.findByNameContains(search));
        }
        else {
            model.addAttribute("cities", cityRepository.findAll());
        }
        return "city/main";
    }

    @GetMapping("/add")
    public String cityAddPage(@ModelAttribute("city") City city, Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        return "city/add";
    }

    @PostMapping("/add")
    public String cityAdd(@ModelAttribute("city") @Valid City city, BindingResult bindingResult, Model model) {
        City dbCity = cityRepository.findByName(city.getName());
        model.addAttribute("countries", countryRepository.findAll());
        if(dbCity != null) {
            model.addAttribute("message", "Город с таким названием уже существует");
            return "city/add";
        }
        if(bindingResult.hasErrors()) {
            return "city/add";
        }
        cityRepository.save(city);
        return "redirect:/city";
    }

    @PostMapping("/delete")
    public String cityDelete(@RequestParam long city_id) {
        City city = cityRepository.findById(city_id).get();
        if (city != null) {
            cityRepository.delete(city);
        }
        return "redirect:/city";
    }

    @GetMapping("/edit")
    public String cityEditPage(@RequestParam long city_id, Model model) {
        City city = cityRepository.findById(city_id).get();
        model.addAttribute("countries", countryRepository.findAll());
        if (city == null) {
            return "redirect:/city";
        }
        model.addAttribute("city", city);
        return "city/edit";
    }

    @PostMapping("/edit")
    public String cityEdit(@ModelAttribute("city") @Valid City city, BindingResult bindingResult, Model model) {
        City dbCity = cityRepository.findByName(city.getName());
        model.addAttribute("countries", countryRepository.findAll());
        if(dbCity != null && dbCity.getId() != city.getId()) {
            model.addAttribute("message", "Город с таким названием уже существует");
            return "city/edit";
        }
        if(bindingResult.hasErrors()) {
            return "city/edit";
        }
        cityRepository.save(city);
        return "redirect:/city";
    }

}
