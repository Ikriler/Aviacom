package com.iproject.aviacom.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(unique = true)
    private String name;

    @Lob
    @NotBlank(message = "Поле не должно быть пустым")
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "cityInc", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voyage> voyageListInc;

    @OneToMany(mappedBy = "cityOut", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voyage> voyageListOut;

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
