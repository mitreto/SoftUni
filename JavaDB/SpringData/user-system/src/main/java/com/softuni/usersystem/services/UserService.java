package com.softuni.usersystem.services;

import com.softuni.usersystem.entites.User;

import java.io.IOException;
import java.util.Set;

public interface UserService {

    void createUser() throws IOException;

   void getUsersByEmailProvider() throws IOException;
}
