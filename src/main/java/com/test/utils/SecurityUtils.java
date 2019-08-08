package com.test.utils;

import com.test.config.SecurityConfig;
import com.test.model.entity.Role;
import com.test.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecurityUtils {

    public static boolean isSecurityPage(String urlPattern) {
        Set<Role> roles = SecurityConfig.getAllAppRoles();


        for (Role role : roles) {
            List<String> urlPatterns = SecurityConfig.getSecuredRoutesForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(String urlPattern, Role userRole) {
        Set<Role> roles = SecurityConfig.getAllAppRoles();

        for (Role role : roles) {
            if (role != userRole) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getSecuredRoutesForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUserIsLogged(HttpServletRequest request, String username){
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(username::equals)){
            return true;
        }
        loggedUsers.add(username);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    public static void logoutUser(HttpSession session){
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext()
                .getAttribute("loggedUsers");
        User user = AppUtils.getLoginedUser(session);
        if(loggedUsers != null && user != null){
            loggedUsers.remove(user.getUsername());
            session.setAttribute("loggedUsers", loggedUsers);
        }
    }

    public static String getHashedPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
