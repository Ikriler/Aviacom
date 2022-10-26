package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
