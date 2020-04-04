package com.softuni.gamestore.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private User buyer;
    private Set<Game> orderedGames;

    public Order() {
    }

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @Column(name = "ordered_games")
    @ManyToMany
    public Set<Game> getOrderedGames() {
        return orderedGames;
    }

    public void setOrderedGames(Set<Game> products) {
        this.orderedGames = products;
    }
}
