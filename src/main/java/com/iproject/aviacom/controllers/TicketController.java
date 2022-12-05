package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Voyage;
import com.iproject.aviacom.repositories.*;
import com.iproject.aviacom.services.TicketService;
import com.iproject.aviacom.services.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    SeatClassRepository seatClassRepository;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    BookingRepository bookingRepository;

    @GetMapping
    public String ticketList(@RequestParam(required = false) Voyage voyage, Model model, HttpSession session) {
        model.addAttribute("voyage", voyage);
        if(voyage != null) {
            model.addAttribute("tickets", ticketRepository.findByVoyage(voyage));
        }
        else {
            model.addAttribute("tickets", ticketRepository.findAll());
        }

        model.addAttribute("voyages", voyageRepository.findAll());
        if(session.getAttribute("message") != null) {
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }
        return "ticket/main";
    }

    @GetMapping("/add")
    public String ticketAddPage(Model model, HttpSession session) {
        List<Voyage> voyages = voyageRepository.findByTicketsIsEmpty();
        if(voyages == null || voyages.stream().count() == 0) {
            session.setAttribute("message", "Нет доступных рейсов");
            return "redirect:/ticket";
        }
        model.addAttribute("voyages", voyages);
        return "ticket/add";
    }

    @PostMapping("/add")
    public String ticketAdd(@ModelAttribute("voyage") Voyage voyage, String price, Model model) {
        List<Voyage> voyages = voyageRepository.findByTicketsIsEmpty();
        model.addAttribute("voyages", voyages);
        if(price == "" || price == "0") {
            model.addAttribute("message", "Цена не должна быть пустой");
            return "ticket/add";
        }
        Double priceDouble = Double.parseDouble(price);
        if(!ValidatorService.notLessZero(model, priceDouble, "message")) {
            return "ticket/add";
        }
        TicketService.generateTickets(ticketRepository, seatClassRepository, voyage, priceDouble);
        return "redirect:/ticket";
    }

    @PostMapping("/delete")
    public String ticketDelete(@ModelAttribute("voyage") Voyage voyage) {
        if(voyage != null && voyage.getTickets() != null) {
            ticketRepository.deleteAll(voyage.getTickets());
        }
        return "redirect:/ticket";
    }

    @GetMapping("/edit")
    public String ticketEditPage(@ModelAttribute("voyage") Voyage voyage, Model model) {
        if(voyage != null && voyage.getTickets() != null) {
            model.addAttribute("voyage", voyage);
            return "ticket/edit";
        }
        return "redirect:/ticket";
    }

    @PostMapping("/edit")
    public String ticketEdit(@ModelAttribute("voyage") Voyage voyage, @RequestParam String price, Model model) {
        if(price == "" || price == "0") {
            return "redirect:/ticket";
        }

        else {
            Double priceDouble = Double.parseDouble(price);
            if(!ValidatorService.notLessZero(model, priceDouble, "message")) {
                return "ticket/edit";
            }
            TicketService.changePrice(priceDouble, voyage.getTickets(), ticketRepository);
        }
        return "redirect:/ticket";
    }

    @GetMapping("/chart")
    public String showChart(Model model) {
        model.addAttribute("sales", ((List) saleRepository.findAll()).stream().count());
        model.addAttribute("bookings", ((List) bookingRepository.findAll()).stream().count());
        return "ticket/chart";
    }

}
