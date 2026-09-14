package staffAmTest.StaffAmTest;

import staffAm.StaffAm.HomePage;
import staffAm.StaffAm.JobResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class StaffAmTesting {

    @Test
    public void WebpageTest() {
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
        homePage.openPage("https://staff.am/");
        homePage.acceptCookiesIfPresent();
        homePage.clickOnSwitchButton();
        homePage.clickOnAllCategories();
        homePage.selectCategoryField();
        homePage.clickOnSearchButton();
        JobResultPage jobResultPage = new JobResultPage(driver);
        jobResultPage.clearFiltersIconIsDisplayed();
        jobResultPage.clickOnClearFiltersIcon();
        jobResultPage.clearFiltersIconIsNotDisplayed();
        homePage.closeWebPage();
    }
}
