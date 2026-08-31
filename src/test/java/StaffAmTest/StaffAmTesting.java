package StaffAmTest;

import StaffAm.HomePage;
import StaffAm.JobResultPage;
import StaffAm.OpenWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class StaffAmTesting {
    @Test
    public void WebpageTest() {
        WebDriver driver = new ChromeDriver();
        OpenWebPage webPage = new OpenWebPage(driver);
        webPage.openPage("https://staff.am/");
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookiesIfPresent();
        homePage.clickOnSwitchButton();
        homePage.clickOnAllCategories();
        homePage.selectCategoryField();
        homePage.clickOnSearchButton();
        JobResultPage jobResultPage = new JobResultPage(driver);
        jobResultPage.clearFiltersIconIsDisplayed();
        jobResultPage.clickOnClearFiltersIcon();
        jobResultPage.clearFiltersIconIsNotDisplayed();
        jobResultPage.closeWebPage();
    }
}
