package com.malhar.adv_hibernate_demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue
    private Integer id;

    private String comment;

    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return STR."Review{id=\{id}, comment='\{comment}\{'\''}\{'}'}";
    }
}
