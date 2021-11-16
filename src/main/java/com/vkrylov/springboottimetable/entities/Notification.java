package com.vkrylov.springboottimetable.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "notifications",  schema = "public")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="order_id", insertable = false, updatable = false)
    private int orderId;

    @Column(name="user_id")
    private int userId;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name="is_read")
    private boolean isRead;

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
