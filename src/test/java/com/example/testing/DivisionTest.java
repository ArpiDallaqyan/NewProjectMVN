package com.example.testing;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DivisionTest {
    @Test
    void testDivision() {
        int expected = 2;
        int actual = CalculatorService.division(10, 5);
        Assert.assertEquals(actual, expected, "Division 10 / 5 = 2");
    }
}
