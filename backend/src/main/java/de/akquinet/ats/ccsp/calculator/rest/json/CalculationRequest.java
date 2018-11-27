package de.akquinet.ats.ccsp.calculator.rest.json;

import de.akquinet.ats.ccsp.calculator.model.Operator;

import java.util.List;

public class CalculationRequest {

    private final List<Double> operands;
    private final Operator operator;

    public CalculationRequest(List<Double> operands, Operator operator) {
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
