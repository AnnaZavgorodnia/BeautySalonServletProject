package com.salon.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private Long id;
    private String name;
    private String nameUa;
    private Integer price;
    private List<Master> masters = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public Service(){}

    public Service(Long id, String name, String nameUa, Integer price) {
        this.id = id;
        this.name = name;
        this.nameUa = nameUa;
        this.price = price;
    }

    public Service(String name, String nameUa, Integer price) {
        this.name = name;
        this.nameUa = nameUa;
        this.price = price;
    }

    public Service(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Master> getMasters() {
        return masters;
    }

    public void setMasters(List<Master> masters) {
        this.masters = masters;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }
}
