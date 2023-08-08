package com.example.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.infrastructure.persistence.entity.OrderPo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<OrderPo, String> {
    @Query()
    List<OrderPo> findOrdersByCustomerId(String customerId);
}