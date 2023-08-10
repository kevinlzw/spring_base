package com.example;

import com.example.presentation.vo.OrderDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class OrderControllerIntegrationTest extends BaseIntegrationTest {
    @Test
    @DataSet("products.yml")
    @ExpectedDataSet("savedOrders.yml")
    public void save_orders_should_success() {
        given()
                .contentType("application/json")
                .body(
                "{\n" +
                        "    \"customerId\": \"XXX\",\n" +
                        "    \"products\": [\n" +
                        "        {\n" +
                        "            \"id\": \"12345\",\n" +
                        "            \"quantity\": 32\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}"
        ).when().post("/orders").then().statusCode(201);
    }
  @Test
  @DataSet("orders.yml")
  public void get_order_list_should_success() {
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

