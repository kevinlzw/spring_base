package com.example;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class OrderControllerIntegrationTest extends BaseIntegrationTest {

  @Test
  @DataSet("orders.yml")
  public void findOrders_should_success() {
    given().when().get("/orders").then().statusCode(200).body("[0].id", equalTo("12345"))
        .body("[0].name", equalTo("idk")).body("[0].price.toString()", equalTo("223.34"))
        .body("[0].status", equalTo("VALID"));
  }
}
