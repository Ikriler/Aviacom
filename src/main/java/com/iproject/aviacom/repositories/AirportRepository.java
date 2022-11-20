package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Airport;
import com.iproject.aviacom.models.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AirportRepository extends CrudRepository<Airport, Long> {

    List<Airport> findByCity(City city);

    Airport findByCityAndName(City city, String name);

}
