package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findByName(String name);

    List<Country> findByNameContains(String name);
}
