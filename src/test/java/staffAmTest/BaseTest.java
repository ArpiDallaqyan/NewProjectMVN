package staffAmTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import staffAm.driver.Driver;

import java.util.Properties;

public class BaseTest {

        protected WebDriver driver;
        private Properties properties;

        @BeforeMethod
        public void setUp() {
            properties = Driver.initProperties();
            driver = Driver.initDriver(properties, "browser");

            driver.get(properties.getProperty("url"));
        }

        @AfterMethod
        public void tearDown() {
            Driver.quitDriver();
        }
    }

