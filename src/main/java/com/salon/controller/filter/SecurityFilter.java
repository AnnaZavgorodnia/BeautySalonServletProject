package com.salon.controller.filter;

import com.salon.model.entity.User;
import com.salon.utils.AppUtils;
import com.salon.utils.SecurityUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    private static final Logger logger = Logger.getLogger(SecurityFilter.class);

    public SecurityFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getRequestURI().replaceAll(".*app/","");

        if (path.equals("login")) {
            chain.doFilter(request, response);
            return;
        }

        User loginedUser = AppUtils.getLoginedUser(request.getSession());

        if (SecurityUtils.isSecurityPage(path)) {

            if (loginedUser == null) {
                response.sendRedirect(request.getContextPath() + "/app/login");
                return;
            }

            if (!SecurityUtils.hasPermission(path, loginedUser.getRole())) {

                logger.info("access denied to user " + loginedUser.getUsername());

                RequestDispatcher dispatcher
                        = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
