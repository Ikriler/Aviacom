package com.iproject.aviacom.controllers;

import com.iproject.aviacom.models.*;
import com.iproject.aviacom.repositories.*;
import com.iproject.aviacom.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/activity")
public class ClientActivityController {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    SaleRepository saleRepository;

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

    @GetMapping("/booking")
    public String bookingList(Model model) {
        Client client = clientRepository.findByEmail(AuthService.getName());
        List<Ticket> ticketList = ticketRepository.findByBookingClient(client);
        if(ticketList.size() == 0) {
            model.addAttribute("message", "У вас нет забронированных билетов");
        }
        model.addAttribute("tickets", ticketList);
        return "activity/booking/main";
    }

    @PostMapping("/booking/add")
    public String bookingAddPage(@ModelAttribute("ticket") Ticket ticket, Model model) throws ParseException {

        Client client = clientRepository.findByEmail(AuthService.getName());

        if(client.getBookingList().stream().filter(b -> b.getTicket().getVoyage().getId() == ticket.getVoyage().getId()).toList().size() > 0) {
            List<Ticket> ticketList = ticketRepository.findByBookingClient(client);
            model.addAttribute("tickets", ticketList);
            model.addAttribute("message", "У вас уже есть забронированный билет на этот рейс");
            return "activity/booking/main";
        }

        if(client.getSaleList().stream().filter(b -> b.getTicket().getVoyage().getId() == ticket.getVoyage().getId()).toList().size() > 0) {
            List<Ticket> ticketList = ticketRepository.findBySaleClient(client);
            model.addAttribute("tickets", ticketList);
            model.addAttribute("message", "У вас уже есть купленный билет на этот рейс");
            return "activity/sale/main";
        }

        Booking booking = new Booking();
        booking.setClient(client);
        booking.setTicket(ticket);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        String dateInc = ticket.getVoyage().getDateTimeInc().split("[ ]")[0];
        calendar.setTime(dateFormat.parse(dateInc));
        calendar.add(Calendar.DATE, -1);

        booking.setDateEnd(dateFormat.format(calendar.getTime()));

        bookingRepository.save(booking);
        return "redirect:/activity/booking";
    }

    @PostMapping("/booking/delete")
    public String bookingDelete(@ModelAttribute("ticket") Ticket ticket) {
        Booking booking = bookingRepository.findByTicket(ticket);
        if(booking != null) {
            bookingRepository.delete(booking);
        }
        return "redirect:/activity/booking";
    }

    @GetMapping("/sale/mainAdd")
    public String saleMainAddPage(@ModelAttribute("ticket") Ticket ticket, Model model) {
        model.addAttribute("ticket", ticket);
        return "activity/sale/mainAdd";
    }

    @GetMapping("/sale/clearAdd")
    public String saleClearAddPage(@ModelAttribute("ticket") Ticket ticket, Model model) {
        model.addAttribute("ticket", ticket);
        return "activity/sale/secondAdd";
    }

    @GetMapping("/sale")
    public String sale(Model model) {
        Client client = clientRepository.findByEmail(AuthService.getName());
        List<Ticket> ticketList = ticketRepository.findBySaleClient(client);
        if(ticketList.size() == 0) {
            model.addAttribute("message", "У вас нет купленных билетов");
        }
        model.addAttribute("tickets", ticketList);
        return "activity/sale/main";
    }

    @PostMapping("/sale/delete")
    public String saleDelete(@ModelAttribute("ticket") Ticket ticket, Model model) throws ParseException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date voyageStartDate = dateFormatter.parse(ticket.getVoyage().getDateTimeInc());
        Date currentDate = new Date();
        if(currentDate.after(voyageStartDate)) {
            Client client = clientRepository.findByEmail(AuthService.getName());
            List<Ticket> ticketList = ticketRepository.findBySaleClient(client);
            if(ticketList.size() == 0) {
                model.addAttribute("message", "Рейс уже прошел, невозможно вернуть билет");
            }
            model.addAttribute("message", "Рейс уже прошел, невозможно вернуть билет");
            model.addAttribute("tickets", ticketList);
            return "activity/sale/main";
        }
        Sale sale = saleRepository.findByTicket(ticket);
        if(sale != null) {
            saleRepository.delete(sale);
        }
        return "redirect:/activity/sale";
    }

    @PostMapping("/sale/add")
    public String saleAdd(@ModelAttribute("ticket") Ticket ticket, Model model) {
        Client client = clientRepository.findByEmail(AuthService.getName());
        Sale sale = new Sale();

        Ticket ticketDB = ticketRepository.findById(ticket.getId()).get();

        if(client.getSaleList().stream().filter(b -> b.getTicket().getVoyage().getId() == ticketDB.getVoyage().getId()).toList().size() > 0) {
            List<Ticket> ticketList = ticketRepository.findBySaleClient(client);
            model.addAttribute("tickets", ticketList);
            model.addAttribute("message", "У вас уже есть купленный билет на этот рейс");
            return "activity/sale/main";
        }

        if(client.getBookingList().stream().filter(b -> b.getTicket().getVoyage().getId() == ticketDB.getVoyage().getId()).toList().size() > 0) {
            List<Ticket> ticketList = ticketRepository.findByBookingClient(client);
            model.addAttribute("tickets", ticketList);
            model.addAttribute("message", "У вас уже есть забронированный билет на этот рейс");
            return "activity/booking/main";
        }

        Booking booking = bookingRepository.findByTicket(ticket);

        if(booking != null) {
            bookingRepository.delete(booking);
        }

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        sale.setTicket(ticket);
        sale.setClient(client);
        sale.setSaleDate(date);

        saleRepository.save(sale);

        return "redirect:/activity/sale";
    }

    @PostMapping("/sale/clearAdd")
    public String saleClearAdd(@ModelAttribute("ticket") Ticket ticket, Model model) {
        Client client = clientRepository.findByEmail(AuthService.getName());
        Sale sale = new Sale();

        Booking booking = bookingRepository.findByTicket(ticket);

        if(booking != null) {
            bookingRepository.delete(booking);
        }

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        sale.setTicket(ticket);
        sale.setClient(client);
        sale.setSaleDate(date);

        saleRepository.save(sale);

        return "redirect:/activity/sale";
    }


}
