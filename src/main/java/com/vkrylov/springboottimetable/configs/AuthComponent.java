package com.vkrylov.springboottimetable.configs;

import com.vkrylov.springboottimetable.services.SecurityUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthComponent {
    public boolean hasPermission(Authentication auth, int id) {
        if(auth instanceof AnonymousAuthenticationToken)
            return false;

        SecurityUser securityUser = (SecurityUser)auth.getPrincipal();
        return id == securityUser.getId();
    }
}
