package de.akquinet.ats.ccsp.calculator.service;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

@Stateless
public class CalculatorService {

    public Double add(final List<Double> operands) {
        return operands.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public double subtract(final List<Double> operands) {
        return operands.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .reduce((left, right) -> left - right)
                .orElse(0.0);
    }

    public double multiply(final List<Double> operands) {
        return operands.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .reduce((left, right) -> left * right)
                .orElse(0.0);
    }

    public OptionalDouble divide(final List<Double> operands) {
        if (operands.contains(Double.valueOf(0))) {
            return OptionalDouble.empty();
        }

        return operands.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .reduce((left, right) -> left / right);
    }
}
