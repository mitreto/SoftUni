package com.example.demo.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryName categoryName;
    private String description;

    public Category() {
    }

    public Category(CategoryName categoryName, String description){
        this.categoryName = categoryName;
        this.description = description;

    }

    @Enumerated(value = EnumType.ORDINAL)
    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public void setDescription(String description) {
        this.description = description;
    }
}
