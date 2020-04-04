package com.softuni.gamestore.services;

import com.softuni.gamestore.domain.dtos.GameAddDto;

public interface GameService {

    void addGame(GameAddDto gameAddDto);
}
