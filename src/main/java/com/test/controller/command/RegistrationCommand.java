package com.test.controller.command;

import com.test.model.entity.Role;
import com.test.model.entity.User;
import com.test.model.exception.UserAlreadyExistsException;
import com.test.model.service.UserService;
import com.test.utils.RegistrationUtils;
import com.test.utils.SecurityUtils;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private final UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        request.setAttribute("module", "registration");

        if(username == null || firstName == null || lastName == null
                || email == null || password == null) {
            return "/WEB-INF/views/registrationView.jsp";
        }

        if(!RegistrationUtils.checkIfValid(
                request, username, firstName,
                lastName, email, password, null)){

            RegistrationUtils.setUserAttributes(request, username, firstName,
                    lastName, email, password, null);

            return "/WEB-INF/views/registrationView.jsp";

        }

        User user = new User();

        user.setUsername(username);
        user.setFullName(firstName + " " + lastName);
        user.setEmail(email);
        user.setPassword(SecurityUtils.getHashedPassword(password));
        user.setRole(Role.CLIENT);

        try{
            userService.createUser(user);
        } catch (UserAlreadyExistsException e){
            request.setAttribute("userExistsError"," ");
            RegistrationUtils.setUserAttributes(request, username, firstName,
                    lastName, email, password, null);
            return "/WEB-INF/views/registrationView.jsp";
        }

        return "redirect:/app/login";
    }
}
