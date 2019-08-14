package com.test.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {

    private static final Logger logger = Logger.getLogger(SecurityFilter.class);

    private static final String DEFAULT_LANGUAGE = "en";
    private static final String MESSAGES_BUNDLE = "messages";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        logger.info("setting the locale");

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter("lang") != null) {
            req.getSession().setAttribute("lang", req.getParameter("lang"));
        } else {
            if (req.getSession().getAttribute("lang") == null)
                req.getSession().setAttribute("lang", DEFAULT_LANGUAGE);
        }
        req.getSession().setAttribute("messages_bundle", MESSAGES_BUNDLE);
        chain.doFilter(request, response);
    }

    public void destroy() {}
    public void init(FilterConfig arg0) throws ServletException {}
}