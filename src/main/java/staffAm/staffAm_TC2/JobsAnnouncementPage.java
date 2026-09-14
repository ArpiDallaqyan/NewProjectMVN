package staffAm.staffAm_TC2;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import staffAm.BaseClass;

import java.time.Duration;

public class JobsAnnouncementPage extends BaseClass {
    private By searchPlaceholderLoc = By.xpath("//input[@placeholder='Enter keywords...']");
    private By searchButtonLog = By.xpath("//div[text()='Search']");
    private By clearFiltersLoc = By.xpath("//div[text()='Clear filters']");
    private By noJobsMessageLoc =
            By.xpath("//*[contains(text(), 'No jobs') or contains(text()," +
                    " 'Your search returned no results. Please try using different keywords.')]");
    private By cookieAcceptButton = (By.xpath("//div[contains(text(), 'We use cookies')]"));
    private By jobTitleText = By.xpath("//div[@id='ai-results-anchor']" +
            "//following-sibling::div//a[@target]//div");


    public JobsAnnouncementPage(WebDriver driver) {
        super(driver);
    }

    public void searchForJob(String key) {
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchPlaceholderLoc));
        searchInput.sendKeys(Keys.CONTROL + "a");
        searchInput.sendKeys(Keys.BACK_SPACE);
        searchInput.sendKeys(key);

    }
    public void pressEnter() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchPlaceholderLoc));
        element.sendKeys(Keys.ENTER);
    }

    public void clickToSearchButton() {
        scrollToElement(searchButtonLog);
        wait.until(ExpectedConditions.elementToBeClickable(searchButtonLog)).click();
    }
    public void scrollToSearchInput() {
        scrollToElement(searchPlaceholderLoc);
    }

    public boolean isClearFiltersVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(clearFiltersLoc));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public boolean isClearFiltersInvisible() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(clearFiltersLoc));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo({top: 0, behavior: 'instant'});");
    }

    private void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);
    }

    public void acceptCookies() {
        try {
            driver.findElement(cookieAcceptButton).click();
        } catch (Exception e) {

        }
    }
    public String getText(){
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(jobTitleText))
                .getText());

    }

    public boolean isDataLoaded() {
        return wait.until(ExpectedConditions.invisibilityOfElementWithText(jobTitleText, getText()));

    }

    public boolean isNoJobsMessageVisible() {
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(noJobsMessageLoc));
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }


    public void clearFilters(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(clearFiltersLoc)).click();
    }
}
