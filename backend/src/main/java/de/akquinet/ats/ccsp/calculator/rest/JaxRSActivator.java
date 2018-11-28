package de.akquinet.ats.ccsp.calculator.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static de.akquinet.ats.ccsp.calculator.rest.JaxRSActivator.ROOT;

@ApplicationPath(ROOT)
public class JaxRSActivator extends Application {

    public static final String ROOT = "/rest";

    public static final String HEALTH = "/health";

    public static final String CALCULATE = "/calculate";
}
