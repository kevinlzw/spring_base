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
public class SaveOrderRequestDto {
  private String customerId;

  private List<ProductRequestDto> products;

}
