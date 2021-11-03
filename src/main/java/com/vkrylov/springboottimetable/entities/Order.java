package com.vkrylov.springboottimetable.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

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
    private List<AttributeValue> attributeValues;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private List<Notification> notifications;


    public Order(){}

    public Order(int authorId, String authorName, ZonedDateTime startDate, ZonedDateTime endDate, OrderStatus status, int timeTableId, List<AttributeValue> attributeValues, List<Notification> notifications) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.timeTableId = timeTableId;
        this.attributeValues = attributeValues;
        this.notifications = notifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public List<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

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
