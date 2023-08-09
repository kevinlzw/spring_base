package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.Order;
import com.example.domain.entity.ProductDetail;
import com.example.domain.repository.OrderRepository;
import com.example.infrastructure.persistence.assembler.OrderDataMapper;
import com.example.infrastructure.persistence.repository.JpaOrderRepository;
import com.example.presentation.vo.ProductDetailsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.infrastructure.persistence.assembler.OrderDataMapper.MAPPER;

@Component
@AllArgsConstructor
public class OrderDomainRepository implements OrderRepository {
  private final JpaOrderRepository jpaOrderRepository;

  @Override
  public List<Order> findOrders(String customerId) {
    return jpaOrderRepository.findOrdersByCustomerId(customerId).stream().map(MAPPER::toDo)
        .collect(Collectors.toList());
  }

  @Override
  public Order findOrderById(String orderId) {
    List<ProductDetail> productDetailsList = jpaOrderRepository.findOrdersByOrderId(orderId).stream().map(
            MAPPER::toProductDetail
    ).collect(Collectors.toList());
    return Order.builder().orderId(orderId).productDetails(productDetailsList).build();
  }

  @Override
  public void saveOrders(Order order) {
    jpaOrderRepository.save(mapper.toPo(order));
  }

}
