package com.test.controller.command;

import com.test.model.entity.Appointment;
import com.test.model.entity.User;
import com.test.model.service.AppointmentService;
import com.test.model.service.UserService;

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
        request.setAttribute("module", "my_appointments");
        return "/WEB-INF/views/user_apps.jsp";
    }
}
