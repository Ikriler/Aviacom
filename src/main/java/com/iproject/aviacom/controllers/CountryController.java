package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Country;
import com.iproject.aviacom.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("country")
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping
    public String countryList(@ModelAttribute("country") Country country, @RequestParam(required = false) String search, Model model) {
        if (search != null && search != "") {
            model.addAttribute("countries", countryRepository.findByNameContains(search));
        } else {
            model.addAttribute("countries", countryRepository.findAll());
        }
        return "country/main";
    }

    @PostMapping("/add")
    public String countryAdd(@ModelAttribute("country") @Valid Country country, BindingResult bindingResult, Model model) {
        Country dbCountry = countryRepository.findByName(country.getName());
        model.addAttribute("countries", countryRepository.findAll());
        if (dbCountry != null) {
            model.addAttribute("message", "Такая страна уже сущесвует");
            return "country/main";
        }
        if (bindingResult.hasErrors()) {
            return "country/main";
        }
        countryRepository.save(country);
        return "redirect:/country";
    }

    @PostMapping("/delete")
    public String countryDelete(@RequestParam long country_id) {
        Country country = countryRepository.findById(country_id).get();
        if (country != null) {
            countryRepository.delete(country);
        }
        return "redirect:/country";
    }

    @GetMapping("/edit")
    public String countryEditPage(@RequestParam long country_id, Model model) {
        Country country = countryRepository.findById(country_id).get();
        if (country == null) {
            return "redirect:/country";
        }
        model.addAttribute("country", country);
        return "country/edit";
    }

    @PostMapping("/edit")
    public String countryEdit(@ModelAttribute("country") @Valid Country country, BindingResult bindingResult, Model model) {
        Country dbCountry = countryRepository.findByName(country.getName());
        if (dbCountry != null && dbCountry.getId() != country.getId()) {
            model.addAttribute("message", "Такая страна уже сущесвует");
            return "country/edit";
        }
        if (bindingResult.hasErrors()) {
            return "country/edit";
        }
        countryRepository.save(country);
        return "redirect:/country";
    }

}
