package de.akquinet.ats.ccsp.calculator.rest.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.akquinet.ats.ccsp.calculator.model.Operator;

import java.util.List;

public class CalculationRequest {

    private final List<Double> operands;
    private final Operator operator;

    @JsonCreator
    public CalculationRequest(
            @JsonProperty("operands") final List<Double> operands,
            @JsonProperty("operator") final Operator operator) {
        this.operands = operands;
        this.operator = operator;
    }

    public List<Double> getOperands() {
        return operands;
    }

    public Operator getOperator() {
        return operator;
    }
}
