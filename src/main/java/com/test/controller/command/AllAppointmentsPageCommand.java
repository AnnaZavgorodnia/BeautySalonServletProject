package com.test.controller.command;

import com.google.gson.Gson;
import com.test.model.entity.Appointment;
import com.test.model.entity.Role;
import com.test.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllAppointmentsPageCommand implements Command  {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("module", "all_appointments");
        return "/WEB-INF/views/allAppointmentsView.jsp";
    }
}
