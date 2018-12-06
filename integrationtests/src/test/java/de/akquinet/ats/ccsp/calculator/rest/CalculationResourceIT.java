package de.akquinet.ats.ccsp.calculator.rest;

import de.akquinet.ats.ccsp.calculator.model.Operator;
import de.akquinet.ats.ccsp.calculator.rest.json.CalculationRequest;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculationResourceIT extends AbstractCalculatorIT {

    public CalculationResourceIT() {
        super("/calculate");
    }

    @Test
    public void canAddValidValues() {
        final CalculationRequest request = CalculationRequest.Builder.aCalculationRequest()
                .withOperands(Arrays.asList(5D, 3D))
                .withOperator(Operator.ADD)
                .build();

        final Response response = given().body(request).contentType("application/json").post();
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.asString()).isEqualTo("8.0");
    }

    @Test
    public void canSubtractValidValues() {
        final CalculationRequest request = CalculationRequest.Builder.aCalculationRequest()
                .withOperands(Arrays.asList(5D, 3D))
                .withOperator(Operator.SUBTRACT)
                .build();

        final Response response = given().body(request).contentType("application/json").post();
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.asString()).isEqualTo("2.0");
    }

    @Test
    public void canMultiplyValidValues() {
        final CalculationRequest request = CalculationRequest.Builder.aCalculationRequest()
                .withOperands(Arrays.asList(5D, 3D))
                .withOperator(Operator.MULTIPLY)
                .build();

        final Response response = given().body(request).contentType("application/json").post();
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.asString()).isEqualTo("15.0");
    }

    @Test
    public void canDivideValidValues() {
        final CalculationRequest request = CalculationRequest.Builder.aCalculationRequest()
                .withOperands(Arrays.asList(5D, 2D))
                .withOperator(Operator.DIVIDE)
                .build();

        final Response response = given().body(request).contentType("application/json").post();
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.asString()).isEqualTo("2.5");
    }

    @Test
    public void canHandleDivisionByZero() {
        final CalculationRequest request = CalculationRequest.Builder.aCalculationRequest()
                .withOperands(Arrays.asList(5D, 0D))
                .withOperator(Operator.DIVIDE)
                .build();

        final Response response = given().body(request).contentType("application/json").post();
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.asString()).contains("Division by 0 is undefined");
    }

    @Test
    public void canHandleUnsupportedOperator() {
        final String request = "{\n" +
                "  \"operands\": [ 2, 3 ],\n" +
                "  \"operator\": \"FOOBAR\"\n" +
                "}\n";

        final Response response = given().body(request).contentType("application/json").post();
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.asString()).contains("The provided operator ");
        assertThat(response.asString()).contains(" is not supported.");
    }

    @Test
    public void canHandleUnsupportedOperands() {
        final String request = "{\n" +
                "  \"operands\": [ 2, \"a\" ],\n" +
                "  \"operator\": \"ADD\"\n" +
                "}";

        final Response response = given().body(request).contentType("application/json").post();
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.asString()).contains("The provided operand ");
        assertThat(response.asString()).contains(" cannot be processed.");
    }
}
