package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Ticket;
import com.iproject.aviacom.models.Voyage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findByVoyage(Voyage voyage);

}
