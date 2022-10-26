package com.iproject.aviacom.services;

import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.models.Employee;
import com.iproject.aviacom.repositories.ClientRepository;
import com.iproject.aviacom.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String role = "";
        UserDetails userDetails = null;
        Client client = clientRepository.findByEmail(username);
        Employee employee = employeeRepository.findByLogin(username);

        if(client != null) {
            role = "USER";
            userDetails = User.builder()
                    .username(client.getEmail())
                    .password(client.getPassword())
                    .roles(role)
                    .build();
        }
        if(employee != null) {
            role = employee.getPost().getName();
            userDetails = User.builder()
                    .username(employee.getLogin())
                    .password(employee.getPassword())
                    .roles(role)
                    .build();
        }

        return userDetails;
    }
}
