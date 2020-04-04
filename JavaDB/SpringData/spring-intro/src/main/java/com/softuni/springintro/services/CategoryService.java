package com.softuni.springintro.services;

import com.softuni.springintro.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService  {

    void seedCategories() throws IOException;

    Category getCategoryById(Long id);

    Set<Category> getRandomCategories();
}
