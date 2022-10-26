package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Airplane;
import org.springframework.data.repository.CrudRepository;

public interface AirplaneRepository extends CrudRepository<Airplane, Long> {
}
