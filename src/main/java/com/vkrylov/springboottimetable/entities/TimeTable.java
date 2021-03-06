package com.vkrylov.springboottimetable.entities;

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
    @ToString.Exclude
    private List<Attribute> attributes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="time_table_id")
    @ToString.Exclude
    private List<Order> orders;

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
