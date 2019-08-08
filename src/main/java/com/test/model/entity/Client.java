package com.test.model.entity;

import java.util.List;

public class Client extends User {
    private List<Appointment> appointments;

    public Client(){

    }

    public Client(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Client(Long id, String username, String fullName, String email, String password, Role role, List<Appointment> appointments) {
        super(id, username, fullName, email, password, role);
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
