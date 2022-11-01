package com.iproject.aviacom.services;

import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.models.Employee;
import com.iproject.aviacom.repositories.ClientRepository;
import com.iproject.aviacom.repositories.EmployeeRepository;
import org.springframework.ui.Model;

public class ValidatorService {
    public static boolean notLessZero(Model model, Double value, String fieldName){
        if(value < 0) {
            model.addAttribute(fieldName, "Значние не должно быть меньше нуля");
            return false;
        }
        return true;
    }

    public static boolean checkNotExistsPassport(Model model, String passportSeries, String passportNumber, ClientRepository clientRepository, String fieldName, Client client) {
        Client dbClient = clientRepository.findByPassportSeriesAndPassportNumber(passportSeries, passportNumber);
        if(dbClient != null && dbClient.getId() != client.getId()) {
            model.addAttribute(fieldName, "Паспортные данные уже существуют");
            return false;
        }
        return true;
    }

    public static boolean checkNotExistsPhoneClient(Model model, String phone, ClientRepository clientRepository, String fieldName, Client client) {
        Client dbClient = clientRepository.findByPhone(phone);
        if(dbClient != null && dbClient.getId() != client.getId()) {
            model.addAttribute(fieldName, "Телефон уже существует");
            return false;
        }
        return true;
    }

    public static boolean checkNotExistsPhoneEmployee(Model model, String phone, EmployeeRepository employeeRepository, String fieldName, Employee employee) {
        Employee dbEmployee = employeeRepository.findByPhone(phone);
        if(dbEmployee != null && dbEmployee.getId() != employee.getId()) {
            model.addAttribute(fieldName, "Телефон уже существует");
            return false;
        }
        return true;
    }
}
