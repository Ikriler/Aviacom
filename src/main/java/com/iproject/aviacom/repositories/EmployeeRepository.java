package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findByLogin(String login);

    List<Employee> findByNameContainsOrSurnameContainsOrPatronymicContains(String name, String surname, String patronymic);

}
