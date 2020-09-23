package com.example.demo.service;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.CategoryName;
import com.example.demo.model.service.CategoryServiceModel;

public interface CategoryService {

    void initCategories();
    CategoryServiceModel findByCategoryName(CategoryName categoryName);
    Category find(CategoryName categoryName);
}
