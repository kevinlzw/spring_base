package com.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.example.common.util.StreamUtil.processList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  private String customerId;

  private String orderId = UUID.randomUUID().toString().replace("-", "");

  private List<ProductDetail> productDetails;

  @Builder.Default
  private String status = "submit";

  private LocalDateTime createTime;

  private LocalDateTime updateTime;

  public Order(List<Product> products, Map<String, Integer> productQuantity,
      LocalDateTime createTime, String customerId) {
    this.createTime = createTime;
    this.customerId = customerId;
    this.productDetails = processList(products,
        product -> new ProductDetail(product, productQuantity.get(product.getId())));
  }

  public BigDecimal calculateTotalPrice() {
    return productDetails.stream()
        .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
