package com.vkrylov.springboottimetable.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "orders",  schema = "public")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="author_id")
    private int author_id;

    @Column(name="author_name")
    private String author_name;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "status")
    private String status;

    @Column(name="time_table_id")
    private int timeTableId;

    public Order(){}

    public Order(int author_id, String author_name, String startDate, String endDate, String status, int timeTableId) {
        this.author_id = author_id;
        this.author_name = author_name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.timeTableId = timeTableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && author_id == order.author_id && timeTableId == order.timeTableId && author_name.equals(order.author_name) && startDate.equals(order.startDate) && endDate.equals(order.endDate) && status.equals(order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author_id, author_name, startDate, endDate, status, timeTableId);
    }
}
