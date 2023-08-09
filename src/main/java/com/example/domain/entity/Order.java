package com.example.domain.entity;

import com.example.presentation.vo.ProductDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  private String orderId;

  private List<ProductDetail> productDetails;

  private String status = "submit";

  private LocalDateTime createTime;

  private LocalDateTime updateTime;
}
