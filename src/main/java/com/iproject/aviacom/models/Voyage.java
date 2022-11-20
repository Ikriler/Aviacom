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
    @JoinColumn(name = "city_inc_id", referencedColumnName = "id")
    private City cityInc;

    @ManyToOne
    @JoinColumn(name = "city_out_id", referencedColumnName = "id")
    private City cityOut;

    @ManyToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "id")
    private Airplane airplane;


    private String dateTimeInc;


    private String dateTimeOut;

    @OneToMany(mappedBy = "voyage", cascade = CascadeType.REMOVE)
    private List<Ticket> tickets;


    @ManyToOne
    @JoinColumn(name = "airport_inc_id", referencedColumnName = "id")
    private Airport airportInc;

    @ManyToOne
    @JoinColumn(name = "airport_out_id", referencedColumnName = "id")
    private Airport airportOut;


    public Voyage(City cityInc, City cityOut, Airplane airplane, String dateTimeInc, String dateTimeOut, List<Ticket> tickets, Airport airportInc, Airport airportOut) {
        this.cityInc = cityInc;
        this.cityOut = cityOut;
        this.airplane = airplane;
        this.dateTimeInc = dateTimeInc;
        this.dateTimeOut = dateTimeOut;
        this.tickets = tickets;
        this.airportInc = airportInc;
        this.airportOut = airportOut;
    }

    public Voyage() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public City getCityInc() {
        return cityInc;
    }

    public void setCityInc(City cityInc) {
        this.cityInc = cityInc;
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

    public String getDateTimeInc() {
        return dateTimeInc;
    }

    public void setDateTimeInc(String dateTimeInc) {
        this.dateTimeInc = dateTimeInc;
    }

    public String getDateTimeOut() {
        return dateTimeOut;
    }

    public void setDateTimeOut(String dateTimeOut) {
        this.dateTimeOut = dateTimeOut;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Airport getAirportInc() {
        return airportInc;
    }

    public void setAirportInc(Airport airportInc) {
        this.airportInc = airportInc;
    }

    public Airport getAirportOut() {
        return airportOut;
    }

    public void setAirportOut(Airport airportOut) {
        this.airportOut = airportOut;
    }
}
