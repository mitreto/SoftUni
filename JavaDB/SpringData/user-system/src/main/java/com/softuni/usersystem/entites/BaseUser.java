package com.softuni.usersystem.entites;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseUser extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private LocalDateTime registeredOn;
    private LocalDateTime lastTimeLoggedIn;
    private int age;
    private boolean isDeleted;

    public BaseUser() {
    }


    @Size(min = 4, max = 30, message = "Invalid username")
    @Column(name = "user_name", length = 30, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Size(min = 6, max = 50, message = "Password not valid!")
    @Column(name = "password")

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "registered_on")
    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registered_on) {
        this.registeredOn = registered_on;
    }

    @Column(name = "last_time_logged_in")
    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime last_time_logged_in) {
        this.lastTimeLoggedIn = last_time_logged_in;
    }

    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "is_deleted")
    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean is_deleted) {
        this.isDeleted = is_deleted;
    }

}
