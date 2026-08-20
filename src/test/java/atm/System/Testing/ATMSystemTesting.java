package atm.System.Testing;

import atm.System.ATMSystem;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ATMSystemTesting {
    @BeforeMethod
    public void insertCard(){
        System.out.println("Card Inserted");
    }
    @AfterMethod
    public void ejectCard(){
        System.out.println("Card ejected");
    }

    @Test(groups = {"equivalence"}, dependsOnMethods = {"testMaximumValidAmount()"}, priority = 1)
    public void testValidAmount() {
        boolean actual = ATMSystem.testValidAmount(15000);
        Assert.assertTrue(actual, "Valid Amount");
    }

    @Test(groups = {"equivalence"})
    public void testTooLowAmount() {
        boolean actual = ATMSystem.tooLowAmount(50);
        Assert.assertTrue(actual, "Too Low Amount");
    }

    @Test(groups = {"equivalence"})
    public void testTooHighAmount() {
        boolean actual = ATMSystem.tooHighAmount(25000);
        Assert.assertTrue(actual, "Too High Amount");
    }

    @Test(groups = {"boundary"})
    public void testBoundaryLowValue() {
        boolean actual = ATMSystem.boundaryLowValue(99);
        Assert.assertTrue(actual);
    }

    @Test(groups = {"boundary"})
    public void testMinimumValidAmount() {
        boolean actual = ATMSystem.minimumValidAmount(100);
        Assert.assertTrue(actual);
    }

    @Test(groups = {"boundary"}, priority = 0)
    public void testMaximumValidAmount() {
        boolean actual = ATMSystem.maximalValidAmount(20000);
        Assert.assertTrue(actual);
}
}
