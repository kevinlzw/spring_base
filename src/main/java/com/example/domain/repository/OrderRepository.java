package com.example.domain.repository;

import com.example.domain.entity.Order;

import java.util.List;

public interface OrderRepository {
  List<Order> findOrders(String customerId);
}
