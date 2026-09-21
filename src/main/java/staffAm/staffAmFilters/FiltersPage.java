package staffAm.staffAmFilters;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import staffAm.BasePage;
import staffAm.businessPage.JobAnnouncement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FiltersPage extends BasePage {

    private By jobsTitles = By.xpath("//div[@id='ai-results-anchor']//following-sibling::div//a[@target]//div");
    private By noJobsMessage =  By.xpath("//*[contains(text(), 'No jobs') or contains(text(), 'no results')]");
    private By location = By.xpath("//img[contains(@src, 'location')]/following::div[@dir='auto'][1]");
    private By jobTitle = By.xpath("//h1[@role='heading']");
    private By companyName = By.xpath("//div[@id='ai-results-anchor'" +
            "]/following-sibling::div//a[contains(@href, '/company/')]/div[@dir='auto']");
    private By date = By.xpath("//img[contains(@src, 'calendar')" +
            " or contains(@alt, 'calendar')]/ancestor::div[2]");
    private By jobCardContainer = By.xpath("//img[@alt='left-icon']/ancestor::div[3]");


    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    private By getCategorySiblingsLocator(String categoryHeadName, String categoryFilter) {
        String xpath = String.format("//div[text()='%s']/following-sibling::div[not(@tabindex='0')]" +
                "//span[text()='%s']//span", categoryHeadName, categoryFilter);
        return By.xpath(xpath);
    }

    public void clickToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        new Actions(driver).scrollToElement(element).perform();
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private By getViewMoreLocator(String sectionHeader) {
        String xpath = String.format("//div[text()='%s']/following-sibling::div[@tabindex='0']", sectionHeader);
        return By.xpath(xpath);
    }

    public FiltersPage clickViewMoreIfExists(String sectionHeader) {
        By viewMoreLoc = getViewMoreLocator(sectionHeader);
        try {
            WebElement element = shortWait.until(ExpectedConditions.presenceOfElementLocated(viewMoreLoc));
            new Actions(driver).scrollToElement(element).perform();
            shortWait.until(ExpectedConditions.elementToBeClickable(viewMoreLoc)).click();
        } catch (TimeoutException e) {
        }
        return this;
    }

    public FiltersPage selectFilterItem(String categoryHeadName, String categoryFilter) {
        By filterLoc = getCategorySiblingsLocator(categoryHeadName, categoryFilter);
        clickToElement(filterLoc);
        return this;
    }

    @DataProvider(name = "JobsFiltersData")
    public static Object[][] getCategoryFilterData() {
        return new Object[][] {
                {"Job category", "Legal"},
                {"Job special tag", "Fresh graduates"},
                {"Specialist level", "Student"},
                {"Job types", "Training"},
                {"Job terms", "Freelance"},
                {"By cities", "Kapan"}
        };
    }

    public String getReplacedText(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        String countOfElements = element.getAttribute("textContent").replaceAll("[^0-9]", "");
        return countOfElements;
    }

    public String getOptionCountText(String categoryName, String categoryFilter) {
        By locator = getCategorySiblingsLocator(categoryName, categoryFilter);
        return getReplacedText(locator);
    }

    public String getSizeOfJobs(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(jobsTitles));
        List<WebElement> jobs = driver.findElements(jobsTitles);
        return String.valueOf(jobs.size());
    }

    public boolean isNoJobsMessageDisplayedIfEmpty() {
        List<WebElement> jobs = driver.findElements(jobsTitles);
        if (jobs.isEmpty()) {
            try {
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
                return shortWait.until(ExpectedConditions.visibilityOfElementLocated(noJobsMessage)).isDisplayed();
            } catch (TimeoutException e) {
                return false;
            }
        }
        return false;
    }

    public FiltersPage waitForJobsToRefresh() {
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(jobsTitles)
        ));
        return this;
    }


    public List<JobAnnouncement> getJobAnnouncementsDetails() {
        List<WebElement> cards = driver.findElements(jobCardContainer);

        List<JobAnnouncement> announcements = new ArrayList<>();

        for (WebElement card : cards) {
            String titleText = card.findElement(jobTitle).getText();
            String companyText = card.findElement(companyName).getText();
            String locationText = card.findElement(location).getText();
            String dateText = card.findElement(date).getText();

            announcements.add(new JobAnnouncement(titleText, companyText, locationText, dateText));
        }

        return announcements;
    }
}

