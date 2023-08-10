package com.example.application.service.common;

import com.example.domain.entity.Product;
import com.example.domain.entity.ProductDetail;
import com.example.presentation.vo.ProductDetailsDto;
import com.example.presentation.vo.ProductRequestDto;

import java.math.BigDecimal;

import static com.example.domain.entity.ProductStatus.*;

public class ProductFixture {

  public final static String PRODUCT_ID = "27EAE572-93BF-467E-B40A-E71E27B85101";

  public final static String PRODUCT_NAME = "name";

  public final static BigDecimal PRODUCT_PRICE = BigDecimal.ONE;

  public final static Integer PRODUCT_QUANTITY = 10;

  public final static ProductRequestDto PRODUCT_REQUEST_DTO = new ProductRequestDto(PRODUCT_ID, 32);

  public final static Product VALID_PRODUCT =
      new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, VALID, null, null);

  public final static Product INVALID_PRODUCT =
      new Product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, INVALID, null, null);


  public final static ProductDetail PRODUCT_DETAIL =
      new ProductDetail(PRODUCT_ID, PRODUCT_NAME, BigDecimal.ONE, PRODUCT_QUANTITY);

  public static ProductDetail.ProductDetailBuilder ProductDetailBuilder(String id, String name,
      Integer price, Integer quantity) {
    return ProductDetail.builder().id(id).name(name).price(BigDecimal.valueOf(price))
        .quantity(quantity);
  }

  public final static ProductDetailsDto PRODUCT_DETAIL_DTO =
      new ProductDetailsDto(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QUANTITY);
}
