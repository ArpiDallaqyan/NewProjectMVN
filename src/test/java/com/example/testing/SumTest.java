package com.example.testing;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumTest {

    @Test
    void testAddition() {
        int expected = 15;
        int actual = CalculatorService.sum(10, 5);
        Assert.assertEquals(actual, expected, "Sum of 10 + 5 = 15");
    }
}

