package com.iproject.aviacom.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class SeatClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private Double price;

    @OneToMany(mappedBy = "seatClass")
    private List<Ticket> ticketList;

    public SeatClass(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public SeatClass() {}

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
