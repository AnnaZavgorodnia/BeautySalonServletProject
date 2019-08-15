package com.salon.controller.command;

import com.salon.model.entity.User;
import com.salon.model.service.UserService;
import com.salon.utils.AppUtils;
import com.salon.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command  {

    private final UserService userService;
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    private final String USER_ALREADY_LOGGINED_ERROR = "login.page.error.already.loggined";
    private final String WRONG_USERNAME_OR_PASSWORD_ERROR = "login.page.error.wrong.input";

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        request.setAttribute("module", "login");

        if(username == null || password == null){
            if(request.getParameter("logout") != null) {
                request.setAttribute("logout", "logout");
            }

            logger.info("returning logout page");
            return "/WEB-INF/views/loginView.jsp";
        }

        if(SecurityUtils.checkUserIsLogged(request, username)){
            request.setAttribute("errorMessage", USER_ALREADY_LOGGINED_ERROR);

            logger.info("user already loggined -> returning login page");
            return "/WEB-INF/views/loginView.jsp";
        }

        Optional<User> user = userService.getUserByUsername(username);

        if (!user.isPresent() || !BCrypt.checkpw(password, user.get().getPassword())) {

            request.setAttribute("errorMessage", WRONG_USERNAME_OR_PASSWORD_ERROR);

            logger.info("login - wrong credentials -> returning login page");
            return "/WEB-INF/views/loginView.jsp";
        }


        AppUtils.storeLoginedUser(request.getSession(), user.get());

        logger.info("user successfully loggined -> redirecting to home page");
        return "redirect:/app/home";
    }
}
