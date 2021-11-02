package com.vkrylov.springboottimetable.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    ROLE_USER(Set.of(Permission.USER_POST, Permission.ORDER_DELETE, Permission.ORDER_POST,
            Permission.NOTIFICATION_POST, Permission.NOTIFICATION_DELETE)),

    ROLE_ADMIN(Set.of(Permission.USER_POST, Permission.USER_DELETE, Permission.TIMETABLE_POST,
            Permission.TIMETABLE_DELETE, Permission.ORDER_DELETE, Permission.ORDER_POST,
            Permission.NOTIFICATION_POST, Permission.NOTIFICATION_DELETE));

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
