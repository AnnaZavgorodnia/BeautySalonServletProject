package com.test.controller.command;

import com.google.gson.Gson;
import com.test.model.entity.*;
import com.test.model.exception.NotUniqueEntity;
import com.test.model.service.AppointmentService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAppointmentCommand implements Command {

    private final AppointmentService appointmentService;
    private static final Logger logger = Logger.getLogger(CreateAppointmentCommand.class);

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

            try {
                appointmentService.create(app);
            } catch (NotUniqueEntity e){
                logger.info("duplicate value for appointment -> returning error message");
                return "{\"errorMessage\":\"appointment already exists\"}";
            }

            logger.info("appointment created successfully -> returning created value");
            return new Gson().toJson(app);
        }

        logger.info("invalid data in createAppointment -> returning error message");
        return "{\"errorMessage\":\"data is not valid\"}";
    }
}
