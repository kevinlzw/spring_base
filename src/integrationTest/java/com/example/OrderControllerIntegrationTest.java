package com.example;

import com.example.presentation.vo.OrderDto;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class OrderControllerIntegrationTest extends BaseIntegrationTest {

  @Test
  @DataSet("orders.yml")
  public void getOrderList_should_returnOrderList() {
    String customerId = "12345";

    List<OrderDto> orderList = given().param("customerId", customerId)
            .when().get("/orders")
            .then().statusCode(200)
            .extract().body().jsonPath().getList(".", OrderDto.class);

    assertThat(orderList).hasSize(2);
    assertThat(orderList.get(0).getOrderId()).isEqualTo("ORD-123456");
    assertThat(orderList.get(0).getTotalPrice()).isEqualTo(30.98);

  }
}

