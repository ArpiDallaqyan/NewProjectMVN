package staffAm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public BaseClass(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
}
