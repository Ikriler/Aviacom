package com.iproject.aviacom.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(max = 200, message = "Поле должно иметь меньше 200 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;
    @Size(max = 200, message = "Поле должно иметь меньше 200 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    private String surname;
    @Size(max = 200, message = "Поле должно иметь меньше 200 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    private String patronymic;
    @Size(max = 200, message = "Поле должно иметь меньше 200 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    @Column(unique = true)
    private String email;
    @Pattern(regexp = "^\\+7\\([0-9]{3}\\)[0-9]{3}-[0-9]{2}-[0-9]{2}$", message = "Телефон должен иметь вид +7(999)999-99-99")
    @Column(unique = true)
    @NotBlank(message = "Поле не должно быть пустым")
    private String phone;
    @Size(min = 4, max = 4, message = "Поле должно иметь 4 символа")
    @NotBlank(message = "Поле не должно быть пустым")
    private String passportSeries;

    @Size(min = 6, max = 6, message = "Поле должно иметь 6 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    private String passportNumber;

    @Lob
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Sale> saleList;

    public Client(String name, String surname, String patronymic, String email, String phone, String passportSeries, String passportNumber, String password) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.password = password;
    }

    public Client() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }
}
