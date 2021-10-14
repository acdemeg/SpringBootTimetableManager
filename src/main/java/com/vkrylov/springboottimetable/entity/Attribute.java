package com.vkrylov.springboottimetable.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "attributes",  schema = "public")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="type_attr")
    @JsonProperty("type_attr")
    private String typeAttr;

    @Column(name="is_required")
    private boolean isRequired;

    @Column(name="time_table_id", insertable = false, updatable = false)
    private int timeTableId;

    public Attribute() {}

    public Attribute(String title, String typeAttr, boolean isRequired, int timeTableId) {
        this.title = title;
        this.typeAttr = typeAttr;
        this.isRequired = isRequired;
        this.timeTableId = timeTableId;
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

    public String getTypeAttr() {
        return typeAttr;
    }

    public void setTypeAttr(String typeAttr) {
        this.typeAttr = typeAttr;
    }

    public boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public int getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return id == attribute.id && isRequired == attribute.isRequired && timeTableId == attribute.timeTableId && title.equals(attribute.title) && typeAttr.equals(attribute.typeAttr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, typeAttr, isRequired, timeTableId);
    }

}
