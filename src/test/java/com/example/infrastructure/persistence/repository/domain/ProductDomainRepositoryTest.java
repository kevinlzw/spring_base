package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.Product;
import com.example.domain.entity.ProductStatus;
import com.example.infrastructure.persistence.entity.ProductPo;
import com.example.infrastructure.persistence.repository.JpaProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductDomainRepositoryTest {

  @Mock
  private JpaProductRepository jpaProductRepository;
  @InjectMocks
  private ProductDomainRepository productDomainRepository;

  @Test
  public void findProductsTest() {
    LocalDateTime createTime = LocalDateTime.now();
    LocalDateTime updateTime = LocalDateTime.now();
    Product product = Product.builder().id("1").name("Product1").price(new BigDecimal("100.00"))
        .status(ProductStatus.INVALID).createTime(createTime).updateTime(updateTime).build();

    ProductPo productPo = new ProductPo();
    productPo.setId("1");
    productPo.setName("Product1");
    productPo.setPrice(new BigDecimal("100.00"));
    productPo.setStatus(ProductStatus.INVALID);
    productPo.setCreateTime(createTime);
    productPo.setUpdateTime(updateTime);

    when(jpaProductRepository.findAll()).thenReturn(Collections.singletonList(productPo));

    List<Product> result = productDomainRepository.findProducts();

    assertEquals(1, result.size());
    assertEquals(product.getId(), result.get(0).getId());
    assertEquals(product.getName(), result.get(0).getName());
    assertEquals(product.getPrice(), result.get(0).getPrice());
    assertEquals(product.getStatus(), result.get(0).getStatus());
  }
}
