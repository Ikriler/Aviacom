package com.iproject.aviacom.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 200, message = "Поле должно иметь меньше 200 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    @Column(unique = true)
    private String name;

    @Lob
    @NotBlank(message = "Поле не должно быть пустым")
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "cityInc", cascade = CascadeType.REMOVE)
    private List<Voyage> voyageListInc;

    @OneToMany(mappedBy = "cityOut", cascade = CascadeType.REMOVE)
    private List<Voyage> voyageListOut;

    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
    private List<Airport> airportList;

    public City(String name, String description, Country country) {
        this.name = name;
        this.description = description;
        this.country = country;
    }

    public City() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Voyage> getVoyageListInc() {
        return voyageListInc;
    }

    public void setVoyageListInc(List<Voyage> voyageListInc) {
        this.voyageListInc = voyageListInc;
    }

    public List<Voyage> getVoyageListOut() {
        return voyageListOut;
    }

    public void setVoyageListOut(List<Voyage> voyageListOut) {
        this.voyageListOut = voyageListOut;
    }
}
