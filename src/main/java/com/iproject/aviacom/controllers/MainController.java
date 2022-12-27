package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.City;
import com.iproject.aviacom.models.Ticket;
import com.iproject.aviacom.models.Voyage;
import com.iproject.aviacom.repositories.AirplaneRepository;
import com.iproject.aviacom.repositories.CityRepository;
import com.iproject.aviacom.repositories.TicketRepository;
import com.iproject.aviacom.repositories.VoyageRepository;
import com.iproject.aviacom.services.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.iproject.aviacom.enums.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("/")
    public String mainPage(@RequestParam(required = false) City cityInc,
                           @RequestParam(required = false) City cityOut,
                           @RequestParam(required = false) String dateInc,
                           @RequestParam(required = false) String dateOut,
                           Model model) {

        model.addAttribute("cityInc", cityInc);
        model.addAttribute("cityOut", cityOut);
        model.addAttribute("dateInc", dateInc);
        model.addAttribute("dateOut", dateOut);

        List<Voyage> voyageList = null;

        if(cityInc != null && cityOut != null) {
            voyageList = voyageRepository.findByCityIncAndCityOut(cityInc, cityOut);
        }
        else {
            voyageList = (List<Voyage>) voyageRepository.findAll();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(cityInc != null && cityOut != null && dateInc != null && dateInc != "") {
            if(dateOut != null & dateOut != "") {
                voyageList = voyageRepository.findByCityIncAndCityOutAndDateTimeIncContainsAndDateTimeOutContains(cityInc, cityOut, dateInc, dateOut);
            }
            else {
                voyageList = voyageRepository.findByCityIncAndCityOutAndDateTimeIncContains(cityInc, cityOut, dateInc);
            }
        }
        Collections.reverse(voyageList);
        if(voyageList.size() == 0) {
            model.addAttribute("message", "Рейсы не найдены");
        }
        model.addAttribute("voyages", voyageList);
        model.addAttribute("cities", cityRepository.findAll());
        return "main";
    }

    @GetMapping("/success")
    public void loginRedirectPage(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String role = authentication.getAuthorities().toString().replaceAll("[\\[\\]]", "");
        CurrentUserService currentUser = new CurrentUserService(role);
        if(currentUser.isAdmin().isPersonnel().CheckContains()) {
            response.sendRedirect(request.getContextPath() + "/employee");
        }
        else if(currentUser.isBooking().isCashier().CheckContains()) {
            response.sendRedirect(request.getContextPath() + "/client");
        }
        else if(currentUser.isAirdrome().CheckContains()) {
            response.sendRedirect(request.getContextPath() + "/voyage");
        }
        else {
            response.sendRedirect(request.getContextPath() + "/");
        }

    }

    @GetMapping("/main/tickets")
    public String availableTickets(@ModelAttribute("voyage") Voyage voyage, Model model) throws ParseException {
        Voyage dbVoyage = voyageRepository.findById(voyage.getId()).get();
        if(dbVoyage == null) {
            return "redirect:/";
        }
        List<Ticket> ticketList = ticketRepository.findByBookingIsNullAndSaleIsNullAndVoyage(dbVoyage);
        if(ticketList == null || ticketList.size() == 0) {
            model.addAttribute("voyages", voyageRepository.findAll());
            model.addAttribute("cities", cityRepository.findAll());
            model.addAttribute("message","Нет доступных билетов");
            return "main";
        }
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date voyageStartDate = dateFormatter.parse(voyage.getDateTimeInc());
        Date currentDate = new Date();
        if(currentDate.after(voyageStartDate)) {
            model.addAttribute("voyages", voyageRepository.findAll());
            model.addAttribute("cities", cityRepository.findAll());
            model.addAttribute("message","Посадка на рейс уже закончилась");
            return "main";
        }
        model.addAttribute("tickets", ticketList);
        return "mainTickets";
    }


}
