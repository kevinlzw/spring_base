package com.example.application.assembler;

import com.example.domain.entity.Order;
import com.example.domain.entity.ProductDetail;
import com.example.presentation.vo.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface OrderDtoMapper {
  OrderDtoMapper MAPPER = getMapper(OrderDtoMapper.class);

  @Mappings({@Mapping(source = "productDetails", target = "products"),
      @Mapping(source = "orderId", target = "orderId"),
      @Mapping(source = "status", target = "orderStatus"),
      @Mapping(source = "createTime", target = "createTime"),
      @Mapping(source = "updateTime", target = "updateTime"),
      @Mapping(source = "order.productDetails", target = "totalPrice",
          qualifiedByName = "calculatePrice")})
  OrderDto toDto(Order order);

  @Named("calculatePrice")
  default BigDecimal calculatePrice(List<ProductDetail> productDetails) {
    return productDetails.stream()
        .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
