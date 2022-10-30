package com.iproject.aviacom.services;

import com.iproject.aviacom.models.LayoutType;
import com.iproject.aviacom.models.SeatClass;
import com.iproject.aviacom.models.Ticket;
import com.iproject.aviacom.models.Voyage;
import com.iproject.aviacom.repositories.SeatClassRepository;
import com.iproject.aviacom.repositories.TicketRepository;
import com.iproject.aviacom.repositories.VoyageRepository;

import java.util.List;

public class TicketService {

    private static String seatsLine = "";

    private static void initSeatLine(LayoutType layoutType) {
        switch (layoutType.getName()) {
            case "Малая":
                seatsLine = "AB";
                break;
            case "Средняя":
                seatsLine = "ABCD";
                break;
            default:
                seatsLine = "ABCDEF";

        }
    };

    public static void generateTickets(TicketRepository ticketRepository, SeatClassRepository seatClassRepository, Voyage voyage, Double price) {
        initSeatLine(voyage.getAirplane().getLayoutType());

        int firstClass = voyage.getAirplane().getFirstClassClientsCount();
        int businessClass = voyage.getAirplane().getBusinessClassClientsCount();
        int economicClass =  voyage.getAirplane().getEconomicClassClientsCount();

        int amount = firstClass + businessClass + economicClass;

        int amountCycle = amount / seatsLine.length();

        SeatClass firstSeatClass = seatClassRepository.findByName("Первый");
        SeatClass businessSeatClass = seatClassRepository.findByName("Бизнес");
        SeatClass economicSeatClass = seatClassRepository.findByName("Эконом");

        for(int seatNumber = 1; seatNumber <= amountCycle; seatNumber++) {
            for(int seatChar = 0; seatChar < seatsLine.length(); seatChar++) {
                Ticket ticket = new Ticket();
                if(firstClass != 0) {
                    firstClass--;
                    ticket.setPrice(price + price * firstSeatClass.getPrice() / 100);
                    ticket.setSeatClass(firstSeatClass);
                }
                else if (businessClass != 0) {
                    businessClass--;
                    ticket.setPrice(price + price * businessSeatClass.getPrice() / 100);
                    ticket.setSeatClass(businessSeatClass);
                }
                else if(economicClass != 0){
                    economicClass--;
                    ticket.setPrice(price + price * economicSeatClass.getPrice() / 100);
                    ticket.setSeatClass(economicSeatClass);
                }
                ticket.setSeat(String.valueOf(seatNumber) + seatsLine.charAt(seatChar));
                ticket.setVoyage(voyage);
                ticketRepository.save(ticket);
            }
        }
    }

    public static void changePrice(Double price, List<Ticket> tickets, TicketRepository ticketRepository) {
        for(Ticket ticket: tickets) {
            ticket.setPrice(price + price * ticket.getSeatClass().getPrice() / 100);
            ticketRepository.save(ticket);
        }
    }
}
