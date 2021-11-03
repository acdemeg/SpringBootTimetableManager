package com.vkrylov.springboottimetable.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name= "time_tables",  schema = "public")
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="start_date")
    private String startDate;

    @Column(name="end_date")
    private String endDate;

    @Column(name="slot_size")
    private String slotSize;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="time_table_id", referencedColumnName = "id", nullable = false)
    private List<Attribute> attributes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="time_table_id")
    private List<Order> orders;

    public TimeTable(){}

    public TimeTable(String title, String startDate, String endDate, String slotSize) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.slotSize = slotSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSlotSize() {
        return slotSize;
    }

    public void setSlotSize(String slotSize) {
        this.slotSize = slotSize;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTable timeTable = (TimeTable) o;
        return id == timeTable.id && title.equals(timeTable.title) && startDate.equals(timeTable.startDate) && endDate.equals(timeTable.endDate) && slotSize.equals(timeTable.slotSize) && Objects.equals(attributes, timeTable.attributes) && Objects.equals(orders, timeTable.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, startDate, endDate, slotSize, attributes, orders);
    }
}
