package com.softuni.usersystem.controllers;

import com.softuni.usersystem.entites.Album;
import com.softuni.usersystem.entites.Picture;
import com.softuni.usersystem.entites.User;
import com.softuni.usersystem.services.AlbumService;
import com.softuni.usersystem.services.PictureService;
import com.softuni.usersystem.services.TownService;
import com.softuni.usersystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {

    private final UserService userService;
    private final TownService townService;
    private final PictureService pictureService;
    private final AlbumService albumService;


    @Autowired
    public AppController(UserService userService, TownService townService, PictureService pictureService, AlbumService albumService) {
        this.userService = userService;
        this.townService = townService;
        this.pictureService = pictureService;
        this.albumService = albumService;
    }


    @Override
    public void run(String... args) throws Exception {

//        userService.createUser();
//        userService.createUser();
//        userService.createUser();
//        userService.createUser();
//        userService.createUser();
//        userService.createUser();
        userService.createUser();

//        userService.getUsersByEmailProvider();
//        userService.getUsersByEmailProvider();
//        userService.getUsersByEmailProvider();

    }
}
