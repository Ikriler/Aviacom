package com.iproject.aviacom.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 200, message = "Поле должно иметь меньше 200 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    List<City> cityList;

    public Country(String name) {
        this.name = name;
    }

    public Country() {}

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

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
