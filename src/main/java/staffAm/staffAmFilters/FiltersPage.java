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
    private By noJobsMessage = By.xpath("//*[contains(text(), 'No jobs') or contains(text(), 'no results')]");
    private By location = By.xpath("//img[contains(@src, 'location')]/following::div[@dir='auto'][1]");
    private By jobTitle = By.xpath("//h1[@role='heading']");
    private By companyName = By.xpath("//div[@id='ai-results-anchor'" +
            "]/following-sibling::div//a[contains(@href, '/company/')]/div[@dir='auto']");
    private By date = By.xpath("//img[contains(@src, 'calendar')" +
            " or contains(@alt, 'calendar')]/ancestor::div[2]");
    private By jobCardContainer = By.xpath("//img[@alt='left-icon']/ancestor::div[3]");

    String jobDetailsFiltersLocText = "//div[normalize-space()='%s']/following-sibling::*[1]";
    String viewMoreDynamicLocText = "//div[text()='%s']/following-sibling::div[@tabindex='0']";
    String categorySiblingsLocText = "//div[text()='%s']/following-sibling::div[not(@tabindex='0')]//span[text()='%s']//span";


    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    private By getCategorySiblingsLocator(String categoryHeadName, String categoryFilter) {
        String xpath = String.format(categorySiblingsLocText, categoryHeadName, categoryFilter);
        return By.xpath(xpath);
    }

    public void clickToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        new Actions(driver).scrollToElement(element).perform();
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private By getViewMoreLocator(String sectionHeader) {
        String xpath = String.format(viewMoreDynamicLocText, sectionHeader);
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

    public FiltersPage clickFirstJob() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(jobsTitles));
        List<WebElement> jobs = driver.findElements(jobsTitles);
        if (!jobs.isEmpty()) {
            String originalWindow = driver.getWindowHandle();
            WebElement firstJob = jobs.get(0);
            new Actions(driver).scrollToElement(firstJob).perform();
            firstJob.click();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!originalWindow.contentEquals(windowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
        } else {
            throw new NoSuchElementException("No jobs found for the selected filter");
        }
        return this;
    }

    public String getJobDetailValueText(FiltersGroupName filterGroup) {
        By locator = getJobDetailsFilterLocator(filterGroup);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Actions(driver).scrollToElement(element).perform();
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText().trim();
    }

    public boolean isFilterCorrectInJobDetails(FiltersGroupName filterGroup, String expectedFilterValue) {
        String actualDetailText = getJobDetailValueText(filterGroup);
        return actualDetailText.toLowerCase().contains(expectedFilterValue.toLowerCase());
    }

    private By getJobDetailsFilterLocator(FiltersGroupName filterGroup) {
        String label = filterGroup.getNameInJobsDetailsPage();
        String xpath = String.format(jobDetailsFiltersLocText, label);
        return By.xpath(xpath);
    }

    public FiltersPage selectFilterItem(String categoryHeadName, String categoryFilter) {
        By filterLoc = getCategorySiblingsLocator(categoryHeadName, categoryFilter);
        clickToElement(filterLoc);
        return this;
    }

    @DataProvider(name = "JobsFiltersData")
    public static Object[][] getCategoryFilterData() {
        return new Object[][]{
                {FiltersGroupName.JOB_CATEGORY, "Legal"},
                {FiltersGroupName.SPECIALIST_LEVEL, "Student"},
                {FiltersGroupName.JOB_TERMS, "Freelance"}
        };
    }

    public boolean isNoJobsMessageDisplayedWhenEmpty() {
        try {
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(noJobsMessage)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
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

