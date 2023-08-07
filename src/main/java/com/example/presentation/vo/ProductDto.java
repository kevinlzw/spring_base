package com.example.presentation.vo;

import com.example.domain.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDto {
  private String name;

  private BigDecimal price;

  private ProductStatus status;
}
