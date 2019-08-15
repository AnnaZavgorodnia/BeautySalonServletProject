package com.salon.controller.command;

import com.google.gson.Gson;
import com.salon.model.entity.Appointment;
import com.salon.model.entity.Role;
import com.salon.model.entity.User;
import com.salon.model.service.AppointmentService;

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
