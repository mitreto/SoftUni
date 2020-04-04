package com.softuni.jsonproductshop;

import com.google.gson.Gson;

import com.softuni.jsonproductshop.constants.GlobalConstants;
import com.softuni.jsonproductshop.models.dtos.CategorySeedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.softuni.jsonproductshop.constants.GlobalConstants.*;

@Component
public class AppRunner implements CommandLineRunner {

    private final Gson gson;

    @Autowired
    public AppRunner(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {

        this.seedCategories();
    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] dtos = this.gson
                .fromJson(new FileReader(CATEGORIES_FILE_PATH), CategorySeedDto[].class);


    }
}
