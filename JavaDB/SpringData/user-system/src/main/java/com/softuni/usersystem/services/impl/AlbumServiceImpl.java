package com.softuni.usersystem.services.impl;

import com.softuni.usersystem.repositories.AlbumRepository;
import com.softuni.usersystem.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
}
