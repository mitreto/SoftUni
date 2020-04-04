package com.mitre.productsshop.services;

import com.mitre.productsshop.models.dtos.UserSeedDto;
import com.mitre.productsshop.models.entities.User;

public interface UserService {

    void seedUsers(UserSeedDto[] userSeedDtos);

    User getRandomUser();
}
