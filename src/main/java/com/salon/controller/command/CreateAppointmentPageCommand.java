package com.salon.controller.command;

import com.salon.model.entity.Master;
import com.salon.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;

public class CreateAppointmentPageCommand implements Command {

    private final MasterService masterService;

    public CreateAppointmentPageCommand(MasterService masterService) {
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("masterId"));
        Master master = masterService.findById(id).get();
        request.setAttribute("master", master);
        request.setAttribute("module", "masters");
        return "/WEB-INF/views/createAppView.jsp";
    }
}
