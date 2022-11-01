package com.iproject.aviacom.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;
    @NotBlank(message = "Поле не должно быть пустым")
    private String surname;
    @NotBlank(message = "Поле не должно быть пустым")
    private String patronymic;
    @Pattern(regexp = "^\\+[78]\\([0-9]{3}\\)[0-9]{3}-[0-9]{2}-[0-9]{2}$", message = "Телефон должен иметь вид +7(999)999-99-99")
    @Column(unique = true)
    @NotBlank(message = "Поле не должно быть пустым")
    private String phone;
    @NotBlank(message = "Поле не должно быть пустым")
    private String login;

    private String password;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Sale> saleList;

    public Employee(String name, String surname, String patronymic, String phone, String login, String password, Post post) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.post = post;
    }

    public Employee() {}

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
