package staffAm.staffAmFilters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import staffAm.BasePage;

public class HomepageNew extends BasePage {

    @FindBy(xpath = "//div[text()='Jobs']")
    private WebElement jobsButton;

    public HomepageNew(WebDriver driver) {
        super(driver);
    }

    public HomepageNew openPage() {
        driver.get("https://staff.am");
        return this;
    }
    public FiltersPage clickJobsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(jobsButton)).click();
        return new FiltersPage(driver);
    }
}
