package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {

    City findByName(String name);

    List<City> findByNameContains(String name);
}
