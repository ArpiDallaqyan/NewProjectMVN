package staffAmJobs;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;


public class JobsResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By cookieAcceptButton = (By.xpath("//div[contains(text(), 'We use cookies')]"));
    private By jobs = By.xpath("//img[@alt='left-icon']/ancestor::div[3]");
    private By expectedLocation = By.xpath("//img[contains(@src, 'location')]/following::div[@dir='auto'][1]");
    private By expectedJobTitle = By.xpath("//h1[@role='heading']");
    private By expectedCompanyName = By.xpath("//a[contains(@href, '/company/')]" +
            "//div[@dir='auto' and contains(@style, 'font-weight: bold')] ");
    private By expectedDate = By.xpath("//img[contains(@src, 'calendar')" +
            " or contains(@alt, 'calendar')]/ancestor::div[2]");
    private By viewMoreBtn = By.xpath(".//*[contains(text(),'View more')]");

    public JobsResultsPage(WebDriver driver) {
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

    public void acceptCookies() {
        try {
            driver.findElement(cookieAcceptButton).click();
        } catch (Exception e) {
        }
    }

    public void clickToJobsAdd() throws InterruptedException {
        List<WebElement> jobElements = driver.findElements(jobs);
        int index = new Random().nextInt(jobElements.size());
        WebElement job = jobElements.get(index);
        wait.until(ExpectedConditions.visibilityOf(job));
        WebElement viewMoreElement = job.findElement(viewMoreBtn);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", viewMoreElement);
        new Actions(driver).moveToElement(viewMoreElement).perform();
        wait.until(ExpectedConditions.elementToBeClickable(viewMoreElement)).click();
    }

}






