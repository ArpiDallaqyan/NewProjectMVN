package staffAm_TC2;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class JobsAnnouncementPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By searchPlaceholderLoc = By.xpath("//input[@placeholder='Enter keywords...']");
    private By searchButtonLog = By.xpath("//div[text()='Search']");
    private By clearFiltersLoc = By.xpath("//div[text()='Clear filters']");
    private By currentJobsOpeningsTextLoc = By.xpath("//h1[text()='Current Job Openings']");
    private By noJobsMessageLoc =
            By.xpath("//*[contains(text(), 'No jobs') or contains(text()," +
                    " 'Your search returned no results. Please try using different keywords.')]");
    private By cookieAcceptButton = (By.xpath("//div[contains(text(), 'We use cookies')]"));


    public JobsAnnouncementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void searchForJob(String key) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchPlaceholderLoc));
        searchInput.sendKeys(Keys.CONTROL + "a");
        searchInput.sendKeys(Keys.BACK_SPACE);
        if (!key.isEmpty()) {
            searchInput.sendKeys(key);
        }

    }
    public void pressEnter() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchPlaceholderLoc));
        element.sendKeys(Keys.ENTER);
    }

    public void clickToSearchButton() {
        WebElement searchBtn = wait.until(ExpectedConditions.presenceOfElementLocated(searchButtonLog));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        } catch (ElementClickInterceptedException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", searchBtn);
        }
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

    public void scrollToElement(By locator) {
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

    public boolean isDataLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(currentJobsOpeningsTextLoc));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isNoJobsMessageVisible(){
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
