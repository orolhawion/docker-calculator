package de.akquinet.ats.ccsp.calculator.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class HealthResourceIT {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080/calculator/rest";
        RestAssured.basePath = "/health";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void canAddValidValues() {
        final Response response = given().get();
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
