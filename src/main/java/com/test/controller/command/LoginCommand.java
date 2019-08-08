package com.test.controller.command;

import com.test.model.entity.User;
import com.test.model.service.UserService;
import com.test.utils.AppUtils;
import com.test.utils.SecurityUtils;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command  {

    private final UserService userService;

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
            return "/WEB-INF/views/loginView.jsp";
        }

        if(SecurityUtils.checkUserIsLogged(request, username)){
            request.setAttribute("errorMessage", USER_ALREADY_LOGGINED_ERROR);
            return "/WEB-INF/views/loginView.jsp";
        }

        Optional<User> user = userService.getUserByUsername(username);

        if (!user.isPresent() || !BCrypt.checkpw(password, user.get().getPassword())) {

            request.setAttribute("errorMessage", WRONG_USERNAME_OR_PASSWORD_ERROR);

            return "/WEB-INF/views/loginView.jsp";
        }


        AppUtils.storeLoginedUser(request.getSession(), user.get());

        return "redirect:/app/home";
    }
}
