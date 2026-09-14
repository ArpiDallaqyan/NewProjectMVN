package staffAm.staffAmFilters;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import staffAm.BaseClass;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FiltersPage extends BaseClass {
    private List<By> jobCategory = List.of(By.xpath(
            "//div[text()='Job category']/following-sibling::div[not(@tabindex='0')]"));
    private List<By> jobSpecialTaf = List.of(By.xpath
            ("//div[text()='Job special tag']/following-sibling::div[not(@tabindex='0')]"));
    private List<By> specialistLevel = List.of(By.xpath
            ("//div[text()='Specialist level']/following-sibling::div[not(@tabindex='0')]"));
    private By cookieAcceptButton = (By.xpath("//div[contains(text(), 'We use cookies')]"));
    private By jobsTitles = By.xpath("//div[@id='ai-results-anchor']//following-sibling::div//a[@target]//div");


    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    public void selectJobCategory(){
        Random randomCategory = new Random();
        By randomLocator = jobCategory.get(randomCategory.nextInt(jobCategory.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomLocator)).click();
    }

    public By getCategorySiblingsLocator(String categoryHeadName, String categoryFilter) {
        String xpath = String.format("//div[text()='%s']/following-sibling::div[not(@tabindex='0')]" +
                "//span[text()='%s']//span", categoryHeadName, categoryFilter);
        return By.xpath(xpath);
    }

    public void clickToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public By getViewMoreLocator(String sectionHeader) {
        String xpath = String.format("//div[text()='%s']/following-sibling::div[@tabindex='0']", sectionHeader);
        return By.xpath(xpath);
    }

    public void clickViewMoreIfExists(String sectionHeader) {
        By viewMoreLoc = getViewMoreLocator(sectionHeader);
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement element = shortWait.until(ExpectedConditions.presenceOfElementLocated(viewMoreLoc));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            js.executeScript("arguments[0].click();", element);
        } catch (TimeoutException e) {
        }
    }

    public void selectFilterItem(String categoryHeadName, String categoryFilter) {
        By filterLoc = getCategorySiblingsLocator(categoryHeadName, categoryFilter);
        clickToElement(filterLoc);
    }

    public String getText(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element.getAttribute("textContent").replaceAll("[^0-9]", "");

    }

    public String getOptionCountText(String categoryName, String categoryFilter) {
        By locator = getCategorySiblingsLocator(categoryName, categoryFilter);
        return getText(locator);
    }

    public String getSizeOfJobs(){
        List<WebElement> jobs = driver.findElements(jobsTitles);
        return String.valueOf(jobs.size());
    }

    public void waitForJobsToRefresh() {
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(jobsTitles)
        ));
    }

    public void acceptCookies() {
        try {
            driver.findElement(cookieAcceptButton).click();
        } catch (Exception e) {

        }
    }
}
