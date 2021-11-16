package com.vkrylov.springboottimetable.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
