package de.akquinet.ats.ccsp.calculator.rest;

import de.akquinet.ats.ccsp.calculator.rest.json.CalculationRequest;
import de.akquinet.ats.ccsp.calculator.service.CalculatorService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.OptionalDouble;

import static de.akquinet.ats.ccsp.calculator.rest.JaxRSActivator.CALCULATE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path(CALCULATE)
public class CalculationResource {

    @Inject
    private CalculatorService service;

    @POST
    @Consumes(APPLICATION_JSON)
    public Response calculate(final CalculationRequest request) {
        if (request.getOperands().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("No operands provided, cannot calculate.")
                    .build();
        }

        switch (request.getOperator()) {
            case ADD: return Response.ok(service.add(request.getOperands())).build();
            case SUBTRACT: return Response.ok(service.subtract(request.getOperands())).build();
            case MULTIPLY: return Response.ok(service.multiply(request.getOperands())).build();
            case DIVIDE:
                final OptionalDouble result = service.divide(request.getOperands());
                if (result.isPresent()) {
                    return Response.ok(result.getAsDouble()).build();
                }
                return Response.status(Response.Status.BAD_REQUEST).entity("Division by 0 is undefined.").build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
