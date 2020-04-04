package com.softuni.jsonproductshop.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;
    private User buyer;
    private User seller;
    private Set<Category> categories;

    public Product() {
    }

    @Length(min = 3, message = "Name should be at least 3 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    @DecimalMin(value = "0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(fetch = EAGER)
    @NotNull(message = "Seller cannot be null")
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public User getSeller() {
        return seller;
    }

    public void setSeller(User sellerId) {
        this.seller = sellerId;
    }

    @ManyToOne(fetch = EAGER, cascade = PERSIST)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyerId) {
        this.buyer = buyerId;
    }

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
