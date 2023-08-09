package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.Product;
import com.example.domain.repository.ProductRepository;
import com.example.infrastructure.persistence.assembler.ProductDataMapper;
import com.example.infrastructure.persistence.repository.JpaProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.common.util.StreamUtil.processList;

@Component
@AllArgsConstructor
public class ProductDomainRepository implements ProductRepository {

  private final JpaProductRepository jpaProductRepository;
  private final ProductDataMapper mapper = ProductDataMapper.mapper;

  @Override
  public List<Product> findProducts() {
    return processList(jpaProductRepository.findAll(), mapper::toDo);
  }

  @Override
  public List<Product> findProducts(List<String> productIds) {
    return processList(jpaProductRepository.findAllById(productIds), mapper::toDo);
  }

  @Override
  public Product findProduct(String productId) {
    return jpaProductRepository.findById(productId).map(mapper::toDo).orElse(null);
  }
}
