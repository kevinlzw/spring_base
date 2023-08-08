package com.example.presentation.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    private ProductDto productDto;

    private BigDecimal totalPrice;

    private String orderStatus;

    private String orderId;

    private String createTime;
}
