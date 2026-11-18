package FITest;

import FunctionalInterfaces.Calculator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class NumberOperationsTest {

    @Test
    void additionTest() {
        assertEquals(8, Calculator.operation(5, 3, Calculator.ADD));
    }

    @Test
    void subtractTest() {
        assertEquals(2, Calculator.operation(5, 3, Calculator.SUBTRACT));
    }

    @Test
    void multiplyTest() {
        assertEquals(15, Calculator.operation(5, 3, Calculator.MULTIPLY));
    }

    @Test
    void divideTest() {
        assertEquals(2, Calculator.operation(10, 5, Calculator.DIVIDE));
    }

    @Test
    void divideByZeroThrowsTest() {
        assertThrows(ArithmeticException.class, () -> Calculator.operation(10, 0, Calculator.DIVIDE));
    }
}
