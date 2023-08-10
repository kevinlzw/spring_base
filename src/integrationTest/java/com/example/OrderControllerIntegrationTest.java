package com.example;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
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
            .body("[0].orderId",equalTo("2"))
            .body("[1].products[0].id",equalTo("1"))
            .body("[1].products[0].name",equalTo("idk"))
            .body("[1].products[0].price.toString()",equalTo("20.0"))
            .body("[1].products[0].quantity",equalTo(5))
            .body("[1].products[1].id",equalTo("2"))
            .body("[1].products[1].name",equalTo("book"))
            .body("[1].products[1].price.toString()",equalTo("10.0"))
            .body("[1].products[1].quantity",equalTo(1))
            .body("[1].orderStatus",equalTo("submit"))
            .body("[1].totalPrice.toString()",equalTo("110.0"));
  }
}

