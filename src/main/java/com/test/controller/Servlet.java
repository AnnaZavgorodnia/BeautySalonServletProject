package com.test.controller;

import com.sun.corba.se.pept.protocol.ClientDelegate;
import com.test.controller.command.*;
import com.test.model.service.AppointmentService;
import com.test.model.service.MasterService;
import com.test.model.service.SalonServicesService;
import com.test.model.service.UserService;
import com.test.utils.SecurityUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet({ "/app/*" })
@MultipartConfig
public class Servlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Servlet.class);

    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig){

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("users",
                new AllUsersCommand(new UserService()));
        commands.put("exception" , new ExceptionCommand());
        commands.put("home" , new HomeCommand());
        commands.put("login" , new LoginCommand(new UserService()));
        commands.put("logout" , new LogoutCommand());
        commands.put("masters", new AllMastersCommand(new MasterService()));
        commands.put("create_app", new CreateAppointmentPageCommand(new MasterService()));
        commands.put("me/appointments", new AllUsersAppointmentsCommand(new AppointmentService()));
        commands.put("all_appointments", new AllAppointmentsPageCommand());
        commands.put("api/all_appointments", new AllAppointmentsApiCommand(new AppointmentService()));
        commands.put("registration", new RegistrationCommand(new UserService()));
        commands.put("api/appointments", new MasterAppointmentsApiCommand(new AppointmentService()));
        commands.put("api/create_appointment", new CreateAppointmentCommand(new AppointmentService()));
        commands.put("add_master", new CreateMasterCommand(new MasterService(), new SalonServicesService()));
        commands.put("me/appointments/delete", new DeleteClientAppointment(new AppointmentService()));
        commands.put("all_masters", new AllMastersAdminCommand(new MasterService()));
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*app/", "");
        Command command = commands.getOrDefault(path, new HomeCommand());
        String page = command.execute(request);
        if(path.contains("api")) {
            response.getWriter().write(page);
        } else if (page.contains("redirect")) {
            logger.info("redirecting to -> " + page);
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            logger.info("forwarding to -> " + page);
            request.getRequestDispatcher(request.getContextPath() + page).forward(request, response);
        }
    }
}
