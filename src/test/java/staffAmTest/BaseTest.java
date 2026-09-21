package staffAmTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import staffAm.driver.Driver;

import java.time.Duration;

public class BaseTest {

        protected WebDriver driver;

        @BeforeMethod
        public void setUp() {
            driver = Driver.getDriverEdge();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://staff.am/");
        }

        @AfterMethod
        public void tearDown() {
            Driver.quitDriver();
        }
    }

