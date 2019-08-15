package com.salon.controller.command;

import com.salon.model.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrationCommandTest {

    private RegistrationCommand registrationCommand;
    private HttpServletRequest request;
    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService();
        assertNotNull(userService);

        registrationCommand = new RegistrationCommand(userService);
        assertNotNull(registrationCommand);

        request = mock(HttpServletRequest.class);
        assertNotNull(request);

        when(request.getParameter("username")).thenReturn("userCreatedForTest");
        when(request.getParameter("firstName")).thenReturn("Anna");
        when(request.getParameter("lastName")).thenReturn("Zavhorodnia");
        when(request.getParameter("email")).thenReturn("test000004@gmail.com");
        when(request.getParameter("password")).thenReturn("root1@34A");

        String responsePath = registrationCommand.execute(request);

        assertEquals("redirect:/app/login", responsePath);
    }

    @After
    public void clearUp(){
        userService.deleteByUsername("userCreatedForTest");
    }

    @Test
    public void getRegistrationPage() {
        String responsePath = registrationCommand.execute(request);
        assertEquals("/WEB-INF/views/registrationView.jsp", responsePath);
    }

    @Test
    public void invalidData() {

        when(request.getParameter("username")).thenReturn("client");
        when(request.getParameter("firstName")).thenReturn("Anna");
        when(request.getParameter("lastName")).thenReturn("Zavhorodnia");
        when(request.getParameter("email")).thenReturn("lazygirl5550@gmail");
        when(request.getParameter("password")).thenReturn("root1@34A");

        String responsePath = registrationCommand.execute(request);

        assertEquals("/WEB-INF/views/registrationView.jsp", responsePath);
    }

    @Test
    public void validData(){

        when(request.getParameter("username")).thenReturn("client");
        when(request.getParameter("firstName")).thenReturn("Anna");
        when(request.getParameter("lastName")).thenReturn("Zavhorodnia");
        when(request.getParameter("email")).thenReturn("lazygirl5550@gmail.com");
        when(request.getParameter("password")).thenReturn("root1@34A");

        String responsePath = registrationCommand.execute(request);

        assertEquals("redirect:/app/login", responsePath);

        userService.deleteByUsername("client");
    }

    @Test
    public void duplicateValue(){

        when(request.getParameter("username")).thenReturn("userCreatedForTest");
        when(request.getParameter("firstName")).thenReturn("Anna");
        when(request.getParameter("lastName")).thenReturn("Zavhorodnia");
        when(request.getParameter("email")).thenReturn("test000004@gmail.com");
        when(request.getParameter("password")).thenReturn("root1@34A");

        String responsePath = registrationCommand.execute(request);

        assertEquals("/WEB-INF/views/registrationView.jsp", responsePath);
    }
}
