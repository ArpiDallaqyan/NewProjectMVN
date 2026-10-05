package staffAm.staffAmJobs;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import staffAm.BasePage;
import staffAm.businessPage.JobAnnouncement;

import java.util.List;
import java.util.Random;


public class JobsResultsPage extends BasePage {
    private By cookieAcceptButton = (By.xpath("//div[contains(text(), 'We use cookies')]"));
    private By jobs = By.xpath("//img[@alt='left-icon']/ancestor::div[3]");
    private By location = By.xpath("//img[contains(@src, 'location')]/following::div[@dir='auto'][1]");
    private By jobTitle = By.xpath("//h1[@role='heading']");
    private By companyName = By.xpath("//div[@id='ai-results-anchor'" +
            "]/following-sibling::div//a[contains(@href, '/company/')]/div[@dir='auto']");
    private By date = By.xpath("//img[contains(@src, 'calendar')" +
            " or contains(@alt, 'calendar')]/ancestor::div[2]");
    private By viewMoreBtn = By.xpath(".//*[contains(text(),'View more')]");

    public JobsResultsPage(WebDriver driver) {
        super(driver);
    }

    public JobAnnouncement getJobAnnouncementDetails(){
        String jobTitle = getJobTitle();
        String companyName = getCompanyName();
        String location = getLocation();
        String date = getDate();
        return new JobAnnouncement(jobTitle, companyName, location, date);
    }

    public String getJobTitle(){
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(jobTitle))
                .getAttribute("textContent"));
    }
    public String getCompanyName(){
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(companyName))
                .getAttribute("textContent"));
    }
    public String getLocation(){
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(location))
                .getAttribute("textContent"));
    }
    public String getDate(){
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(date))
                .getAttribute("textContent"));

    }

    public void acceptCookies() {
        try {
            driver.findElement(cookieAcceptButton).click();
        } catch (Exception e) {
        }
    }


    public void clickToJobsAdd(int index) {
        List<WebElement> jobElements = driver.findElements(jobs);
        WebElement job = jobElements.get(index);
        wait.until(ExpectedConditions.visibilityOf(job));
        WebElement viewMoreElement = job.findElement(viewMoreBtn);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", viewMoreElement);

        new Actions(driver).moveToElement(viewMoreElement).perform();
        wait.until(ExpectedConditions.elementToBeClickable(viewMoreElement)).click();
    }

    public void clickToJobsAdd() {
        List<WebElement> jobElements = driver.findElements(jobs);
        if (jobElements.isEmpty()) {
            throw new RuntimeException("No job elements found with locator: " + jobs);
        }
        int randomIndex = new Random().nextInt(jobElements.size());
        clickToJobsAdd(randomIndex);
    }
}