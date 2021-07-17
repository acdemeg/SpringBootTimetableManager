package com.vkrylov.springboottimetable.entity;

import javax.persistence.*;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTable timeTable = (TimeTable) o;
        return id == timeTable.id && title.equals(timeTable.title) && startDate.equals(timeTable.startDate) && endDate.equals(timeTable.endDate) && slotSize.equals(timeTable.slotSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, startDate, endDate, slotSize);
    }
}
