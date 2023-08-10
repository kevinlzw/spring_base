package com.example.application.service;

import com.example.application.assembler.OrderDtoMapper;
import com.example.domain.entity.Order;
import com.example.domain.entity.Product;
import com.example.domain.repository.OrderRepository;
import com.example.domain.repository.ProductRepository;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.ProductRequestDto;
import com.example.presentation.vo.SaveOrderRequestDto;
import com.example.presentation.vo.SaveOrderResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.common.util.StreamUtil.filterList;
import static com.example.common.util.StreamUtil.processList;
import static java.time.LocalDateTime.now;

@Service
@AllArgsConstructor
public class OrderApplicationService {

  private final OrderRepository orderRepository;

  private final ProductRepository productRepository;
  private final OrderDtoMapper mapper = OrderDtoMapper.MAPPER;

  public List<OrderDto> getOrderList(String customerId) {
    List<Order> orders = orderRepository.findOrders(customerId);
    orders.sort(Comparator.comparing(Order::getUpdateTime).reversed());
    return processList(orders, mapper::toDto);
  }

  public OrderDto getOrderByOrderId(String orderId) {
    return mapper.toDto(orderRepository.findOrderById(orderId));
  }

  public SaveOrderResponseDto takeOrder(SaveOrderRequestDto orderRequest) {
    List<String> productIds = processList(orderRequest.getProducts(), ProductRequestDto::getId);
    Map<String, Integer> productQuantity = orderRequest.getProducts().stream()
        .collect(Collectors.toMap(ProductRequestDto::getId, ProductRequestDto::getQuantity));
    List<Product> validProducts =
        filterList(productRepository.findProducts(productIds), Product::isValid);
    Order order = new Order(validProducts, productQuantity, now(), orderRequest.getCustomerId());
    orderRepository.saveOrders(order);
    return new SaveOrderResponseDto(order.getOrderId());
  }

}
