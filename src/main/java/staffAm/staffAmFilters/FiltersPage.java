package staffAm.staffAmFilters;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import staffAm.BasePage;
import java.time.Duration;
import java.util.List;

public class FiltersPage extends BasePage {

    private By jobsTitles = By.xpath("//div[@id='ai-results-anchor']//following-sibling::div//a[@target]//div");
    private By noJobsMessage =  By.xpath("//*[contains(text(), 'No jobs') or contains(text(), 'no results')]");


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

    public void clickViewMoreIfExists(String sectionHeader) {
        By viewMoreLoc = getViewMoreLocator(sectionHeader);
        try {
            WebElement element = shortWait.until(ExpectedConditions.presenceOfElementLocated(viewMoreLoc));
            new Actions(driver).scrollToElement(element).perform();
            shortWait.until(ExpectedConditions.elementToBeClickable(viewMoreLoc)).click();
        } catch (TimeoutException e) {
            return;
        }
    }

    public void selectFilterItem(String categoryHeadName, String categoryFilter) {
        By filterLoc = getCategorySiblingsLocator(categoryHeadName, categoryFilter);
        clickToElement(filterLoc);
    }

    @DataProvider(name = "JobsFiltersData")
    public static Object[][] getCategoryFilterData() {
        return new Object[][] {
                {"Job category", "Banking/credit"},
                {"Job special tag", "Fresh graduates"},
                {"Specialist level", "Student"},
                {"Job salary", "Mentioned"},
                {"Job types", "Full time"},
                {"Job terms", "Other"},
                {"By cities", "Yerevan"}
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

    public void waitForJobsToRefresh() {
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(jobsTitles)
        ));
    }
}
