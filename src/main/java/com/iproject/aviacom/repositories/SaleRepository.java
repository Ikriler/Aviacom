package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Booking;
import com.iproject.aviacom.models.Sale;
import com.iproject.aviacom.models.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface SaleRepository extends CrudRepository<Sale, Long> {
    Sale findByTicket(Ticket ticket);

}
