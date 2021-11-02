package com.vkrylov.springboottimetable.entity;

public enum Permission {
    USER_POST("user:post"),
    ORDER_POST("order:post"),
    TIMETABLE_POST("timetable:post"),
    NOTIFICATION_POST("notification:post"),
    USER_DELETE("user:delete"),
    ORDER_DELETE("order:delete"),
    TIMETABLE_DELETE("timetable:delete"),
    NOTIFICATION_DELETE("notification:delete");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
