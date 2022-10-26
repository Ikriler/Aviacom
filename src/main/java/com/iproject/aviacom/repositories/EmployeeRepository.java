package com.iproject.aviacom.repositories;

import com.iproject.aviacom.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findByLogin(String login);

}
