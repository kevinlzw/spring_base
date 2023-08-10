package com.example.infrastructure.persistence.assembler;

import com.example.domain.entity.Order;
import com.example.domain.entity.ProductDetail;
import com.example.infrastructure.persistence.entity.OrderPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.common.util.StreamUtil.processList;
import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface OrderDataMapper {
  OrderDataMapper mapper = getMapper(OrderDataMapper.class);

  default Map<String, List<ProductDetail>> groupOrderPoByOrderId(List<OrderPo> orders) {
    return orders.stream().collect(Collectors.groupingBy(OrderPo::getOrderId,
        Collectors.mapping(this::toProductDetail, Collectors.toList())));
  }

  @Mapping(source = "order.orderId", target = "orderId")
  @Mapping(source = "order.customerId", target = "customerId")
  @Mapping(source = "productDetail.id", target = "productId")
  @Mapping(source = "productDetail.name", target = "name")
  @Mapping(source = "productDetail.price", target = "price")
  @Mapping(source = "productDetail.quantity", target = "quantity")
  OrderPo toOrderPo(Order order, ProductDetail productDetail);

  default List<OrderPo> toPo(Order order) {
    return processList(order.getProductDetails(), productDetail -> toOrderPo(order, productDetail));
  }


  @Mapping(source = "productId", target = "id")
  ProductDetail toProductDetail(OrderPo orderPo);
}
