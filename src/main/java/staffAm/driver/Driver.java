package staffAm.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Driver {
    private static WebDriver driver;
    private static Properties properties;

    public static Properties initProperties() {
        properties = new Properties();
        try (InputStream input = Driver.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties file.", e);
        }
        return properties;
    }

    public static WebDriver initDriver(Properties prop, String browserKey) {
        String browserName = prop.getProperty(browserKey, "chrome").trim().toLowerCase();

        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
            case "ff":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser for key '" + browserKey + "': " + browserName);
        }

        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}