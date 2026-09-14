package staffAmTest.staffAmTC2Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import staffAm.staffAm_TC2.JobsAnnouncementPage;

public class FindJobsTest {
    private WebDriver driver;
    private JobsAnnouncementPage jobsAnnouncementPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staff.am/jobs");

        jobsAnnouncementPage = new JobsAnnouncementPage(driver);
        jobsAnnouncementPage.acceptCookies();
    }

    @Test
    public void jobsPageSearchTest() {
        jobsAnnouncementPage.scrollToSearchInput();
        jobsAnnouncementPage.searchForJob("IT");
        jobsAnnouncementPage.clickToSearchButton();
        Assert.assertTrue(jobsAnnouncementPage.isClearFiltersVisible(), "Clear filters button should be visible");
        Assert.assertTrue(jobsAnnouncementPage.isDataLoaded(), "First job's name should be changed");
        jobsAnnouncementPage.searchForJob("HR");
        jobsAnnouncementPage.pressEnter();
        Assert.assertTrue(jobsAnnouncementPage.isClearFiltersVisible(), "Clear filters button should be visible after HR search");
        Assert.assertTrue(jobsAnnouncementPage.isDataLoaded(), "Job openings header should be visible after HR search");
        jobsAnnouncementPage.searchForJob("asdfhefv");
        jobsAnnouncementPage.scrollToTop();
        jobsAnnouncementPage.clickToSearchButton();
        Assert.assertTrue(jobsAnnouncementPage.isNoJobsMessageVisible(), "'No jobs' message should be displayed");
        jobsAnnouncementPage.searchForJob("");
        jobsAnnouncementPage.pressEnter();
        Assert.assertTrue(jobsAnnouncementPage.isClearFiltersInvisible(), "Clear filters button should be invisible when filters cleared");
    }

    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}