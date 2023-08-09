package com.example.presentation.vo;

import com.example.domain.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductDetailsDto {
  private String name;

  private BigDecimal price;

  private Integer quantity;

  private ProductStatus status;
}
