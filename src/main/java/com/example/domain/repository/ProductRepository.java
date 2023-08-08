package com.example.domain.repository;

import com.example.domain.entity.Product;

import java.util.List;

public interface ProductRepository {

  List<Product> findProducts();

  Product findProduct(String productId);
}
