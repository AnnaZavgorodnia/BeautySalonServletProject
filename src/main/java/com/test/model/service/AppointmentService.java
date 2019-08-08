package com.test.model.service;

import com.test.model.dao.AppointmentDao;
import com.test.model.dao.DaoFactory;
import com.test.model.entity.Appointment;
import com.test.model.entity.Role;

import java.time.LocalDate;
import java.util.List;

public class AppointmentService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Appointment> getAllAppointmentsByClientOrMaster_Username(String username, Role role){
        try (AppointmentDao dao = daoFactory.createAppointmentDao()) {
            return dao.findAllByClientOrMaster_Username(username, role);
        }
    }

    public List<Appointment> findAll() {
        try (AppointmentDao dao = daoFactory.createAppointmentDao()) {
            return dao.findAll();
        }
    }

    public List<Appointment> findAllByMasterIdAndDate(Long masterId, LocalDate date) {
        try (AppointmentDao dao = daoFactory.createAppointmentDao()) {
            return dao.findAllByMasterIdAndDate(masterId, date);
        }
    }

    public void create(Appointment app) {
        try (AppointmentDao dao = daoFactory.createAppointmentDao()) {
            dao.create(app);
        }
    }

    public void deleteById(Long id) {
        try (AppointmentDao dao = daoFactory.createAppointmentDao()) {
            dao.delete(id);
        }
    }
}
