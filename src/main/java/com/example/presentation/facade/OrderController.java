package com.example.presentation.facade;

import com.example.presentation.vo.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
  @GetMapping("/{id}")
  public OrderDto getOrderDetails(@PathVariable String id) {
    return null;
    // Todo: need development
  }
}
