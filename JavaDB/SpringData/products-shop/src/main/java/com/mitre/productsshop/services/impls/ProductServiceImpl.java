package com.mitre.productsshop.services.impls;

import com.mitre.productsshop.models.dtos.ProductAndSellerDto;
import com.mitre.productsshop.models.dtos.ProductSeedDto;
import com.mitre.productsshop.models.dtos.UserSeedDto;
import com.mitre.productsshop.models.entities.Product;
import com.mitre.productsshop.repositories.ProductRepository;
import com.mitre.productsshop.services.CategoryService;
import com.mitre.productsshop.services.ProductService;
import com.mitre.productsshop.services.UserService;
import com.mitre.productsshop.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {

        Arrays.stream(productSeedDtos)
                .forEach(productSeedDto -> {

                    if (this.validationUtil.isValid(productSeedDto)) {

                        Product product = this.modelMapper
                                .map(productSeedDto, Product.class);

                        if (this.productRepository.findByName(product.getName()) == null) {

                            product.setSeller(this.userService.getRandomUser());

                            Random random = new Random();
                            int randomNum = random.nextInt(2);
                            if (randomNum == 1){
                                product.setBuyer(this.userService.getRandomUser());
                            }

//                            if (!product.getName().startsWith("P")) {
//                                product.setBuyer(this.userService.getRandomUser());
//                            }

                            product.setCategories(this.categoryService.getRandomCategories());

                            this.productRepository.saveAndFlush(product);
                        }

                    } else {
                        this.validationUtil.getViolations(productSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });


    }

    @Override
    public Set<ProductAndSellerDto> getAllProductsWithPriceInRangeAndNoBuyer(BigDecimal from, BigDecimal to) {
        return this.productRepository
                .findAllByPriceBetweenAndAndBuyerIsNull(from, to)
                .stream()
                .map(p ->{
                    ProductAndSellerDto productAndSellerDto =
                            this.modelMapper.map(p, ProductAndSellerDto.class);

                    productAndSellerDto
                            .setSellerFullName(String.format("%s %s",
                                    p.getSeller().getFirstName(),
                                    p.getSeller().getLastName()));

                    UserSeedDto seller = this.modelMapper.map(p.getSeller(), UserSeedDto.class);
                    productAndSellerDto.setSeller(seller);

                    return productAndSellerDto;
                }).collect(Collectors.toSet());
    }
}
