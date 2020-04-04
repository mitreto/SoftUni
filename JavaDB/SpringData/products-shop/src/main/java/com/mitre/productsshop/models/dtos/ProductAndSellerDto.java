package com.mitre.productsshop.models.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductAndSellerDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private String sellerFullName;

    @Expose
    private UserSeedDto seller;

    public ProductAndSellerDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public void setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
    }

    public UserSeedDto getSeller() {
        return seller;
    }

    public void setSeller(UserSeedDto seller) {
        this.seller = seller;
    }
}
