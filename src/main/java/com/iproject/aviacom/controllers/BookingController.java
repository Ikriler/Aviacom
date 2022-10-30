package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.Booking;
import com.iproject.aviacom.models.Employee;
import com.iproject.aviacom.models.Ticket;
import com.iproject.aviacom.models.Voyage;
import com.iproject.aviacom.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public String bookingList(@RequestParam(required = false) String listType,
                              @ModelAttribute("voyage") Voyage voyage,
                              Model model) {
        if(listType != null) {
            switch (listType) {
                case "Доступные":
                    model.addAttribute("tickets", ticketRepository.findByBookingIsNullAndSaleIsNullAndVoyage(voyage));
                    break;
                default:
                    model.addAttribute("bookingTable", true);
                    model.addAttribute("tickets", ticketRepository.findByBookingIsNotNullAndSaleIsNullAndVoyage(voyage));
            }
        }
        else {
            model.addAttribute("tickets", ticketRepository.findByBookingIsNullAndSaleIsNull());
        }
        model.addAttribute("voyages", voyageRepository.findAll());
        return "booking/main";
    }

    @GetMapping("/add")
    public String bookingAddPage(@ModelAttribute("booking") Booking booking, @ModelAttribute("ticket") Ticket ticket, Model model) {
        model.addAttribute("ticket", ticket);
        model.addAttribute("clients", clientRepository.findAll());
        return "booking/add";
    }

    @PostMapping("/add")
    public String bookingAdd(@ModelAttribute("booking") @Valid Booking booking,
                             BindingResult bindingResult,
                             Model model) {
        model.addAttribute("ticket", booking.getTicket());
        model.addAttribute("clients", clientRepository.findAll());

        String employeeLogin = SecurityContextHolder.getContext().getAuthentication().getName();

        Employee employee = employeeRepository.findByLogin(employeeLogin);

        if(employee == null) {
            return "redirect:/login";
        }

        if(bindingResult.hasErrors()) {
            return "booking/add";
        }

        booking.setEmployee(employee);

        bookingRepository.save(booking);

        return "redirect:/booking";
    }

    @PostMapping("/delete")
    public String bookingDelete(@ModelAttribute("ticket") Ticket ticket) {
        if(ticket.getBooking() != null) {
            bookingRepository.delete(ticket.getBooking());
        }
        return "redirect:/booking";
    }

}
