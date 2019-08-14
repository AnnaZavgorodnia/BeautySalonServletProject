package com.test.controller.command;

import com.google.gson.Gson;
import com.test.model.entity.Appointment;
import com.test.model.service.AppointmentService;
import com.test.model.service.MasterService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class MasterAppointmentsApiCommand implements Command {

    private final AppointmentService appointmentService;
    private static final Logger logger = Logger.getLogger(MasterAppointmentsApiCommand.class);

    public MasterAppointmentsApiCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String reqId = request.getParameter("masterId");
        String reqDate = request.getParameter("date");
        if(reqId != null && reqDate != null){
            Long masterId = Long.valueOf(reqId);
            LocalDate date = LocalDate.parse(reqDate.substring(0,10));
            List<Appointment> appointments = appointmentService.findAllByMasterIdAndDate(masterId, date);

            logger.info("returning found appointments");
            return new Gson().toJson(appointments);
        }

        logger.info("invalid data -> returning error message");
        return "{\"errorMessage:\" \"Invalid data\"";
    }
}
