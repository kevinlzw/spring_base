package com.example.application.assembler;

import com.example.presentation.vo.OrderDto;

@org.mapstruct.Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public class OrderDtoMapper {
    OrderDtoMapper MAPPER = getMapper(OrderDtoMapper.class);

    OrderDto toDto(Order order);
}
