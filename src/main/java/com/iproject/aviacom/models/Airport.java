package com.iproject.aviacom.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 200, message = "Поле должно иметь меньше 200 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;

    @Size(max = 200, message = "Поле должно иметь меньше 200 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    public Airport(String name, String address, City city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public Airport() {}

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
