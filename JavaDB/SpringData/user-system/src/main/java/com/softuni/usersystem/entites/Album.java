package com.softuni.usersystem.entites;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album extends BaseEntity {

    private String name;
    private String color;
    private boolean isPublic;
    private Set<Picture> pictures;
    private User user;

    public Album() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @ManyToMany
    @JoinTable(name = "albums_pictures",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"))
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
