package com.test.controller.command;

import com.test.model.entity.Master;
import com.test.model.entity.Role;
import com.test.model.entity.User;
import com.test.model.service.MasterService;
import com.test.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class MyAccountCommand implements Command {

    private final MasterService masterService;

    public MyAccountCommand(MasterService masterService) {
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("loginedUser");
        if(user.getRole() == Role.MASTER){
            Master master = masterService.findById(user.getId()).get();
            request.setAttribute("image", master.getImagePath());
        }
        request.setAttribute("module", "my_account");
        return "/WEB-INF/views/userAccountView.jsp";
    }
}
