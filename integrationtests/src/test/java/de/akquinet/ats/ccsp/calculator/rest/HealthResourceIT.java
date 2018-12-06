package de.akquinet.ats.ccsp.calculator.rest;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class HealthResourceIT extends AbstractCalculatorIT {

    public HealthResourceIT() {
        super("/health");
    }

    @Test
    public void canAddValidValues() {
        final Response response = given().get();
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
