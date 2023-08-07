package com.example.presentation.facade;


import com.example.application.service.ProductApplicationService;
import com.example.presentation.vo.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
  private final ProductApplicationService productApplicationService;

  @GetMapping
  public List<ProductDto> getProducts() {
    return productApplicationService.getProducts();
  }

}
