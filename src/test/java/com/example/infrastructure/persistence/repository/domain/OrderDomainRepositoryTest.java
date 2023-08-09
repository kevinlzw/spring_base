package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.Order;
import com.example.infrastructure.persistence.entity.OrderPo;
import com.example.infrastructure.persistence.repository.JpaOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderDomainRepositoryTest {

  @Mock
  private JpaOrderRepository jpaOrderRepository;
  @InjectMocks
  private OrderDomainRepository orderDomainRepository;

  @Test
  public void findOrdersTest() {
    LocalDateTime createTime = LocalDateTime.of(2016, 7, 31, 14, 15);

    OrderPo orderPo = new OrderPo();
    orderPo.setOrderId("1");
    orderPo.setProductId("product1");
    orderPo.setQuantity(3);
    orderPo.setCreateTime(createTime);
    when(jpaOrderRepository.findOrdersByCustomerId(anyString())).thenReturn(Collections.singletonList(orderPo));

    List<Order> orders = orderDomainRepository.findOrders(anyString());
    Order order = orders.get(0);
    assertEquals(1, orders.size());
    assertEquals("1", order.getOrderId());
    assertEquals("product1",order.getProductDetails().get(0).getId());
    assertEquals(3,order.getProductDetails().get(0).getQuantity());
    assertEquals(createTime, order.getCreateTime());
  }
}
