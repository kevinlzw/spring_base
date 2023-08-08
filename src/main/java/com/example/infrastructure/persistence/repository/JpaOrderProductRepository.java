package com.example.infrastructure.persistence.repository;

import com.example.common.base.JpaAndQueryDslExecutor;
import com.example.infrastructure.persistence.entity.OrderProductPo;

import java.util.List;

public interface JpaOrderProductRepository extends JpaAndQueryDslExecutor<OrderProductPo, String> {
    List<OrderProductPo> findAllByOrderId(String orderId);
}