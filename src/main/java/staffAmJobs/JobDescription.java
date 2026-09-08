package staffAmJobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JobDescription {
    private WebDriver driver;
    private WebDriverWait wait;

    private By expectedLocation = By.xpath("//img[contains(@src, 'location')]/following::div[@dir='auto'][1]");
    private By expectedJobTitle = By.xpath("//h1[@role='heading']");
    private By expectedCompanyName = By.xpath("//a[contains(@href, '/company/')]" +
            "//div[@dir='auto' and contains(@style, 'font-weight: bold')] ");
    private By expectedDate = By.xpath("//img[contains(@src, 'calendar')" +
            " or contains(@alt, 'calendar')]/ancestor::div[2]");

    public JobDescription(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    public String getJobTitle(){
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(expectedJobTitle))
                .getAttribute("textContent"));
    }
    public String getCompanyName(){
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(expectedCompanyName))
                .getAttribute("textContent"));
    }
    public String getLocation(){
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(expectedLocation))
                .getAttribute("textContent"));

    }
    public String getDate(){
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(expectedDate))
                .getAttribute("textContent"));

    }




}
