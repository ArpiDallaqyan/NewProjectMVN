package staffAmTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import staffAm.driver.Driver;
import io.qameta.allure.testng.AllureTestNg;
import java.util.Properties;

@Listeners(AllureTestNg.class)
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

    public WebDriver getDriver() {
            return driver;
    }
}

