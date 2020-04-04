package com.mitre.productsshop.repositories;

import com.mitre.productsshop.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    Set<Product> findAllByPriceBetweenAndAndBuyerIsNull(BigDecimal from, BigDecimal to);
}
