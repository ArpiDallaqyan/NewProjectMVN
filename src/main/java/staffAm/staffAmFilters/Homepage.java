package staffAm.staffAmFilters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import staffAm.BasePage;

public class Homepage extends BasePage {

    @FindBy(xpath = "//div[text()='Jobs']")
    private WebElement jobsButton;

    public Homepage(WebDriver driver) {
        super(driver);
    }

    public void clickJobsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(jobsButton)).click();
    }

}
