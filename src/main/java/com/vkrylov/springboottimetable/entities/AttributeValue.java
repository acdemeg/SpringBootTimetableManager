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
