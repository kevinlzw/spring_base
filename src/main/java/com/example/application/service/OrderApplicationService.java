package com.example.application.service;

import com.example.application.assembler.OrderDtoMapper;
import com.example.domain.repository.OrderRepository;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.SaveOrderRequestDto;
import com.example.presentation.vo.ProductDetailsDto;
import com.example.presentation.vo.ProductRequestDto;
import com.example.presentation.vo.SaveOrderResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.common.util.StreamUtil.processList;

import static com.example.common.util.StreamUtil.processList;
import static java.time.LocalDateTime.now;

@Service
@AllArgsConstructor
public class OrderApplicationService {

  private final OrderRepository orderRepository;
  private final OrderDtoMapper mapper = OrderDtoMapper.MAPPER;

  public List<OrderDto> getOrderList(String customerId) {
    return processList(orderRepository.findOrders(customerId), mapper::toDto);
  }

  public OrderDto getOrderByOrderId(String orderId) {
    return mapper.toDto(orderRepository.findOrderById(orderId));
  }

  public SaveOrderResponseDto takeOrder(SaveOrderRequestDto orderRequest) {
    List<String> productIds = processList(orderRequest.getProducts(), ProductRequestDto::getId);
    Map<String, Integer> productQuantity = orderRequest.getProducts().stream()
        .collect(Collectors.toMap(ProductRequestDto::getId, ProductRequestDto::getQuantity));
    List<Product> products = productRepository.findProducts(productIds);
    Order order = new Order(products, productQuantity, now(), orderRequest.getCustomerId());
    orderRepository.saveOrders(order);
    return new SaveOrderResponseDto(order.getOrderId());
  }

}
