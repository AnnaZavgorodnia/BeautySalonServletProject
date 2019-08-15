package com.salon.controller.command;

import com.salon.model.entity.Appointment;
import com.salon.model.entity.User;
import com.salon.model.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsersAppointmentsCommand implements Command {

    private final AppointmentService appointmentService;

    public AllUsersAppointmentsCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("loginedUser");
        List<Appointment> appointments = appointmentService.getAllAppointmentsByClientOrMaster_Username(user.getUsername(), user.getRole());
        request.setAttribute("appointments", appointments);
        request.setAttribute("module", "my_account");
        return "/WEB-INF/views/userAppointmentsView.jsp";
    }
}
