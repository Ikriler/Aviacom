package com.iproject.aviacom.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "city_in_id", referencedColumnName = "id")
    private City cityIn;

    @ManyToOne
    @JoinColumn(name = "city_out_id", referencedColumnName = "id")
    private City cityOut;

    @ManyToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "id")
    private Airplane airplane;

    private Date dateTimeIn;

    private Date dateTimeOut;

    @OneToMany(mappedBy = "voyage")
    private List<Ticket> tickets;

    public Voyage(City cityIn, City cityOut, Airplane airplane, Date dateTimeIn, Date dateTimeOut) {
        this.cityIn = cityIn;
        this.cityOut = cityOut;
        this.airplane = airplane;
        this.dateTimeIn = dateTimeIn;
        this.dateTimeOut = dateTimeOut;
    }

    public Voyage() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public City getCityIn() {
        return cityIn;
    }

    public void setCityIn(City cityIn) {
        this.cityIn = cityIn;
    }

    public City getCityOut() {
        return cityOut;
    }

    public void setCityOut(City cityOut) {
        this.cityOut = cityOut;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Date getDateTimeIn() {
        return dateTimeIn;
    }

    public void setDateTimeIn(Date dateTimeIn) {
        this.dateTimeIn = dateTimeIn;
    }

    public Date getDateTimeOut() {
        return dateTimeOut;
    }

    public void setDateTimeOut(Date dateTimeOut) {
        this.dateTimeOut = dateTimeOut;
    }
}
