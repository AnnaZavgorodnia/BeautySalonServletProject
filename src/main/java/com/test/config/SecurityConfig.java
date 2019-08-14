package com.test.config;

import com.test.model.entity.Role;
import org.apache.log4j.Logger;

import java.util.*;

public class SecurityConfig {

    private static final Logger logger = Logger.getLogger(SecurityConfig.class);
    private static final Map<Role, List<String>> securedRoutes = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        logger.info("initializing sercurity paths");

        securedRoutes.put(Role.CLIENT, Arrays.asList(
                "masters",
                "me/appointments",
                "create_appointment",
                "create_app",
                "api/appointments",
                "me/appointments/delete"));

        securedRoutes.put(Role.ADMIN, Arrays.asList(
                "all_appointments",
                "api/appointments",
                "create_master",
                "add_master",
                "all_masters"));

        securedRoutes.put(Role.MASTER, Arrays.asList(
                "all_appointments",
                "api/appointments"));
    }

    public static List<String> getSecuredRoutesForRole(Role role){
        return securedRoutes.get(role);
    }

    public static Set<Role> getAllAppRoles() {
        return securedRoutes.keySet();
    }
}
