package com.vkrylov.springboottimetable.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "notifications",  schema = "public")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="order_id")
    private int orderId;

    @Column(name="user_id")
    private int userId;

    @Column(name="type")
    private String type;

    @Column(name="is_read")
    private boolean isRead;

    public Notification(){}

    public Notification(int orderId, int userId, String type, boolean isRead) {
        this.orderId = orderId;
        this.userId = userId;
        this.type = type;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id == that.id && orderId == that.orderId && userId == that.userId && isRead == that.isRead && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, userId, type, isRead);
    }
}
