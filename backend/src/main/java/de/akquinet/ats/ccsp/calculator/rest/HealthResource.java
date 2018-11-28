package de.akquinet.ats.ccsp.calculator.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static de.akquinet.ats.ccsp.calculator.rest.JaxRSActivator.HEALTH;

@Path(HEALTH)
public class HealthResource {

    @GET
    public Response health() {
        return Response.ok().build();
    }
}
