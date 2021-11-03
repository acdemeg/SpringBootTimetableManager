package com.vkrylov.springboottimetable.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "attribute_values",  schema = "public")
public class AttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="attribute_id")
    private int attributeId;

    @Column(name="order_id", insertable = false, updatable = false)
    private int orderId;

    @Column(name="time_table_id")
    private int timeTableId;

    @Column(name="value")
    private String value;

    public AttributeValue(){}

    public AttributeValue(int attributeId, int orderId, int timeTableId, String value) {
        this.attributeId = attributeId;
        this.orderId = orderId;
        this.timeTableId = timeTableId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeValue that = (AttributeValue) o;
        return id == that.id && attributeId == that.attributeId && orderId == that.orderId && timeTableId == that.timeTableId && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attributeId, orderId, timeTableId, value);
    }
}
