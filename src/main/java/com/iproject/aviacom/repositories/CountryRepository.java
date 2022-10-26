package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
