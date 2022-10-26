package com.iproject.aviacom.models;

import javax.persistence.*;

@Entity
public class LayoutType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    public LayoutType(String name) {
        this.name = name;
    }

    public LayoutType() {}

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
}
