package com.test.utils;

import javax.servlet.http.HttpServletRequest;

public class RegistrationUtils {


    private static String USERNAME_REGEX = "^[a-zA-Z0-9._-]{4,}$";
    private static String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$";
    private static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

    public static boolean checkIfValid(
            HttpServletRequest request, String username,
            String firstName, String lastName,
            String email, String password, String instagram) {

        boolean inst = true;
        if(instagram != null)
            inst = checkInstagram(request, instagram);

        return (checkUsername(request, username) & checkEmail(request,email)
                & checkPassword(request, password) & checkFirstName(request, firstName)
                & checkLastName(request, lastName) & inst);
    }

    private static boolean checkUsername(HttpServletRequest request, String username){
        if(!username.matches(USERNAME_REGEX)){
            request.setAttribute("usernameError"," ");
            return false;
        }
        return true;
    }

    private static boolean checkEmail(HttpServletRequest request, String email){
        if(!email.matches(EMAIL_REGEX)){
            request.setAttribute("emailError"," ");
            return false;
        }
        return true;
    }

    private static boolean checkPassword(HttpServletRequest request, String password){
        if(!password.matches(PASSWORD_REGEX)){
            request.setAttribute("passwordError"," ");
            return false;
        }
        return true;
    }

    private static boolean checkFirstName(HttpServletRequest request, String firstName){
        if(firstName.trim().isEmpty()){
            request.setAttribute("firstNameError"," ");
            return false;
        }
        return true;
    }

    private static boolean checkLastName(HttpServletRequest request, String lastName){
        if(lastName.trim().isEmpty()){
            request.setAttribute("lastNameError"," ");
            return false;
        }
        return true;
    }

    private static boolean checkInstagram(HttpServletRequest request, String instagram){
        if(instagram.trim().isEmpty()){
            request.setAttribute("instagramError"," ");
            return false;
        }
        return true;
    }

    public static void setUserAttributes(
            HttpServletRequest request, String username, String firstName,
            String lastName, String email, String password, String instagram){
        request.setAttribute("username", username);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        if(instagram != null)
            request.setAttribute("instagram", instagram);
    }

}
