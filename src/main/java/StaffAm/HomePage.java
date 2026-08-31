package StaffAm;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    private By switchToStandartPageButtonLoc =
            By.xpath("//button[contains(text(),'Switch to standard search')]");
    private By allCategorySelectFieldButton = By.xpath("//span[@class='ant-select-selection-wrap']");
    private By categorySelectFiledLoc = By.xpath("//div[@title='Administrative/office-work']");
    private By searchButton = By.xpath("//img[@alt='search-icon']");
    private By cookieAcceptButton = By.xpath("//button[@aria-label='close']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnSwitchButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until
                (ExpectedConditions.elementToBeClickable(switchToStandartPageButtonLoc)).click();
    }
    public void acceptCookiesIfPresent() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    ExpectedConditions.elementToBeClickable(cookieAcceptButton)
            ).click();
        } catch (TimeoutException e) {
            System.out.println("Cookie popup is not present.");

        }
    }

    public void clickOnAllCategories(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until
                (ExpectedConditions.elementToBeClickable(allCategorySelectFieldButton)).click();
    }

    public void selectCategoryField(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until
                (ExpectedConditions.elementToBeClickable(categorySelectFiledLoc)).click();
    }

    public void clickOnSearchButton(){
        driver.findElement(searchButton).click();
    }

}
