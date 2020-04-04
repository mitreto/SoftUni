package com.mitre.productsshop.services;

import com.mitre.productsshop.models.dtos.CategorySeedDto;
import com.mitre.productsshop.models.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);

    Set<Category> getRandomCategories();
}
