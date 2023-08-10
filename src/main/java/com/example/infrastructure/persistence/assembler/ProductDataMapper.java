package com.example.infrastructure.persistence.assembler;

import com.example.domain.entity.Product;
import com.example.infrastructure.persistence.entity.ProductPo;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ProductDataMapper {
  ProductDataMapper mapper = getMapper(ProductDataMapper.class);

  Product toDo(ProductPo productPo);
}
