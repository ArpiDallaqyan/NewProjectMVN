package homework_xPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTesting {
    @Test
    public void cartTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath
                ("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        String expectedName = driver.findElement(By.xpath("//a[@id='item_3_title_link']")).getText();
        driver.findElement(By.xpath
                ("//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']")).click();
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();
        String expectedPrice = driver.findElement(By.xpath
                ("//div[@class='inventory_item_price']")).getText();
        System.out.println(driver.findElement
                (By.xpath("//div[@id='shopping_cart_container']")).isDisplayed());
        String actualName = driver.findElement(By.xpath("//a[@id='item_3_title_link']")).getText();
        driver.findElement(By.xpath("//a[@id='item_3_title_link']")).click();
        String actualPrice = driver.findElement(By.xpath
                ("//div[@class='inventory_details_price']")).getText();
        Assert.assertEquals(actualPrice, expectedPrice);
        Assert.assertEquals(actualName, expectedName);

    }
}
