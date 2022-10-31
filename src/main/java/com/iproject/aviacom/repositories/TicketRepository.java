package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Booking;
import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.models.Ticket;
import com.iproject.aviacom.models.Voyage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findByVoyage(Voyage voyage);

    List<Ticket> findByBookingIsNullAndSaleIsNullAndVoyage(Voyage voyage);

    List<Ticket> findByBookingIsNotNullAndSaleIsNullAndVoyage(Voyage voyage);

    List<Ticket> findByBookingIsNullAndSaleIsNotNullAndVoyage(Voyage voyage);

    List<Ticket> findByBookingIsNullAndSaleIsNull();

    List<Ticket> findByBookingClient(Client client);

    List<Ticket> findBySaleClient(Client client);

}
