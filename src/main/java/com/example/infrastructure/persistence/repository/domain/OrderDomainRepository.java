package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.Order;
import com.example.domain.entity.ProductDetail;
import com.example.domain.repository.OrderRepository;
import com.example.infrastructure.persistence.assembler.OrderDataMapper;
import com.example.infrastructure.persistence.entity.OrderPo;
import com.example.infrastructure.persistence.repository.JpaOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderDomainRepository implements OrderRepository {
  private final JpaOrderRepository jpaOrderRepository;
  private final OrderDataMapper mapper = OrderDataMapper.mapper;

  @Override
  public List<Order> findOrders(String customerId) {
    List<OrderPo> ordersByCustomerId = jpaOrderRepository.findOrdersByCustomerId(customerId);
    return mapper.groupOrderPoByOrderId(ordersByCustomerId).entrySet().stream()
            .map(entry -> Order.builder()
                    .orderId(entry.getKey())
                    .productDetails(entry.getValue())
                    .createTime(ordersByCustomerId.stream().filter(order -> entry.getKey().equals(order.getOrderId()))
                            .findFirst().map(OrderPo::getCreateTime).orElse(null))
                    .updateTime(ordersByCustomerId.stream().filter(order -> entry.getKey().equals(order.getOrderId()))
                            .findFirst().map(OrderPo::getUpdateTime).orElse(null))
                    .build())
            .collect(Collectors.toList());
  }

  @Override
  public Order findOrderById(String orderId) {
    java.util.List<OrderPo> ordersByOrderId = jpaOrderRepository.findOrdersByOrderId(orderId);
    List<ProductDetail> productDetailsList = ordersByOrderId
        .stream().map(mapper::toProductDetail).collect(Collectors.toList());
    return Order.builder().orderId(orderId).productDetails(productDetailsList).build();
  }

  @Override
  public void saveOrders(Order order) {
    jpaOrderRepository.saveAll(mapper.toPo(order));
  }

}
