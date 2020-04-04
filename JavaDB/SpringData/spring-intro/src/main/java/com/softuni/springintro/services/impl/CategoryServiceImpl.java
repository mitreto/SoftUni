package com.softuni.springintro.services.impl;

import com.softuni.springintro.entities.Category;
import com.softuni.springintro.repositories.CategoryRepository;
import com.softuni.springintro.services.CategoryService;
import com.softuni.springintro.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static com.softuni.springintro.constants.GlobalConstants.*;


@Service
public class CategoryServiceImpl implements CategoryService {


   private final CategoryRepository categoryRepository;
   private final FileUtil fileUtil;


   @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {

        if (this.categoryRepository.count() != 0){
            return;
        }

        List<String> fileContent = this.fileUtil.
                readFileContent(CATEGORIES_FILE_PATH);


        fileContent
                .forEach(r ->{
                    Category category = new Category(r);

                    this.categoryRepository.saveAndFlush(category);
                });

    }

    @Override
    public Category getCategoryById(Long id) {
      return this.categoryRepository.getOne(id);
    }

    @Override
    public Set<Category> getRandomCategories() {

        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3) + 1;

        for (int i = 1; i <= bound; i++) {
            int categoryId = random.nextInt(8) + 1;
            result.add(this.getCategoryById((long) categoryId));
        }

        return result;
    }
}
