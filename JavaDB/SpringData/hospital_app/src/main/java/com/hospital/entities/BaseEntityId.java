package com.hospital.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntityId {

    private String id;


    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
