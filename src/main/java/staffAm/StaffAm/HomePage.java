package staffAm.StaffAm;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import staffAm.BasePage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @BeforeMethod
    public void openPage(String url) {
        driver.get(url);
    }

    private By switchToStandartPageButtonLoc =
            By.xpath("//button[contains(text(),'Switch to standard search')]");
    private By allCategorySelectFieldButton = By.xpath("//span[@class='ant-select-selection-wrap']");
    private List<By> categorySelectFiledLoc =
            Arrays.asList(By.xpath("//div[@title='Administrative/office-work']"),
                    By.xpath("//div[@title='All categories']"),
                    By.xpath("//div[@title='AI']"),
                    By.xpath("//div[@title='Accounting/Bookkeeping/Cash register'"));

    private By searchButton = By.xpath("//img[@alt='search-icon']");
    private By cookieAcceptButton = By.xpath("//button[@aria-label='close']");


    public void clickOnSwitchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(switchToStandartPageButtonLoc)).click();
    }
    public void acceptCookiesIfPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (TimeoutException e) {
            System.out.println("Cookie popup is not present.");

        }
    }

    public void clickOnAllCategories(){
        wait.until(ExpectedConditions.elementToBeClickable(allCategorySelectFieldButton)).click();
    }

    public void selectCategoryField(){
        Random randomCategory = new Random();
        By randomLocator = categorySelectFiledLoc.get(randomCategory.nextInt(categorySelectFiledLoc.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomLocator)).click();
    }

    public void clickOnSearchButton(){
        driver.findElement(searchButton).click();
    }

    @AfterMethod
    public void closeWebPage(){
        driver.quit();
    }

}
