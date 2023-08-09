package com.example.presentation.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
  private List<ProductDetailsDto> products;

  private BigDecimal totalPrice;

  private String orderStatus;

  private String orderId;

  private LocalDateTime createTime;
}
