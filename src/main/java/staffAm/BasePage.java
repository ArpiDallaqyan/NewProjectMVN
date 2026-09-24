package staffAm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import staffAm.staffAmFilters.FiltersPage;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;
    protected By cookieAcceptButton = (By.xpath("//div[contains(text(), 'We use cookies')]"));

    @FindBy(xpath = "//div[text()='Jobs']")
    private WebElement jobsButton;

    public FiltersPage clickJobsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(jobsButton)).click();
        return new FiltersPage(driver);
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (Exception e) {
            return;
        }
    }
}
