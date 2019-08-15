package com.salon.controller.command;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("module", "index");
        return "/WEB-INF/views/homeView.jsp";
    }
}
