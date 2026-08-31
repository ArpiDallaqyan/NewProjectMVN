package StaffAm;

import org.openqa.selenium.WebDriver;

public class OpenWebPage {
    private WebDriver driver;

    public OpenWebPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(String url) {
        driver.get(url);
    }
}
