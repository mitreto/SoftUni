package com.mitre.productsshop.services.impls;

import com.mitre.productsshop.models.dtos.UserSeedDto;
import com.mitre.productsshop.models.entities.User;
import com.mitre.productsshop.repositories.UserRepository;
import com.mitre.productsshop.services.UserService;
import com.mitre.productsshop.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {

        Arrays.stream(userSeedDtos)
                .forEach(userSeedDto -> {
                    if (this.validationUtil.isValid(userSeedDto)){

                        User user = this.modelMapper
                                .map(userSeedDto, User.class);

                        if (this.userRepository.findByLastName(user.getLastName()) == null){

                            this.userRepository.saveAndFlush(user);
                        }

                    } else {
                        this.validationUtil.getViolations(userSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();

        long randomId = random
                .nextInt((int) this.userRepository.count()) + 1;

        return this.userRepository.getOne(randomId);
    }
}
