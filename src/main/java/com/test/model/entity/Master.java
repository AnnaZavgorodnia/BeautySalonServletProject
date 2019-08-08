package com.test.model.entity;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import java.util.ArrayList;
import java.util.List;

public class Master extends User {
    private List<Appointment> appointments = new ArrayList<>();
    private String instagram;
    private Position position;
    private String imagePath;
    private List<Service> services = new ArrayList<>();


    public Master() {
    }

    public Master(List<Appointment> appointments, String instagram, Position position, String imagePath, List<Service> services) {
        this.appointments = appointments;
        this.instagram = instagram;
        this.position = position;
        this.imagePath = imagePath;
        this.services = services;
    }

    public Master(Long id, String username, String fullName, String email, String password, Role role, List<Appointment> appointments, String instagram, Position position, String imagePath, List<Service> services) {
        super(id, username, fullName, email, password, role);
        this.appointments = appointments;
        this.instagram = instagram;
        this.position = position;
        this.imagePath = imagePath;
        this.services = services;
    }

    public Master(String username, String fullName, String email, String password, Role role, List<Appointment> appointments, String instagram, Position position, String imagePath, List<Service> services) {
        super(username, fullName, email, password, role);
        this.appointments = appointments;
        this.instagram = instagram;
        this.position = position;
        this.imagePath = imagePath;
        this.services = services;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
