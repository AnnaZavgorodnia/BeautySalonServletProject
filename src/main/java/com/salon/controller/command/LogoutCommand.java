package com.salon.controller.command;

import com.salon.utils.SecurityUtils;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command  {
    @Override
    public String execute(HttpServletRequest request) {
        SecurityUtils.logoutUser(request.getSession());
        request.getSession().invalidate();
        return "redirect:/app/login?logout";
    }
}
