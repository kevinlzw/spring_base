package com.example.infrastructure.persistence.assembler;

import com.example.domain.entity.Order;
import com.example.domain.entity.ProductDetail;
import com.example.infrastructure.persistence.entity.OrderPo;
import com.example.presentation.vo.ProductDetailsDto;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@org.mapstruct.Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface OrderDataMapper {
    OrderDataMapper mapper = getMapper(OrderDataMapper.class);

    Order toDo(OrderPo orderPo);

    OrderPo toPo(Order order);

    @Mapping(source = "productId", target = "id")
    ProductDetail toProductDetail(OrderPo orderPo);
}
