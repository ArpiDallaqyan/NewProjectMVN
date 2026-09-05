package StaffAm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JobResultPage {
    private WebDriver driver;
    private By clearFiltersIcon = By.xpath("//img[@alt='close-icon']");
    private WebDriverWait wait ;

    public JobResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clearFiltersIconIsDisplayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(clearFiltersIcon));
    }

    public boolean clearFiltersIconIsNotDisplayed(){
       return wait.until(ExpectedConditions.invisibilityOfElementLocated(clearFiltersIcon));
    }

    public void clickOnClearFiltersIcon(){
       wait.until(ExpectedConditions.visibilityOfElementLocated(clearFiltersIcon)).click();
    }
}
