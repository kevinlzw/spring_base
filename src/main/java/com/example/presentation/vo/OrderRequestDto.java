package com.example.presentation.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderRequestDto {
  private String customerId;

  private List<ProductRequestDto> products;

  public static class ProductRequestDto{
    private String id;
    private Integer quantity;
  }
}
