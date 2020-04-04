package com.softuni.springintro.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    private long id;

    public BaseEntity() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
