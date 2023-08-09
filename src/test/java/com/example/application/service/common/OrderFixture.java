package com.example.application.service.common;

import com.example.domain.entity.Order;
import com.example.presentation.vo.SaveOrderRequestDto;
import com.example.presentation.vo.SaveOrderResponseDto;

import java.util.List;
import java.util.UUID;

import static com.example.application.service.common.CustomerFixture.CUSTOMER_ID;
import static com.example.application.service.common.ProductFixture.PRODUCT_DETAIL;
import static com.example.application.service.common.ProductFixture.PRODUCT_REQUEST_DTO;

public class OrderFixture {

  public final static String ORDER_ID = UUID.randomUUID().toString().replace("-", "");
  public final static SaveOrderRequestDto SAVE_ORDER_REQUEST_DTO =
      new SaveOrderRequestDto(CUSTOMER_ID, List.of(PRODUCT_REQUEST_DTO));

  public final static Order ORDER =
      new Order(CUSTOMER_ID, ORDER_ID, List.of(PRODUCT_DETAIL), "submit", null, null);

  public final static SaveOrderResponseDto SAVE_ORDER_RESPONSE_DTO =
      new SaveOrderResponseDto(ORDER_ID);
}
