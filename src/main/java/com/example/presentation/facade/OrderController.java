package com.example.presentation.facade;

import com.example.application.service.OrderApplicationService;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.SaveOrderResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

  private final OrderApplicationService orderApplicationService;

  @GetMapping("/{id}")
  public OrderDto getOrderDetails(@PathVariable String id, @RequestParam String customerId) {
    return orderApplicationService.getOrderByOrderId(id);
  }

  @GetMapping("/{customerId}")
  public List<OrderDto> getOrderList(@PathVariable String customerId) {
    return orderApplicationService.getOrderList(customerId);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public SaveOrderResponseDto takeOrder(
      @RequestBody com.example.presentation.vo.SaveOrderRequestDto orderRequest) {
    return orderApplicationService.takeOrder(orderRequest);
  }
}
