package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Airport;
import com.iproject.aviacom.models.City;
import com.iproject.aviacom.models.Voyage;
import com.iproject.aviacom.repositories.AirplaneRepository;
import com.iproject.aviacom.repositories.AirportRepository;
import com.iproject.aviacom.repositories.CityRepository;
import com.iproject.aviacom.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/voyage")
public class VoyageController {

    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    AirplaneRepository airplaneRepository;
    @Autowired
    AirportRepository airportRepository;

    @GetMapping
    public String voyageList(@RequestParam(required = false) City cityInc,
                             @RequestParam(required = false) City cityOut,
                             @RequestParam(required = false) String dateInc,
                             @RequestParam(required = false) String dateOut,
                             Model model) throws ParseException {

        model.addAttribute("cityInc", cityInc);
        model.addAttribute("cityOut", cityOut);
        model.addAttribute("dateInc", dateInc);
        model.addAttribute("dateOut", dateOut);

        if(cityInc != null && cityOut != null) {
            model.addAttribute("voyages", voyageRepository.findByCityIncAndCityOut(cityInc, cityOut));
        }
        else {
            model.addAttribute("voyages", voyageRepository.findAll());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(cityInc != null && cityOut != null && dateInc != null && dateInc != "") {
            if(dateOut != null & dateOut != "") {
                model.addAttribute("voyages", voyageRepository.findByCityIncAndCityOutAndDateTimeIncContainsAndDateTimeOutContains(cityInc, cityOut, dateInc, dateOut));
                model.addAttribute("voyages", voyageRepository.findByCityIncAndCityOutAndDateTimeIncContains(cityInc, cityOut, dateInc));
            }
            else {
                model.addAttribute("voyages", voyageRepository.findByCityIncAndCityOutAndDateTimeIncContains(cityInc, cityOut, dateInc));
            }
        }

        model.addAttribute("cities", cityRepository.findAll());

        return "voyage/main";
    }

    @GetMapping("/add")
    public String voyageAddPage(@ModelAttribute("voyage") @Valid Voyage voyage, BindingResult bindingResult, Model model) {
        model.addAttribute("airplanes", airplaneRepository.findAll());
        model.addAttribute("airports", airportRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        return "voyage/add";
    }

    @PostMapping("/add")
    public String voyageAdd(@ModelAttribute("voyage") @Valid Voyage voyage,
                            BindingResult bindingResult,
                            @RequestParam String dateInc,
                            @RequestParam String timeInc,
                            @RequestParam String dateOut,
                            @RequestParam String timeOut,
                            Model model) {
        model.addAttribute("airplanes", airplaneRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("dateInc", dateInc);
        model.addAttribute("timeInc", timeInc);
        model.addAttribute("dateOut", dateOut);
        model.addAttribute("timeOut", timeOut);
        model.addAttribute("airports", airportRepository.findAll());


        String dateTimeIncMessage = "";
        String dateTimeOutMessage = "";
        String citiesError = "";

        if(dateInc == "" || timeInc == "") {
            dateTimeIncMessage = "???????? ???? ???????????? ???????? ??????????????";
            model.addAttribute("dateTimeIncMessage", dateTimeIncMessage);
        }

        if(dateOut == "" || timeOut == "") {
            dateTimeOutMessage = "???????? ???? ???????????? ???????? ??????????????";
            model.addAttribute("dateTimeOutMessage", dateTimeOutMessage);
        }

        if(voyage.getAirportInc().getCity() == voyage.getAirportOut().getCity()) {
            citiesError = "???????????? ???? ???????????? ??????????????????";
            model.addAttribute("citiesError", citiesError);
        }

        if(bindingResult.hasErrors() || dateTimeIncMessage != "" || dateTimeOutMessage != "" || citiesError != "") {
            return "voyage/add";
        }

        voyage.setCityInc(voyage.getAirportInc().getCity());
        voyage.setCityOut(voyage.getAirportOut().getCity());

        String dateTimeInc = dateInc + " " + timeInc;
        String dateTimeOut = dateOut + " " + timeOut;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime localDateTimeInc = LocalDateTime.parse(dateTimeInc, dateTimeFormatter);
        LocalDateTime localDateTimeOut = LocalDateTime.parse(dateTimeOut, dateTimeFormatter);

        if(localDateTimeInc.isAfter(localDateTimeOut)) {
            model.addAttribute("datesError", "????????/?????????? ?????????????????????? ???? ???????????? ???????? ?????????? ????????????????");
            return "voyage/add";
        }

        voyage.setDateTimeInc(dateTimeInc);
        voyage.setDateTimeOut(dateTimeOut);

        voyageRepository.save(voyage);
        return "redirect:/voyage";
    }

    @PostMapping("/delete")
    public String voyageDelete(@RequestParam long voyage_id) {
        Voyage voyage = voyageRepository.findById(voyage_id).get();
        if(voyage != null) {
            voyageRepository.delete(voyage);
        }
        return "redirect:/voyage";
    }

    @GetMapping("/edit")
    public String voyageEditPage(@RequestParam long voyage_id, Model model) {
        model.addAttribute("airports", airportRepository.findAll());
        Voyage voyage = voyageRepository.findById(voyage_id).get();
        if(voyage == null) {
            return "redirect:/voyage";
        }

        List<String> dateTimeInc = Arrays.stream(voyage.getDateTimeInc().split(" ")).toList();
        String dateInc = dateTimeInc.get(0);
        String timeInc = dateTimeInc.get(1);

        List<String> dateTimeOut = Arrays.stream(voyage.getDateTimeOut().split(" ")).toList();
        String dateOut = dateTimeOut.get(0);
        String timeOut = dateTimeOut.get(1);

        model.addAttribute("airplanes", airplaneRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());

        model.addAttribute("dateInc", dateInc);
        model.addAttribute("timeInc", timeInc);
        model.addAttribute("dateOut", dateOut);
        model.addAttribute("timeOut", timeOut);

        model.addAttribute("voyage", voyage);

        return "voyage/edit";
    }

    @PostMapping("edit")
    public String voyageEdit(@ModelAttribute("voyage") @Valid Voyage voyage,
                             BindingResult bindingResult,
                             @RequestParam String dateInc,
                             @RequestParam String timeInc,
                             @RequestParam String dateOut,
                             @RequestParam String timeOut,
                             Model model) {
        model.addAttribute("airplanes", airplaneRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("dateInc", dateInc);
        model.addAttribute("timeInc", timeInc);
        model.addAttribute("dateOut", dateOut);
        model.addAttribute("timeOut", timeOut);
        model.addAttribute("airports", airportRepository.findAll());

        String dateTimeIncMessage = "";
        String dateTimeOutMessage = "";
        String citiesError = "";

        if(dateInc == "" || timeInc == "") {
            dateTimeIncMessage = "???????? ???? ???????????? ???????? ??????????????";
            model.addAttribute("dateTimeIncMessage", dateTimeIncMessage);
        }

        if(dateOut == "" || timeOut == "") {
            dateTimeOutMessage = "???????? ???? ???????????? ???????? ??????????????";
            model.addAttribute("dateTimeOutMessage", dateTimeOutMessage);
        }

        if(voyage.getAirportInc().getCity() == voyage.getAirportOut().getCity()) {
            citiesError = "???????????? ???? ???????????? ??????????????????";
            model.addAttribute("citiesError", citiesError);
        }

        if(bindingResult.hasErrors() || dateTimeIncMessage != "" || dateTimeOutMessage != "" || citiesError != "") {
            return "voyage/edit";
        }

        voyage.setCityInc(voyage.getAirportInc().getCity());
        voyage.setCityOut(voyage.getAirportOut().getCity());

        String dateTimeInc = dateInc + " " + timeInc;
        String dateTimeOut = dateOut + " " + timeOut;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime localDateTimeInc = LocalDateTime.parse(dateTimeInc, dateTimeFormatter);
        LocalDateTime localDateTimeOut = LocalDateTime.parse(dateTimeOut, dateTimeFormatter);

        if(localDateTimeInc.isAfter(localDateTimeOut)) {
            model.addAttribute("datesError", "????????/?????????? ?????????????????????? ???? ???????????? ???????? ?????????? ????????????????");
            return "voyage/edit";
        }

        voyage.setDateTimeInc(dateTimeInc);
        voyage.setDateTimeOut(dateTimeOut);

        voyageRepository.save(voyage);

        return "redirect:/voyage";
    }


}
