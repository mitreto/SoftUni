package com.softuni.usersystem.services.impl;

import com.softuni.usersystem.repositories.TownRepository;
import com.softuni.usersystem.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }
}
