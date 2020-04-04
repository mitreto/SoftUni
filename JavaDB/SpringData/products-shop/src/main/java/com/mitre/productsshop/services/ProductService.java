package com.mitre.productsshop.services;

import com.mitre.productsshop.models.dtos.ProductAndSellerDto;
import com.mitre.productsshop.models.dtos.ProductSeedDto;
import com.mitre.productsshop.models.entities.Product;

import java.math.BigDecimal;
import java.util.Set;

public interface ProductService {

    void seedProducts(ProductSeedDto[] productSeedDtos);

    Set<ProductAndSellerDto> getAllProductsWithPriceInRangeAndNoBuyer(BigDecimal from, BigDecimal to);
}
