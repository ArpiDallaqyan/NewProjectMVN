package staffAm.StaffAm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import staffAm.BaseClass;

import java.time.Duration;

public class JobResultPage extends BaseClass {
    private By clearFiltersIcon = By.xpath("//img[@alt='close-icon']");

    public JobResultPage(WebDriver driver) {
        super(driver);
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
