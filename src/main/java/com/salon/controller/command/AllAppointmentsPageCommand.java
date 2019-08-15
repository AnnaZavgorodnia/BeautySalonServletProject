package com.salon.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AllAppointmentsPageCommand implements Command  {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("module", "all_appointments");
        return "/WEB-INF/views/allAppointmentsView.jsp";
    }
}
