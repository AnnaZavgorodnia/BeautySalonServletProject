package com.salon.utils;

import com.salon.model.entity.User;

import javax.servlet.http.HttpSession;

public class AppUtils {

    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute("loginedUser");
    }
}
