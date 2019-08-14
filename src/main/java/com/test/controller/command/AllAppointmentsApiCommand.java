package com.test.controller.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.config.SecurityConfig;
import com.test.model.entity.Appointment;
import com.test.model.entity.Role;
import com.test.model.entity.User;
import com.test.model.service.AppointmentService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllAppointmentsApiCommand implements Command {

    private final AppointmentService appointmentService;

    public AllAppointmentsApiCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("loginedUser");
        List<Appointment> appointments = user.getRole() == Role.MASTER
                ? appointmentService.getAllAppointmentsByClientOrMaster_Username(user.getUsername(), user.getRole())
                : appointmentService.findAll();

        return new Gson().toJson(appointments);
    }
}
