package com.kang.jpa.hibernate.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Course")
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "select c from Course  c"),
        @NamedQuery(name = "query_get_50_steps_courses", query = "select c from Course c where c.name like '%50 steps'")
})


public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;


    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
