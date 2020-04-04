package com.softuni.usersystem.entites;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    private String id;

    //private Long id;
    public BaseEntity() {
    }

    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//}

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

