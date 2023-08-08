package com.example.presentation.facade;

import com.example.application.service.OrderApplicationService;
import com.example.presentation.vo.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

  private final OrderApplicationService orderApplicationService;

  @GetMapping("customer/{id}")
  public OrderDto getOrderDetails(@PathVariable String id) {
    return null;
    // Todo: need development
  }

  @GetMapping("/{customerId}")
  public List<OrderDto> getOrderList(@PathVariable String customerId) {
    return orderApplicationService.getOrderList(customerId);
  }
}
