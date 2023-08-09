package com.example.presentation.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SaveOrderResponseDto {
  private String orderId;
}
