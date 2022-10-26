package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
