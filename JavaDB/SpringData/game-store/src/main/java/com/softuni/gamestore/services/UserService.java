package com.softuni.gamestore.services;

import com.softuni.gamestore.domain.dtos.UserLoginDto;
import com.softuni.gamestore.domain.dtos.UserRegisterDto;

public interface UserService {

    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logoutUser();

    boolean isLoggedUserAdmin();
}
