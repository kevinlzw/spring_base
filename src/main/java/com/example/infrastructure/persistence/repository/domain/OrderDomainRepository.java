package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.Order;
import com.example.domain.entity.ProductDetail;
import com.example.domain.repository.OrderRepository;
import com.example.infrastructure.persistence.assembler.OrderDataMapper;
import com.example.infrastructure.persistence.entity.OrderPo;
import com.example.infrastructure.persistence.repository.JpaOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.common.util.StreamUtil.processList;

@Component
@AllArgsConstructor
public class OrderDomainRepository implements OrderRepository {
  private final JpaOrderRepository jpaOrderRepository;
  private final OrderDataMapper mapper = OrderDataMapper.mapper;

  @Override
  public List<Order> findOrders(String customerId) {
    List<OrderPo> ordersByCustomerId = jpaOrderRepository.findOrdersByCustomerId(customerId);
    var entries = mapper.groupOrderPoByOrderId(ordersByCustomerId).entrySet();
    return processList(entries, getOrder(ordersByCustomerId));
  }

  @Override
  public Order findOrderById(String orderId) {
    java.util.List<OrderPo> orderPos = jpaOrderRepository.findOrdersByOrderId(orderId);
    if(Objects.isNull(orderPos)){
      return null;
    }
    OrderPo orderPo = orderPos.get(0);
    LocalDateTime createTime = orderPo.getCreateTime();
    LocalDateTime updateTime = orderPo.getUpdateTime();

    List<ProductDetail> productDetailsList =
            orderPos.stream().map(mapper::toProductDetail).collect(Collectors.toList());
    return Order.builder().orderId(orderId).createTime(createTime).updateTime(updateTime).productDetails(productDetailsList).build();
  }

  @Override
  public void saveOrders(Order order) {
    List<OrderPo> orderPos = processList(order.getProductDetails(),
        productDetail -> mapper.toOrderPo(order, productDetail));
    jpaOrderRepository.saveAll(orderPos);
  }

  private static Function<Entry<String, List<ProductDetail>>, Order> getOrder(
      List<OrderPo> ordersByCustomerId) {
    return entry -> {
      var orderPo = ordersByCustomerId.stream()
          .filter(order -> entry.getKey().equals(order.getOrderId())).findFirst();

      LocalDateTime createTime = orderPo.map(OrderPo::getCreateTime).orElse(null);
      LocalDateTime updateTime = orderPo.map(OrderPo::getUpdateTime).orElse(null);

      return Order.builder().orderId(entry.getKey()).productDetails(entry.getValue())
          .createTime(createTime).updateTime(updateTime).build();
    };
  }


}
