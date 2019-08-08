package com.test.controller.command;

import com.test.model.entity.User;
import com.test.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsersCommand implements Command {

    private UserService userService;

    public AllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users" , users);
        return "/jsp/users.jsp";
    }
}
