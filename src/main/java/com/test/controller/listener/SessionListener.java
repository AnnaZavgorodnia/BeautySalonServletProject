package com.test.controller.listener;

import com.test.model.entity.User;
import com.test.utils.AppUtils;
import com.test.utils.SecurityUtils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        SecurityUtils.logoutUser(httpSessionEvent.getSession());
    }
}