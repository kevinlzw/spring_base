package com.example.application.service;

import com.example.application.assembler.ProductDtoMapper;
import com.example.domain.repository.ProductRepository;
import com.example.presentation.vo.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.common.util.StreamUtil.processList;

@Service
@AllArgsConstructor
public class ProductApplicationService {
  private final ProductRepository productRepository;

  private final ProductDtoMapper mapper = ProductDtoMapper.MAPPER;

  public List<ProductDto> getProducts() {
    return processList(productRepository.findProducts(), mapper::toDto);
  }
}
