package com.example.application.service;

import com.example.domain.entity.Product;
import com.example.domain.entity.ProductStatus;
import com.example.domain.repository.ProductRepository;
import com.example.presentation.vo.ProductDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductApplicationServiceTest {
  @Mock
  private ProductRepository repository;
  @InjectMocks
  private ProductApplicationService service;

  @Test
  void getProducts() {
    Product product1 = Product.builder().id("1").name("Product1").price(new BigDecimal("100.00"))
        .status(ProductStatus.INVALID).createTime(LocalDateTime.now())
        .updateTime(LocalDateTime.now()).build();

    Product product2 = Product.builder().id("2").name("Product2").price(new BigDecimal("200.00"))
        .status(ProductStatus.VALID).createTime(LocalDateTime.now()).updateTime(LocalDateTime.now())
        .build();

    List<Product> productList = Arrays.asList(product1, product2);
    when(repository.findProducts()).thenReturn(productList);

    List<ProductDto> productDtos = service.getProducts();

    assertEquals(productList.size(), productDtos.size());
    for (int i = 0; i < productList.size(); i++) {
      Product product = productList.get(i);
      ProductDto dto = productDtos.get(i);

      assertEquals(product.getId(), dto.getId());
      assertEquals(product.getName(), dto.getName());
      assertEquals(product.getPrice(), dto.getPrice());
      assertEquals(product.getStatus(), dto.getStatus());
    }
  }
}
