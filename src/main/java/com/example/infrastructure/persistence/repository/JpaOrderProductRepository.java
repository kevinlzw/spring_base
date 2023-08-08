package com.example.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.infrastructure.persistence.entity.OrderProductPo;

import java.util.List;

public interface JpaOrderProductRepository extends JpaRepository<OrderProductPo, String> {
    List<OrderProductPo> findAllByOrderId(String orderId);
}