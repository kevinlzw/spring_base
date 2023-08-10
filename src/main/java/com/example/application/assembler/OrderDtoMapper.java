package com.example.application.assembler;

import com.example.domain.entity.Order;
import com.example.presentation.vo.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface OrderDtoMapper {
  OrderDtoMapper MAPPER = getMapper(OrderDtoMapper.class);

  @Mappings({@Mapping(source = "productDetails", target = "products"),
      @Mapping(source = "orderId", target = "orderId"),
      @Mapping(source = "status", target = "orderStatus"),
      @Mapping(source = "createTime", target = "createTime"),
      @Mapping(source = "updateTime", target = "updateTime"),
      @Mapping(expression = "java(order.calculateTotalPrice())", target = "totalPrice")})
  OrderDto toDto(Order order);

}
