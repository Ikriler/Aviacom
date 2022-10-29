package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByEmail(String email);

    List<Client> findByNameContainsOrSurnameContainsOrPatronymicContains(String name, String surname, String patronymic);
}
