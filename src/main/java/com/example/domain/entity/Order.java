package com.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
  private String id;

  private String orderId;

  private String productId;

  private Integer quantity;

  private String status;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;
}
