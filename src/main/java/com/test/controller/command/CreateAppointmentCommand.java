package com.test.controller.command;

import com.google.gson.Gson;
import com.test.model.entity.*;
import com.test.model.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateAppointmentCommand implements Command {

    private final AppointmentService appointmentService;

    public CreateAppointmentCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String reqId = request.getParameter("masterId");
        String reqAppDate = request.getParameter("appDate");
        String reqAppTime = request.getParameter("appTime");
        String reqServiceId = request.getParameter("serviceId");
        if(reqId != null || reqAppDate != null
                || reqAppTime != null || reqServiceId != null){
            Long masterId = Long.valueOf(reqId);
            LocalDate appDate = LocalDate.parse(reqAppDate);
            LocalTime appTime = LocalTime.parse(reqAppTime);
            Long serviceId = Long.valueOf(reqServiceId);

            Client client = new Client();
            User currentUser = (User)request.getSession().getAttribute("loginedUser");
            client.setId(currentUser.getId());

            Service service = new Service();
            service.setId(serviceId);

            Master master = new Master();
            master.setId(masterId);

            Appointment app = new Appointment();
            app.setAppDate(appDate);
            app.setAppTime(appTime);
            app.setClient(client);
            app.setMaster(master);
            app.setService(service);

            appointmentService.create(app);

            return new Gson().toJson(app);
        }
        return "{\"errorMessage\":\"data is not valid\"}";
    }
}
