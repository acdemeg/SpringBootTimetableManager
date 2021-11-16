package com.vkrylov.springboottimetable.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "users",  schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Column(name = "role", insertable = false, updatable = false)
    private String role;

    @Column(name = "image_path", updatable = false)
    private String imagePath;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Notification> notifications;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && email.equals(user.email) && password.equals(user.password) && role.equals(user.role) && Objects.equals(imagePath, user.imagePath) && Objects.equals(orders, user.orders) && Objects.equals(notifications, user.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, role, imagePath, orders, notifications);
    }
}