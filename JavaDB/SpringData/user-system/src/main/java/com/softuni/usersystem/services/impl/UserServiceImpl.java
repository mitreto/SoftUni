package com.softuni.usersystem.services.impl;

import com.softuni.usersystem.entites.User;
import com.softuni.usersystem.repositories.UserRepository;
import com.softuni.usersystem.services.UserService;
import com.softuni.usersystem.utils.ConsoleReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConsoleReader consoleReader;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ConsoleReader consoleReader) {
        this.userRepository = userRepository;
        this.consoleReader = consoleReader;
    }


    @Override
    public void createUser() throws IOException {

        System.out.println("Enter user email address: ");

        String email = consoleReader.readLine();
        User user = new User(email);
        userRepository.saveAndFlush(user);

    }

    @Override
    public void getUsersByEmailProvider() throws IOException {

        System.out.println("Enter email provider:");

       String emailProvider = "@" + this.consoleReader.readLine();

        Set<User> usersMatch = userRepository.getAllByEmailEndingWith(emailProvider);

        if (usersMatch.isEmpty()) {
            System.out.println("No users found with this email provider");
        } else {
            usersMatch.forEach(u -> System.out.printf("%s %s%n",
                    u.getUsername(),
                    u.getEmail()));
        }
    }
}
