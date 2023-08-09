package com.example.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order")
public class OrderPo {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(name = "customer_id")
  private String customerId;

  @Column(name = "order_id")
  private String orderId;

  @Column(name = "product_id")
  private String productId;

  @Column(name = "product_name")
  private String name;

  @Column(name = "product_price")
  private BigDecimal price;

  @Column(name = "product_quantity")
  private Integer quantity;

  @CreationTimestamp
  private LocalDateTime createTime;

  @UpdateTimestamp
  private LocalDateTime updateTime;
}
