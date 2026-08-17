package com.example.testing;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MultiplyTest {
    @Test
    void testMultiply() {
        int expected = 50;
        int actual = CalculatorService.multiply(10, 5);
        Assert.assertEquals(actual, expected, "Multiply 10 * 5 = 50");
    }
}
