package com.test.controller.command;

import com.test.model.service.MasterService;

import javax.servlet.http.HttpServletRequest;

public class AllMastersAdminCommand implements Command {

    private final MasterService masterService;

    public AllMastersAdminCommand(MasterService masterService) {
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("masters", masterService.getAllMasters());
        request.setAttribute("module", "all_masters");
        return "/WEB-INF/views/allMastersAdminView.jsp";
    }
}
