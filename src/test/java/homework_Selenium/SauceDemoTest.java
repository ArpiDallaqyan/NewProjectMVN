package homework_Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SauceDemoTest {

    @Test
    public void loginTest() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com/");
        WebElement element = webDriver.findElement(By.id("user-name"));
        element.sendKeys("standard_user");
        WebElement element1 = webDriver.findElement(By.id("password"));
        element1.sendKeys("secret_sauce");
        WebElement element2 = webDriver.findElement(By.id("login-button"));
        element2.click();
        WebElement item = webDriver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
        item.click();
        webDriver.quit();


    }
}
