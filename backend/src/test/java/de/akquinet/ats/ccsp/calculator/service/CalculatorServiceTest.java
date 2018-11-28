package de.akquinet.ats.ccsp.calculator.service;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorServiceTest {

    private CalculatorService sut = new CalculatorService();

    @Test
    public void canAdd() {
        final List<Double> operands = Arrays.asList(3d, 2d);
        assertThat(sut.add(operands)).isEqualTo(5d);
    }

    @Test
    public void canSubtract() {
        final List<Double> operands = Arrays.asList(3d, 2d);
        assertThat(sut.subtract(operands)).isEqualTo(1d);
    }

    @Test
    public void canMultiply() {
        final List<Double> operands = Arrays.asList(3d, 2d);
        assertThat(sut.multiply(operands)).isEqualTo(6d);
    }

    @Test
    public void canDivide() {
        final List<Double> operands = Arrays.asList(4d, 2d);
        assertThat(sut.divide(operands)).isPresent();
        assertThat(sut.divide(operands).orElse(0d)).isEqualTo(2d);
    }

    @Test
    public void canHandleDivisionByZero() {
        final List<Double> operands = Arrays.asList(4d, 0d);

        assertThat(operands).contains(0d);
        assertThat(sut.divide(operands)).isNotPresent();
    }
}