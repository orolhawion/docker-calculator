package de.akquinet.ats.ccsp.calculator.rest;

import javax.ws.rs.ApplicationPath;

import static de.akquinet.ats.ccsp.calculator.rest.JaxRSActivator.ROOT;

@ApplicationPath(ROOT)
public class JaxRSActivator {

    public static final String ROOT = "/rest";
    public static final String CALCULATE = "/calculate";
}
