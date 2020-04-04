package com.softuni.usersystem.entites;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    private String name;
    private String country;
    private Set<User> nativeUser;
    private Set<User> foreignUser;


    public Town() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @OneToMany(mappedBy = "bornTown", targetEntity = User.class)
    public Set<User> getNativeUser() {
        return nativeUser;
    }

    public void setNativeUser(Set<User> nativeUser) {
        this.nativeUser = nativeUser;
    }

    @OneToMany(mappedBy = "livingInTown", targetEntity = User.class)
    public Set<User> getForeignUser() {
        return foreignUser;
    }

    public void setForeignUser(Set<User> foreignUser) {
        this.foreignUser = foreignUser;
    }
}
