package de.akquinet.ats.ccsp.calculator.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Operator {

    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE;

    @JsonValue
    public String value() {
        return this.name();
    }
}
