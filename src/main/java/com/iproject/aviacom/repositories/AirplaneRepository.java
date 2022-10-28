package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Airplane;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AirplaneRepository extends CrudRepository<Airplane, Long> {

    List<Airplane> findByModelContains(String model);

    Airplane findByModel(String model);

}
