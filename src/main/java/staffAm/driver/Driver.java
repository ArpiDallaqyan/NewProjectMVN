package staffAm.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Driver {
    private static WebDriver driver;

    public static WebDriver getDriverChrome(){
        if(driver == null){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getDriverEdge(){
        if(driver == null){
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--incognito");

            driver = new EdgeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
