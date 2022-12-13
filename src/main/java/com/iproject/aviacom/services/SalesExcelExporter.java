package com.iproject.aviacom.services;

import com.iproject.aviacom.models.Client;
import com.iproject.aviacom.models.Employee;
import com.iproject.aviacom.models.Sale;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SalesExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Sale> listSales;

    public SalesExcelExporter(List<Sale> listSales) {
        this.listSales = listSales;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Sales");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Код рейса", style);
        createCell(row, 1, "Дата продажи", style);
        createCell(row, 2, "Цена", style);
        createCell(row, 3, "Место", style);
        createCell(row, 4, "Класс места", style);
        createCell(row, 5, "ФИО клиента", style);
        createCell(row, 6, "Контактные данные клиента", style);
        createCell(row, 7, "ФИО сотрудника", style);
        createCell(row, 8, "Контактные данные сотрудника", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Sale sale : listSales) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            Client client = sale.getClient();
            Employee employee = sale.getEmployee();

            String ClientFIO = client.getSurname() + " " + client.getName() + " " + client.getPatronymic();
            String EmployeeFIO = "";

            String ClientInfoData = client.getPhone() + " - " + client.getEmail();
            String EmployeeInfoData = "";

            if(employee != null) {
                EmployeeInfoData = employee.getPhone();
                EmployeeFIO = employee.getSurname() + " " + employee.getName() + " " + employee.getPatronymic();
            }

            createCell(row, columnCount++, String.valueOf(sale.getTicket().getVoyage().getId()), style);
            createCell(row, columnCount++, sale.getSaleDate(), style);
            createCell(row, columnCount++, sale.getTicket().getPrice().toString(), style);
            createCell(row, columnCount++, sale.getTicket().getSeat(), style);
            createCell(row, columnCount++, sale.getTicket().getSeatClass().getName(), style);
            createCell(row, columnCount++, ClientFIO, style);
            createCell(row, columnCount++, ClientInfoData, style);
            createCell(row, columnCount++, EmployeeFIO, style);
            createCell(row, columnCount++, EmployeeInfoData, style);

        }
    }

    public void export(String currentDateTime) throws IOException {
        writeHeaderLine();
        writeDataLines();

        String path = "load/sales_" + currentDateTime + ".xlsx";

        File excelFile = new File(path);

        OutputStream outputStream = Files.newOutputStream(Paths.get(path));
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
