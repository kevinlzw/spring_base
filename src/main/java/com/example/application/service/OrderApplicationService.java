package com.example.application.service;

import com.example.application.assembler.OrderDtoMapper;
import com.example.domain.entity.Order;
import com.example.domain.entity.Product;
import com.example.domain.repository.OrderRepository;
import com.example.domain.repository.ProductRepository;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderApplicationService {

  private final OrderRepository orderRepository;

  private final ProductRepository productRepository;
  private final OrderDtoMapper mapper = OrderDtoMapper.MAPPER;

  public List<OrderDto> getOrderList(String customerId) {

    List<Order> orders = orderRepository.findOrders(customerId);

    Map<String, List<ProductDto>> orderIdProductMap = orders.stream()
        .collect(Collectors.groupingBy(Order::getOrderId, Collectors.mapping(order -> {
          Product product = productRepository.findProduct(order.getProductId());
          return ProductDto.builder().price(product.getPrice()).name(product.getName())
              .quantity(order.getQuantity()).build();
        }, Collectors.toList())));

    List<OrderDto> result = orderIdProductMap.entrySet().stream()
        .map(entry -> OrderDto.builder().orderId(entry.getKey()).products(entry.getValue())
            .createTime(orders.stream().filter(order -> entry.getKey().equals(order.getOrderId()))
                .findFirst().map(Order::getCreateTime).orElse(null))
            .orderStatus(orders.stream().filter(order -> entry.getKey().equals(order.getOrderId()))
                .findFirst().map(Order::getStatus).orElse(null))
            // todo
            .totalPrice(BigDecimal.valueOf(0)).build())
        .collect(Collectors.toList());

    return result;


  }

  public OrderDto getOrderByOrderId(String orderId) {

    return mapper.toDto(orderRepository.findOrderById(orderId));
  }
}
