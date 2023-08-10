package com.example.application.service.common;

import com.example.domain.entity.Order;
import com.example.presentation.vo.OrderDto;
import com.example.domain.entity.ProductDetail;
import com.example.presentation.vo.SaveOrderRequestDto;
import com.example.presentation.vo.SaveOrderResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.example.application.service.common.CustomerFixture.CUSTOMER_ID;
import static com.example.application.service.common.ProductFixture.*;

public class OrderFixture {

  public final static String ORDER_ID = UUID.randomUUID().toString().replace("-", "");
  public final static BigDecimal TOTAL_PRICE = BigDecimal.TEN;
  public final static SaveOrderRequestDto SAVE_ORDER_REQUEST_DTO =
      new SaveOrderRequestDto(CUSTOMER_ID, List.of(PRODUCT_REQUEST_DTO));

  public final static Order ORDER =
      new Order(CUSTOMER_ID, ORDER_ID, List.of(PRODUCT_DETAIL), "submit", null, null);

  public final static OrderDto ORDER_DTO =
          new OrderDto(List.of(PRODUCT_DETAIL_DTO),BigDecimal.TEN,"submit",ORDER_ID,null,null);

  public final static SaveOrderResponseDto SAVE_ORDER_RESPONSE_DTO =
      new SaveOrderResponseDto(ORDER_ID);

  public static Order.OrderBuilder orderBuilder(String id, List<ProductDetail> products){
    LocalDateTime time = LocalDateTime.of(2016, 7, 31, 14, 15);
    return Order.builder().orderId(id).productDetails(products).createTime(time).updateTime(time);
  }
}
