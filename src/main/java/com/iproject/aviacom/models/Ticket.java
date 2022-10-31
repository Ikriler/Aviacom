package com.iproject.aviacom.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "voyage_id", referencedColumnName = "id")
    private Voyage voyage;

    @ManyToOne
    @JoinColumn(name = "seat_class_id", referencedColumnName = "id")
    private SeatClass seatClass;

    private Double price;

    private String seat;

    @OneToOne(optional = true, mappedBy = "ticket", cascade = CascadeType.REMOVE)
    private Booking booking;

    @OneToOne(optional = true, mappedBy = "ticket", cascade = CascadeType.REMOVE)
    private Sale sale;

    public Ticket(Voyage voyage, SeatClass seatClass, Double price, String seat) {
        this.voyage = voyage;
        this.seatClass = seatClass;
        this.price = price;
        this.seat = seat;
    }

    public Ticket() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
