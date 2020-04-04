package com.softuni.usersystem.entites;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseUser {

    private String firstName;
    private String lastName;
    private Town bornTown;
    private Town livingInTown;
    private Set<User> friends;
    private Set<Album> albums;

    public User(){
    }

    public User(String email){
        super.setEmail(email);
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToOne
    @JoinColumn(name = "born_town_id", referencedColumnName = "id")
    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    @ManyToOne
    @JoinColumn(name = "living_in_town_id", referencedColumnName = "id")
    public Town getLivingInTown() {
        return livingInTown;
    }

    public void setLivingInTown(Town livingInTown) {
        this.livingInTown = livingInTown;
    }

    @ManyToMany
//    @JoinTable(name = "users_friends",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "user", targetEntity = Album.class)
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
