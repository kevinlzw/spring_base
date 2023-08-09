package com.example.application.service;

import com.example.domain.entity.Order;
import com.example.domain.entity.ProductDetail;
import com.example.domain.repository.OrderRepository;
import com.example.domain.repository.ProductRepository;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.SaveOrderResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.example.application.service.common.OrderFixture.ORDER;
import static com.example.application.service.common.OrderFixture.ORDER_ID;
import static com.example.application.service.common.OrderFixture.SAVE_ORDER_REQUEST_DTO;
import static com.example.application.service.common.ProductFixture.PRODUCT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderApplicationServiceTest {
  @Mock
  private OrderRepository orderRepository;

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private OrderApplicationService service;

  @Test
  void should_get_orders_successfully() {
    ProductDetail product1 =
            ProductDetail.builder().id("product1").name("book").price(BigDecimal.valueOf(20)).quantity(3).build();
    ProductDetail product2 =
            ProductDetail.builder().id("product2").name("cat").price(BigDecimal.valueOf(200)).quantity(2).build();

    LocalDateTime createTime = LocalDateTime.of(2016, 7, 31, 14, 15);
    Order order1 = Order.builder().orderId("1").productDetails(List.of(product1,product2))
        .createTime(createTime).updateTime(createTime).build();

    Order order2 = Order.builder().orderId("2").productDetails(List.of(product1))
        .createTime(createTime).updateTime(createTime).build();

    List<Order> orders = Arrays.asList(order1, order2);
    when(orderRepository.findOrders(anyString())).thenReturn(orders);

    List<OrderDto> orderList = service.getOrderList(anyString());
    OrderDto orderDto1 = orderList.get(0);
    OrderDto orderDto2 = orderList.get(1);

    assertEquals(2, orderList.size());
    assertEquals("1", orderDto1.getOrderId());
    assertEquals("book", orderDto1.getProducts().get(0).getName());
    assertEquals(BigDecimal.valueOf(20), orderDto1.getProducts().get(0).getPrice());
    assertEquals(3, orderDto1.getProducts().get(0).getQuantity());
    assertEquals("cat", orderDto1.getProducts().get(1).getName());
    assertEquals(BigDecimal.valueOf(200), orderDto1.getProducts().get(1).getPrice());
    assertEquals(2, orderDto1.getProducts().get(1).getQuantity());
    assertEquals("submit", orderDto1.getOrderStatus());
    assertEquals(createTime, orderDto1.getCreateTime());
    assertEquals("2", orderDto2.getOrderId());
    assertEquals("book", orderDto2.getProducts().get(0).getName());
    assertEquals(BigDecimal.valueOf(20), orderDto2.getProducts().get(0).getPrice());
    assertEquals(3, orderDto2.getProducts().get(0).getQuantity());
    assertEquals("submit", orderDto2.getOrderStatus());
    assertEquals(createTime, orderDto2.getCreateTime());
  }

  @Test
  public void should_save_order_successfully() {
    when(productRepository.findProducts(anyList())).thenReturn(List.of(PRODUCT));

    doNothing().when(orderRepository).saveOrders(ORDER);

    SaveOrderResponseDto response = service.takeOrder(SAVE_ORDER_REQUEST_DTO);

    assertEquals(ORDER_ID, response.getOrderId());
  }
}
