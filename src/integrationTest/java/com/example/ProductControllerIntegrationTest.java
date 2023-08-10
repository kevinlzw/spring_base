package com.example;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ProductControllerIntegrationTest extends BaseIntegrationTest {

  @Test
  @DataSet("products.yml")
  public void findProducts_should_success() {
    given().when().get("/products").then().statusCode(200).body("[0].id", equalTo("12345"))
        .body("[0].name", equalTo("idk")).body("[0].price.toString()", equalTo("223.34"))
        .body("[0].status", equalTo("VALID")).body("[1].id", equalTo("123456"))
        .body("[1].name", equalTo("idksaa")).body("[1].price.toString()", equalTo("223.34"))
        .body("[1].status", equalTo("INVALID"));
  }
}
