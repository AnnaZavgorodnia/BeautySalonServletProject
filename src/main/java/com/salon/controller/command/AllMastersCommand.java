package com.salon.controller.command;

import com.salon.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;

public class AllMastersCommand implements Command {

    private final MasterService masterService;

    public AllMastersCommand(MasterService masterService) {
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("masters",masterService.getAllMasters());
        request.setAttribute("module", "masters");
        return "/WEB-INF/views/mastersView.jsp";
    }
}
