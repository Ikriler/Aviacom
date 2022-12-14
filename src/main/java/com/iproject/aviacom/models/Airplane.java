package com.iproject.aviacom.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(max = 200, message = "Поле должно иметь меньше 200 символов")
    @NotBlank(message = "Поле не должно быть пустым")
    @Column(unique = true)
    private String model;

    private int firstClassClientsCount;

    private int businessClassClientsCount;

    private int economicClassClientsCount;

    @ManyToOne
    @JoinColumn(name = "layout_type_id", referencedColumnName = "id")
    private LayoutType layoutType;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.REMOVE)
    private List<Voyage> voyageList;

    public Airplane(int firstClassClientsCount, int businessClassClientsCount, int economicClassClientsCount, LayoutType layoutType, String model) {
        this.model = model;
        this.firstClassClientsCount = firstClassClientsCount;
        this.businessClassClientsCount = businessClassClientsCount;
        this.economicClassClientsCount = economicClassClientsCount;
        this.layoutType = layoutType;
    }

    public Airplane() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFirstClassClientsCount() {
        return firstClassClientsCount;
    }

    public void setFirstClassClientsCount(int firstClassClientsCount) {
        this.firstClassClientsCount = firstClassClientsCount;
    }

    public int getBusinessClassClientsCount() {
        return businessClassClientsCount;
    }

    public void setBusinessClassClientsCount(int businessClassClientsCount) {
        this.businessClassClientsCount = businessClassClientsCount;
    }

    public int getEconomicClassClientsCount() {
        return economicClassClientsCount;
    }

    public void setEconomicClassClientsCount(int economicClassClientsCount) {
        this.economicClassClientsCount = economicClassClientsCount;
    }

    public LayoutType getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(LayoutType layoutType) {
        this.layoutType = layoutType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Voyage> getVoyageList() {
        return voyageList;
    }

    public void setVoyageList(List<Voyage> voyageList) {
        this.voyageList = voyageList;
    }
}
