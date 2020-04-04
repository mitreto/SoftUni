package com.mitre.productsshop;

import com.google.gson.Gson;
import com.mitre.productsshop.models.dtos.CategorySeedDto;
import com.mitre.productsshop.models.dtos.ProductAndSellerDto;
import com.mitre.productsshop.models.dtos.ProductSeedDto;
import com.mitre.productsshop.models.dtos.UserSeedDto;
import com.mitre.productsshop.services.CategoryService;
import com.mitre.productsshop.services.ProductService;
import com.mitre.productsshop.services.UserService;
import com.mitre.productsshop.utils.FileIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import static com.mitre.productsshop.constants.GlobalConstants.*;

@Component
public class AppRunner implements CommandLineRunner {

    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;
    private final FileIOUtil fileIOUtil;

    @Autowired
    public AppRunner(Gson gson, CategoryService categoryService,
                     UserService userService, ProductService productService,
                     BufferedReader bufferedReader, FileIOUtil fileIOUtil) {

        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.bufferedReader = bufferedReader;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws Exception {

        this.seedCategories();
        this.seedUsers();
        this.seedProducts();

        System.out.println("Enter price from and price to");
        this.writeAllProductsWithPriceInRange(
                new BigDecimal(this.bufferedReader.readLine()),
                new BigDecimal(this.bufferedReader.readLine())
        );
    }

    private void writeAllProductsWithPriceInRange(BigDecimal from, BigDecimal to) throws IOException {

        Set<ProductAndSellerDto> dtos =
                this.productService.getAllProductsWithPriceInRangeAndNoBuyer(from, to);

        String jsonContent = this.gson.toJson(dtos);

        this.fileIOUtil.write(jsonContent, EX1_OUTPUT_FILE_PATH);

    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] dtos = this.gson
                .fromJson(new FileReader(PRODUCTS_FILE_PATH), ProductSeedDto[].class);

        this.productService.seedProducts(dtos);
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[] dtos = this.gson
                .fromJson(new FileReader(USERS_FILE_PATH), UserSeedDto[].class);

        this.userService.seedUsers(dtos);
    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] dtos = this.gson
                .fromJson(new FileReader(CATEGORIES_FILE_PATH), CategorySeedDto[].class);

        this.categoryService.seedCategories(dtos);

    }
}
