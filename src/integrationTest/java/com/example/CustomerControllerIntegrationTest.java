package com.example;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CustomerControllerIntegrationTest extends BaseIntegrationTest {

  @Test
  @DataSet("datasets/customer.yml")
  public void findById_should_success() {
    given().when().get("/customers/{id}", "1").then().statusCode(200);
  }
}
