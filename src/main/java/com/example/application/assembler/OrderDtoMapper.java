package com.example.application.assembler;

import com.example.domain.entity.Order;
import com.example.presentation.vo.OrderDto;

import static org.mapstruct.factory.Mappers.getMapper;

@org.mapstruct.Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface OrderDtoMapper {
    OrderDtoMapper MAPPER = getMapper(OrderDtoMapper.class);

    OrderDto toDto(Order order);
}
