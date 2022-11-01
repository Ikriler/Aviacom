package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.City;
import com.iproject.aviacom.models.Voyage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public interface VoyageRepository extends CrudRepository<Voyage, Long> {

    List<Voyage> findByCityIncAndCityOutAndDateTimeIncContains(City cityInc, City cityOut, String dateTimeInc);

    List<Voyage> findByCityIncAndCityOut(City cityInc, City cityOut);

    List<Voyage> findByCityIncAndCityOutAndDateTimeIncContainsAndDateTimeOutContains(City cityInc, City cityOut, String dateTimeInc, String dateTimeOut);

    List<Voyage> findByTicketsIsEmpty();

}
