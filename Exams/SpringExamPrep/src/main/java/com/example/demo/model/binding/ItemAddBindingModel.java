package com.example.demo.model.binding;

import com.example.demo.model.entity.CategoryName;
import com.example.demo.model.entity.Gender;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemAddBindingModel {

    private String name;
    private String description;
    private CategoryName category;
    private Gender gender;
    private BigDecimal price;

    public ItemAddBindingModel() {
    }

    @Length(min = 2, message = "Username must be more than two characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 2, message = "Description must be more than three characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Enter valid category!")
    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    @NotNull(message = "Enter valid gender!")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @DecimalMin(value = "0", message = "Enter valid price!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
