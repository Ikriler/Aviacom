package com.iproject.aviacom.controllers;

import com.iproject.aviacom.configs.DBConfig;
import com.iproject.aviacom.models.*;
import com.iproject.aviacom.repositories.*;
import com.iproject.aviacom.services.ConsoleService;
import com.iproject.aviacom.services.SalesExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private ResourceLoader resourceLoader;
    private RuntimeException runtimeException;

    @GetMapping
    public String saleList(@RequestParam(required = false) String listType,
                           @ModelAttribute("voyage") Voyage voyage,
                           Model model) {
        model.addAttribute("voyage", voyage);
        model.addAttribute("listType", listType);
        if (listType != null) {
            switch (listType) {
                case "Доступные":
                    model.addAttribute("tickets", ticketRepository.findByBookingIsNullAndSaleIsNullAndVoyage(voyage));
                    break;
                default:
                    model.addAttribute("saleTable", true);
                    model.addAttribute("tickets", ticketRepository.findByBookingIsNullAndSaleIsNotNullAndVoyage(voyage));
            }
        } else {
            model.addAttribute("tickets", ticketRepository.findByBookingIsNullAndSaleIsNull());
        }
        model.addAttribute("voyages", voyageRepository.findAll());
        return "sale/main";
    }

    @GetMapping("/add")
    public String saleAddPage(@ModelAttribute("sale") Sale sale, @ModelAttribute("ticket") Ticket ticket, Model model) {
        model.addAttribute("ticket", ticket);
        model.addAttribute("clients", clientRepository.findAll());
        return "sale/add";
    }

    @PostMapping("/add")
    public String saleAdd(@ModelAttribute("sale") @Valid Sale sale,
                          BindingResult bindingResult,
                          Model model) {
        model.addAttribute("ticket", sale.getTicket());
        model.addAttribute("clients", clientRepository.findAll());

        String employeeLogin = SecurityContextHolder.getContext().getAuthentication().getName();

        Employee employee = employeeRepository.findByLogin(employeeLogin);

        if (employee == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "sale/add";
        }
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        sale.setSaleDate(date);

        sale.setEmployee(employee);

        saleRepository.save(sale);

        return "redirect:/sale";
    }

    @PostMapping("/delete")
    public String saleDelete(@ModelAttribute("ticket") Ticket ticket) {
        if (ticket.getSale() != null) {
            saleRepository.delete(ticket.getSale());
        }
        return "redirect:/sale";
    }

    @GetMapping("/report")
    public ResponseEntity<Resource> getReport() throws IOException {
//        DBConfig.initField();
//        String tableName = "sale";
//        String query = "SELECT ticket.voyage_id as 'Код рейса', sale_date as 'Дата продажи', ticket.price as 'Цена', ticket.seat as 'Место', seat_class.name as 'Класс места', concat(client.surname, ' ', client.name, ' ', client.patronymic) as 'ФИО клиента', client.phone as 'Контактные данные клиента', concat(employee.surname, ' ', employee.name, ' ', employee.patronymic) as 'ФИО сотрудника', employee.phone as 'Контактные данные сотрудника' FROM sale LEFT JOIN ticket ON ticket.id = sale.ticket_id LEFT JOIN client ON sale.client_id = client.id LEFT JOIN employee ON employee.id = sale.employee_id LEFT JOIN seat_class ON seat_class.id = ticket.seat_class_id;";
//        String command = String.format("mysql -u%s -p%s -h%s %s --default-character-set=utf8 -e \"%s\" > %s",
//                DBConfig.username, DBConfig.password, DBConfig.host, DBConfig.dbname, query, "load/" + tableName + ".xls");
//
//        try {
//            ConsoleService.exec(command);
//            String uri = Paths.get("load/" + tableName + ".xls").toUri().toString();
//            org.springframework.core.io.Resource resource = resourceLoader.getResource(uri);
//            System.out.println(uri);
//            ResponseEntity<org.springframework.core.io.Resource> body = ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
//                    .contentLength(resource.contentLength())
//                    .body(resource);
//            return body;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = HttpHeaders.CONTENT_DISPOSITION;
        String headerValue = "attachment; filename=sales_" + currentDateTime + ".xlsx";

        List<Sale> listSales = (List<Sale>) saleRepository.findAll();

        SalesExcelExporter excelExporter = new SalesExcelExporter(listSales);

        excelExporter.export(currentDateTime);

        // return response;

        try {
            String uri = Paths.get("load/sales_" + currentDateTime + ".xlsx").toUri().toString();
            org.springframework.core.io.Resource resource = resourceLoader.getResource(uri);
            ResponseEntity<org.springframework.core.io.Resource> body = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .contentLength(resource.contentLength())
                    .body(resource);
            return body;
        } catch (IOException e) {
            throw runtimeException;
        }

    }
}
