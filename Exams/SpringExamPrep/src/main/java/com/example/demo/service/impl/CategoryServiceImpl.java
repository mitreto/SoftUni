package com.example.demo.service.impl;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.CategoryName;
import com.example.demo.model.service.CategoryServiceModel;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0){
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> this.categoryRepository.save(new Category(categoryName,
                            String.format("Description for %s", categoryName.name()))));
        }
    }

    @Override
    public CategoryServiceModel findByCategoryName(CategoryName categoryName) {

        return this.categoryRepository
                .findByCategoryName(categoryName)
                .map(category -> this.modelMapper.map(category, CategoryServiceModel.class))
                .orElse(null);
    }

    @Override
    public Category find(CategoryName categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName).orElse(null);
    }
}
