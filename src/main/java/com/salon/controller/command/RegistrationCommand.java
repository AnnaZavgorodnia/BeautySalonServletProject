package com.salon.controller.command;

import com.salon.model.entity.Role;
import com.salon.model.entity.User;
import com.salon.model.exception.NotUniqueEntity;
import com.salon.model.service.UserService;
import com.salon.utils.RegistrationUtils;
import com.salon.utils.SecurityUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class RegistrationCommand implements Command {

    private final UserService userService;

    private static final Logger logger = Logger.getLogger(RegistrationCommand.class);

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

            logger.info("returning registration page");
            return "/WEB-INF/views/registrationView.jsp";
        }

        if(!RegistrationUtils.checkIfValidUser(
                request, username, firstName,
                lastName, email, password)){

            RegistrationUtils.setUserAttributes(request, username, firstName,
                    lastName, email, password);

            logger.info("invalid data -> returning registration page");
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
        } catch (NotUniqueEntity e){
            request.setAttribute("userExistsError"," ");
            RegistrationUtils.setUserAttributes(request, username, firstName,
                    lastName, email, password);

            logger.info("duplicate value of client -> returning registration page");
            return "/WEB-INF/views/registrationView.jsp";
        }

        logger.info("client created successfully -> redirecting to login page");
        return "redirect:/app/login";
    }
}
