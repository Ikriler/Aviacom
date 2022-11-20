package com.iproject.aviacom.seeders;

import com.iproject.aviacom.enums.RolesEnum;
import com.iproject.aviacom.models.Employee;
import com.iproject.aviacom.models.LayoutType;
import com.iproject.aviacom.models.Post;
import com.iproject.aviacom.repositories.EmployeeRepository;
import com.iproject.aviacom.repositories.LayoutTypeRepository;
import com.iproject.aviacom.repositories.PostRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSeeder {

    private static List<Employee> employeeList = new ArrayList<>();

    private static void init(PostRepository postRepository) {
        Post adminPost = postRepository.findByName("ADMIN");
        employeeList.add(new Employee("Админ", "Админ", "Админ", "+7(999)999-99-99", "Админ", new BCryptPasswordEncoder().encode("Админ"), adminPost));
    }

    public static void seed(EmployeeRepository employeeRepository, PostRepository postRepository) {
        init(postRepository);
        for (Employee employee : employeeList) {
            if (employeeRepository.findByLogin(employee.getLogin()) == null) {
                employeeRepository.save(employee);
            }
        }
    }
}
