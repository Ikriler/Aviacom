package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Booking;
import com.iproject.aviacom.models.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    Booking findByTicket(Ticket ticket);
}
