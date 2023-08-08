package com.example;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.junit5.api.DBRider;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@DBRider
@DBUnit(schema = "PUBLIC")
@ExtendWith(DBUnitExtension.class)
@ActiveProfiles("api-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {
  @LocalServerPort
  int port = 9090;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
  }
}
