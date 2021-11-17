package com.vkrylov.springboottimetable.configs;

import com.vkrylov.springboottimetable.services.SecurityUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthComponent {

    public boolean hasPermission(Authentication auth, int id) {
        Integer authID = this.getIdOfAuthUser(auth);
        if(authID == null)
            return false;
        return id == authID;
    }

    public Integer getIdOfAuthUser(Authentication auth){
        if(auth == null || auth instanceof AnonymousAuthenticationToken)
            return null;
        SecurityUser securityUser = (SecurityUser)auth.getPrincipal();
        return securityUser.getId();
    }
}
