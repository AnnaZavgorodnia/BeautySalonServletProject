package com.test.model.dao;

import com.test.model.entity.Appointment;
import com.test.model.entity.Role;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao extends GenericDao<Appointment>{
    List<Appointment> findAllByClientOrMaster_Username(String username, Role role);

    List<Appointment> findAllByMasterIdAndDate(Long masterId, LocalDate date);
}
