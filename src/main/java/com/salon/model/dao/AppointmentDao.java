package com.salon.model.dao;

import com.salon.model.entity.Appointment;
import com.salon.model.entity.Role;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao extends GenericDao<Appointment>{
    List<Appointment> findAllByClientOrMaster_Username(String username, Role role);

    List<Appointment> findAllByMasterIdAndDate(Long masterId, LocalDate date);
}
