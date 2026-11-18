package FITest;

import FunctionalInterfaces.Employee;
import FunctionalInterfaces.SalaryCalculator;
import org.testng.annotations.Test;
import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class SalaryCalculatorTest {

    private final Employee emp = new Employee("Ani", 25, 1000);

    @Test
    public void addBonusTest() {
        assertEquals(1200, SalaryCalculator.operate(emp, 200, SalaryCalculator.ADD_BONUS));
    }

    @Test
    public void raisePercentTest() {
        assertEquals(1100, SalaryCalculator.operate(emp, 10, SalaryCalculator.RAISE_PERCENT));
    }

    @Test
    public void nullEmployeeThrowsTest() {
        assertThrows(IllegalArgumentException.class, () -> SalaryCalculator.operate(null, 10,
                SalaryCalculator.ADD_BONUS));
    }

    @Test
    public void nullOperationThrowsTest() {
        assertThrows(IllegalArgumentException.class, () -> SalaryCalculator.operate(emp, 10, null));
    }

    @Test
    public void negativeValueThrowsTest() {
        assertThrows(IllegalArgumentException.class, () -> SalaryCalculator.operate(emp, -5,
                SalaryCalculator.ADD_BONUS));
    }
}