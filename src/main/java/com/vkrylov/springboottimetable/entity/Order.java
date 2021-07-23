package com.vkrylov.springboottimetable.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name= "orders",  schema = "public")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="author_id")
    private int authorId;

    @Column(name="author_name")
    private String authorName;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "status")
    private String status;

    @Column(name="time_table_id")
    private int timeTableId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private List<AttributeValue> attributeValues;

    public Order(){}

    public Order(int authorId, String authorName, String startDate, String endDate,
                 String status, int timeTableId, List<AttributeValue> attributeValues) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.timeTableId = timeTableId;
        this.attributeValues = attributeValues;
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

    public List<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && authorId == order.authorId && timeTableId == order.timeTableId && authorName.equals(order.authorName) && startDate.equals(order.startDate) && endDate.equals(order.endDate) && status.equals(order.status) && Objects.equals(attributeValues, order.attributeValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, authorName, startDate, endDate, status, timeTableId, attributeValues);
    }
}
