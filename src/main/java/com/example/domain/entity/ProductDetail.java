package com.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetail {
  private String id;

  private String name;

  private BigDecimal price;

  private Integer quantity;

  public ProductDetail(Product product, Integer quantity) {
    this.id = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();
    this.quantity = quantity;
  }
}
