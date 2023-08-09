package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.OrderPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<OrderPo, String> {
  List<OrderPo> findOrdersByCustomerId(String customerId);
  List<OrderPo> findOrdersByOrderId(String customerId);
}
