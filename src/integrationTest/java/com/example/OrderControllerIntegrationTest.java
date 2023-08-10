package com.example;

import com.example.presentation.vo.OrderDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;


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

    given().param("customerId","12345")
            .when().get("/orders")
            .then().statusCode(200)
            .body("[0].products[0].id",equalTo("1"))
            .body("[0].products[0].name",equalTo("idk"))
            .body("[0].products[0].price.toString()",equalTo("20.0"))
            .body("[0].products[0].quantity",equalTo(5))
            .body("[0].orderStatus",equalTo("submit"))
            .body("[0].totalPrice.toString()",equalTo("100.0"))
            .body("[0].orderId",equalTo("1"));
  }
}

