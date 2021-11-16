package com.vkrylov.springboottimetable.entities;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "orders",  schema = "public")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="author_id", unique = true)
    private int authorId;

    @Column(name="author_name")
    private String authorName;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Column(name = "status", insertable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="time_table_id")
    private int timeTableId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private List<AttributeValue> attributeValues;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    @ToString.Exclude
    private List<Notification> notifications;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && authorId == order.authorId && timeTableId == order.timeTableId && authorName.equals(order.authorName) && startDate.equals(order.startDate) && endDate.equals(order.endDate) && status == order.status && Objects.equals(attributeValues, order.attributeValues) && Objects.equals(notifications, order.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, authorName, startDate, endDate, status, timeTableId, attributeValues, notifications);
    }
}
