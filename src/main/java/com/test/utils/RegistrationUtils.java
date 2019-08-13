package com.test.utils;

import javax.servlet.http.HttpServletRequest;

public class RegistrationUtils {


    private static String USERNAME_REGEX = "^[a-zA-Z0-9._-]{4,}$";
    private static String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$";
    private static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";
    private static String FIRST_NAME_REGEX = "[A-Z][a-z]*";
    private static String LAST_NAME_REGEX = "[A-Z][a-z]*";
    private static String FIRST_NAME_UA_REGEX = "[А-ЩЬЮЯЇІЄҐ][а-щьюяїієґ]*";
    private static String LAST_NAME_UA_REGEX = "[А-ЩЬЮЯЇІЄҐ][а-щьюяїієґ']*";

    public static boolean checkIfValidUser(
            HttpServletRequest request, String username,
            String firstName, String lastName,
            String email, String password) {

        return checkUsername(request, username) & checkEmail(request,email)
                & checkPassword(request, password) & checkFirstName(request, firstName)
                & checkLastName(request, lastName);
    }

    public static boolean checkIfValidMaster(
            HttpServletRequest request, String username,
            String firstName, String lastName,
            String email, String password, String instagram,
            String firstNameUa, String lastNameUa){

        return checkUsername(request, username) & checkEmail(request,email)
                & checkPassword(request, password) & checkFirstNameEn(request, firstName)
                & checkLastNameEn(request, lastName) & checkInstagram(request,instagram)
                & checkFirstNameUa(request, firstNameUa) & checkLastNameUa(request, lastNameUa);
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

    private static boolean checkFirstNameEn(HttpServletRequest request, String firstName){
        if(firstName.trim().isEmpty() || !firstName.matches(FIRST_NAME_REGEX)){
            request.setAttribute("firstNameError"," ");
            return false;
        }
        return true;
    }

    private static boolean checkLastNameEn(HttpServletRequest request, String lastName){
        if(lastName.trim().isEmpty() || !lastName.matches(LAST_NAME_REGEX)){
            request.setAttribute("lastNameError"," ");
            return false;
        }
        return true;
    }

    private static boolean checkFirstNameUa(HttpServletRequest request, String firstNameUa){
        if(firstNameUa.trim().isEmpty() || !firstNameUa.matches(FIRST_NAME_UA_REGEX)){
            request.setAttribute("firstNameUaError"," ");
            return false;
        }
        return true;
    }

    private static boolean checkLastNameUa(HttpServletRequest request, String lastNameUa){
        if(lastNameUa.trim().isEmpty() || !lastNameUa.matches(LAST_NAME_UA_REGEX)){
            request.setAttribute("lastNameUaError"," ");
            return false;
        }
        return true;
    }

    public static void setUserAttributes(
            HttpServletRequest request, String username, String firstName,
            String lastName, String email, String password){
        request.setAttribute("username", username);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
    }

    public static void setMasterAttributes(
            HttpServletRequest request, String username, String firstName,
            String lastName, String email, String password, String instagram,
            String firstNameUa, String lastNameUa){
        setUserAttributes(request, username, firstName, lastName, email, password);
        request.setAttribute("instagram", instagram);
        request.setAttribute("firstNameUa", firstNameUa);
        request.setAttribute("lastNameUa", lastNameUa);
    }

}
