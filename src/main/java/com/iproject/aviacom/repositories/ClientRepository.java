package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByEmail(String email);
}
