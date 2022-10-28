package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Airplane;
import com.iproject.aviacom.models.LayoutType;
import com.iproject.aviacom.repositories.AirplaneRepository;
import com.iproject.aviacom.repositories.LayoutTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/airplane")
public class AirplaneController {

    @Autowired
    AirplaneRepository airplaneRepository;
    @Autowired
    LayoutTypeRepository layoutTypeRepository;

    @GetMapping
    public String airplaneList(@RequestParam(required = false) String search, Model model) {
        if (search != null && search != "") {
            model.addAttribute("airplanes", airplaneRepository.findByModelContains(search));
        } else {
            model.addAttribute("airplanes", airplaneRepository.findAll());
        }
        return "airplane/main";
    }

    @GetMapping("/add")
    public String airplaneAddPage(@ModelAttribute("airplane") Airplane airplane, Model model) {
        model.addAttribute("layoutTypes", layoutTypeRepository.findAll());
        return "airplane/add";
    }

    @PostMapping("/add")
    public String airplaneAdd(@ModelAttribute("airplane") @Valid Airplane airplane, BindingResult bindingResult, Model model) {
        Airplane dbAirplane = airplaneRepository.findByModel(airplane.getModel());
        model.addAttribute("layoutTypes", layoutTypeRepository.findAll());
        if(dbAirplane != null) {
            model.addAttribute("message", "Самолёт с такой моделью уже существует");
            return "airplane/add";
        }
        if(bindingResult.hasErrors()) {
            return "airplane/add";
        }
        airplaneRepository.save(airplane);
        return "redirect:/airplane";
    }

    @PostMapping("/delete")
    public String airplaneDelete(@RequestParam long airplane_id) {
        Airplane airplane = airplaneRepository.findById(airplane_id).get();
        if(airplane != null) {
            airplaneRepository.delete(airplane);
        }
        return "redirect:/airplane";
    }

    @GetMapping("/edit")
    public String airplaneEditPage(@RequestParam long airplane_id, Model model) {
        Airplane airplane = airplaneRepository.findById(airplane_id).get();
        model.addAttribute("layoutTypes", layoutTypeRepository.findAll());
        if(airplane == null) {
            return "redirect:/airplane";
        }
        model.addAttribute("airplane", airplane);
        return "airplane/edit";
    }

    @PostMapping("/edit")
    public String airplaneEdit(@ModelAttribute("airplane") @Valid Airplane airplane, BindingResult bindingResult, Model model) {
        Airplane dbAirplane = airplaneRepository.findByModel(airplane.getModel());
        model.addAttribute("layoutTypes", layoutTypeRepository.findAll());
        if(dbAirplane != null && dbAirplane.getId() != airplane.getId()) {
            model.addAttribute("message", "Самолёт с такой моделью уже существует");
            return "airplane/edit";
        }
        if(bindingResult.hasErrors()) {
            return "airplane/edit";
        }
        airplaneRepository.save(airplane);
        return "redirect:/airplane";
    }
}
