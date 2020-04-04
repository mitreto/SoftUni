package com.softuni.gamestore.domain.dtos;

public class UserLoginDto {
    private String email;
    private String password;

    public UserLoginDto() {
    }

    public String getEmail() {
        return email;
    }

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
