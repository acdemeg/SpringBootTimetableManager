package com.vkrylov.springboottimetable.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "sessions",  schema = "public")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="sess")
    private String sess;

    public Session(){}

    public Session(String sess) {
        this.sess = sess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSess() {
        return sess;
    }

    public void setSess(String sess) {
        this.sess = sess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id && sess.equals(session.sess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sess);
    }
}
