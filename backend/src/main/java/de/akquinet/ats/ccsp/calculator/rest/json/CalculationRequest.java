package de.akquinet.ats.ccsp.calculator.rest.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.akquinet.ats.ccsp.calculator.model.Operator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        if (operands == null) {
            return Collections.emptyList();
        }
        return operands.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public Operator getOperator() {
        return operator;
    }


    public static final class Builder {
        private List<Double> operands;
        private Operator operator;

        private Builder() {
        }

        public static Builder aCalculationRequest() {
            return new Builder();
        }

        public Builder withOperands(List<Double> operands) {
            this.operands = operands;
            return this;
        }

        public Builder withOperator(Operator operator) {
            this.operator = operator;
            return this;
        }

        public CalculationRequest build() {
            return new CalculationRequest(operands, operator);
        }
    }
}
