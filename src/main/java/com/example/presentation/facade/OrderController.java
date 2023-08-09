package com.example.presentation.facade;

import com.example.application.service.OrderApplicationService;
import com.example.presentation.vo.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

  private final OrderApplicationService orderApplicationService;

  @GetMapping("/{id}/customerId")
  public OrderDto getOrderDetails(@PathVariable String id,@RequestParam String customerId) {
    return orderApplicationService.getOrderByOrderId(id);
  }

  @GetMapping("/{customerId}")
  public List<OrderDto> getOrderList(@PathVariable String customerId) {
    return orderApplicationService.getOrderList(customerId);
  }
}
