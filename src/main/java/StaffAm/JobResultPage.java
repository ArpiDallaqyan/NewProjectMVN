package StaffAm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class JobResultPage {
    private WebDriver driver;
    private By clearFiltersIcon = By.xpath("//img[@alt='close-icon']");

    public JobResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clearFiltersIconIsDisplayed(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(clearFiltersIcon));
    }

    public void clearFiltersIconIsNotDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Assert.assertTrue(
                wait.until(ExpectedConditions.invisibilityOfElementLocated(clearFiltersIcon))
        );
    }
    public void clickOnClearFiltersIcon(){
       driver.findElement(clearFiltersIcon).click();
    }
    public void closeWebPage(){
        driver.quit();
    }
}
