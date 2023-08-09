package com.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
  private String id;

  private String name;

  private BigDecimal price;

  private ProductStatus status;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

}
