package com.salon.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private Long id;
    private LocalDate appDate;
    private LocalTime appTime;
    private User client;
    private Master master;
    private Service service;

    public Appointment() {
    }

    public Appointment(Long id, LocalDate appDate, LocalTime appTime, User client, Master master, Service service) {
        this.id = id;
        this.appDate = appDate;
        this.appTime = appTime;
        this.client = client;
        this.master = master;
        this.service = service;
    }

    public Appointment(LocalDate appDate, LocalTime appTime, User client, Master master, Service service) {
        this.appDate = appDate;
        this.appTime = appTime;
        this.client = client;
        this.master = master;
        this.service = service;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAppDate() {
        return appDate;
    }

    public void setAppDate(LocalDate appDate) {
        this.appDate = appDate;
    }

    public void setAppTime(LocalTime appTime) {
        this.appTime = appTime;
    }

    public LocalTime getAppTime() {
        return appTime;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
