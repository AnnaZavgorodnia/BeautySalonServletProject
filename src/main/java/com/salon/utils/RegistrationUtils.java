package com.salon.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *  This util checks registration data for validity
 * @author Anna Zavhorodnia
 * @since 1.0
 */
public class RegistrationUtils {

    /**
     * Regular expressions for checking fields
     */
    private static String USERNAME_REGEX = "^[a-zA-Z0-9._-]{4,}$";
    private static String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$";
    private static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";
    private static String NAME_REGEX = "[A-Z][a-z]*";
    private static String NAME_UA_REGEX = "[А-ЩЬЮЯЇІЄҐ][а-щьюяїієґ]*";

    /**
     * Static method that checks for validity fields of User entity
     * {@link com.salon.model.entity.User}
     *
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param username - username to check
     * @param firstName - first name to check
     * @param lastName - last name to check
     * @param email - email to check
     * @param password - password to check
     *
     * @return {@code true} if input parameters are valid
     *         {@code false} otherwise
     *
     */
    public static boolean checkIfValidUser(
            HttpServletRequest request, String username,
            String firstName, String lastName,
            String email, String password) {

        return checkUsername(request, username) & checkEmail(request,email)
                & checkPassword(request, password) & checkFirstName(request, firstName)
                & checkLastName(request, lastName);
    }

    /**
     * Static method that checks for validity fields of Master entity
     * {@link com.salon.model.entity.Master}
     *
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param username - username to check
     * @param firstNameEn - first name in english to check
     * @param lastNameEn - last name in english to check
     * @param email - email to check
     * @param password - password to check
     * @param instagram - instagram link to check
     * @param firstNameUa - first name in ukrainian to check
     * @param lastNameUa - last name in ukrainian to check
     *
     * @return {@code true} if input parameters are valid
     *         {@code false} otherwise
     *
     */
    public static boolean checkIfValidMaster(
            HttpServletRequest request, String username,
            String firstNameEn, String lastNameEn,
            String email, String password, String instagram,
            String firstNameUa, String lastNameUa){

        return checkUsername(request, username) & checkEmail(request,email)
                & checkPassword(request, password) & checkFirstNameEn(request, firstNameEn)
                & checkLastNameEn(request, lastNameEn) & checkInstagram(request,instagram)
                & checkFirstNameUa(request, firstNameUa) & checkLastNameUa(request, lastNameUa);
    }

    /**
     * Private static method helper that checks username parameter
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param username - username to check
     *
     * @return {@code true} if username parameter is valid
     *         {@code false} otherwise
     */
    private static boolean checkUsername(HttpServletRequest request, String username){
        if(!username.matches(USERNAME_REGEX)){
            request.setAttribute("usernameError"," ");
            return false;
        }
        return true;
    }

    /**
     * Private static method helper that checks email parameter
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param email - email to check
     *
     * @return {@code true} if email parameter is valid
     *         {@code false} otherwise
     */
    private static boolean checkEmail(HttpServletRequest request, String email){
        if(!email.matches(EMAIL_REGEX)){
            request.setAttribute("emailError"," ");
            return false;
        }
        return true;
    }

    /**
     * Private static method helper that checks password parameter
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param password - password to check
     *
     * @return {@code true} if password parameter is valid
     *         {@code false} otherwise
     */
    private static boolean checkPassword(HttpServletRequest request, String password){
        if(!password.matches(PASSWORD_REGEX)){
            request.setAttribute("passwordError"," ");
            return false;
        }
        return true;
    }

    /**
     * Private static method helper that checks firstName parameter
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param firstName - first name to check
     *
     * @return {@code true} if firstName parameter is valid
     *         {@code false} otherwise
     */
    private static boolean checkFirstName(HttpServletRequest request, String firstName){
        if(firstName.trim().isEmpty()){
            request.setAttribute("firstNameError"," ");
            return false;
        }
        return true;
    }

    /**
     * Private static method helper that checks lastName parameter
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param lastName - last name to check
     *
     * @return {@code true} if lastName parameter is valid
     *         {@code false} otherwise
     */
    private static boolean checkLastName(HttpServletRequest request, String lastName){
        if(lastName.trim().isEmpty()){
            request.setAttribute("lastNameError"," ");
            return false;
        }
        return true;
    }

    /**
     * Private static method helper that checks instagram parameter
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param instagram - instagram link to check
     *
     * @return {@code true} if instagram parameter is valid
     *         {@code false} otherwise
     */
    private static boolean checkInstagram(HttpServletRequest request, String instagram){
        if(instagram.trim().isEmpty()){
            request.setAttribute("instagramError"," ");
            return false;
        }
        return true;
    }

    /**
     * Private static method helper that checks firstName in english parameter
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param firstName - first name in english to check
     *
     * @return {@code true} if firstName parameter is valid
     *         {@code false} otherwise
     */
    private static boolean checkFirstNameEn(HttpServletRequest request, String firstName){
        if(firstName.trim().isEmpty() || !firstName.matches(NAME_REGEX)){
            request.setAttribute("firstNameError"," ");
            return false;
        }
        return true;
    }

    /**
     * Private static method helper that checks lastName in english parameter
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param lastName - last name in english to check
     *
     * @return {@code true} if lastName parameter is valid
     *         {@code false} otherwise
     */
    private static boolean checkLastNameEn(HttpServletRequest request, String lastName){
        if(lastName.trim().isEmpty() || !lastName.matches(NAME_REGEX)){
            request.setAttribute("lastNameError"," ");
            return false;
        }
        return true;
    }

    /**
     * Private static method helper that checks firstName in ukrainian parameter
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param firstNameUa - first name in ukrainian to check
     *
     * @return {@code true} if firstName parameter is valid
     *         {@code false} otherwise
     */
    private static boolean checkFirstNameUa(HttpServletRequest request, String firstNameUa){
        if(firstNameUa.trim().isEmpty() || !firstNameUa.matches(NAME_UA_REGEX)){
            request.setAttribute("firstNameUaError"," ");
            return false;
        }
        return true;
    }

    /**
     * Private static method helper that checks lastName in ukrainian parameter
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param lastNameUa - last name in ukrainian to check
     *
     * @return {@code true} if lastName parameter is valid
     *         {@code false} otherwise
     */
    private static boolean checkLastNameUa(HttpServletRequest request, String lastNameUa){
        if(lastNameUa.trim().isEmpty() || !lastNameUa.matches(NAME_UA_REGEX)){
            request.setAttribute("lastNameUaError"," ");
            return false;
        }
        return true;
    }

    /**
     * Static method that sets request attributes for passed fields of User entity
     * {@link com.salon.model.entity.User}
     *
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param username - username value
     * @param firstName - first name value
     * @param lastName - last name value
     * @param email - email value
     * @param password - password value
     *
     */
    public static void setUserAttributes(
            HttpServletRequest request, String username, String firstName,
            String lastName, String email, String password){
        request.setAttribute("username", username);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
    }

    /**
     * Static method that sets request attributes for passed fields of Master entity
     * {@link com.salon.model.entity.Master}
     *
     * @param request - HttpServletRequest
     * @see HttpServletRequest
     * @param username - username value
     * @param firstName - first name in english value
     * @param lastName - last name in english value
     * @param email - email value
     * @param password - password value
     * @param firstNameUa - first name in ukrainian value
     * @param lastNameUa - last name in ukrainian value
     */
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
