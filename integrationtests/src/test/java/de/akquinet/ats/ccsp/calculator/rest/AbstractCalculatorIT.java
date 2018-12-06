package de.akquinet.ats.ccsp.calculator.rest;

import io.restassured.RestAssured;
import org.junit.Before;

public abstract class AbstractCalculatorIT {

    private final String restBasePath;

    protected AbstractCalculatorIT(final String restBasePath) {
        this.restBasePath = restBasePath;
    }

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080/calculator/rest";
        RestAssured.basePath = this.restBasePath;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
