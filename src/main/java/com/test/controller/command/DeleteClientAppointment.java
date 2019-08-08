package com.test.controller.command;

import com.test.model.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;

public class DeleteClientAppointment implements Command {

    private final AppointmentService appointmentService;

    public DeleteClientAppointment(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String reqAppId = request.getParameter("appId");
        if(reqAppId != null){
            appointmentService.deleteById(Long.valueOf(reqAppId));
        }

        return "redirect:/app/me/appointments";
    }
}
