package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.City;
import com.iproject.aviacom.models.Voyage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface VoyageRepository extends CrudRepository<Voyage, Long> {


    List<Voyage> findVoyageByCityIncAndCityOutAndDateTimeInc(City cityInc, City cityOut, Date dateTimeInc);

    List<Voyage> findVoyageByCityIncAndCityOutAndDateTimeIncAndDateTimeOut(City cityInc, City cityOut, Date dateTimeInc, Date dateTimeOut);
}
