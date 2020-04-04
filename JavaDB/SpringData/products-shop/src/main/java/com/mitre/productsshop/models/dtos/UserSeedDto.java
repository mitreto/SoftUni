package com.mitre.productsshop.models.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserSeedDto {

    @Expose
    private String firstName;

    @Expose
    @Length(min = 3, message = "Name must be at least 3 characters")
    @NotNull
    private String lastName;

    @Expose
    private Integer age;

    public UserSeedDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
