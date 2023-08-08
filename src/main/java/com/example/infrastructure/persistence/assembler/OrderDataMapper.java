package com.example.infrastructure.persistence.assembler;

import com.example.domain.entity.Order;
import com.example.infrastructure.persistence.entity.OrderPo;

import static org.mapstruct.factory.Mappers.getMapper;

@org.mapstruct.Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface OrderDataMapper {
  OrderDataMapper mapper = getMapper(OrderDataMapper.class);

  Order toDo(OrderPo orderPo);
}
