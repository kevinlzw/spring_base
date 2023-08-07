package com.example.application.assembler;

import com.example.domain.entity.Product;
import com.example.presentation.vo.ProductDto;

import static org.mapstruct.factory.Mappers.getMapper;

@org.mapstruct.Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ProductDtoMapper {
  ProductDtoMapper MAPPER = getMapper(ProductDtoMapper.class);

  ProductDto toDto(Product product);

}
