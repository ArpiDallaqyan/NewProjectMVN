package staffAmTest.filtersPageTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import staffAm.staffAmFilters.FiltersPage;

public class FiltersPageTest {
    private WebDriver driver;
    private FiltersPage filtersPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staff.am/jobs");
        filtersPage = new FiltersPage(driver);
        filtersPage.acceptCookies();
    }

    @Test
    public void testFiltersFunctions(){
        filtersPage.clickViewMoreIfExists("Specialist level");
        filtersPage.selectFilterItem("Specialist level", "Junior");
        filtersPage.clickViewMoreIfExists("Job salary");
        filtersPage.selectFilterItem("Job salary", "Mentioned");
        String jobsCount = filtersPage.getOptionCountText("Job salary","Mentioned");
        filtersPage.waitForJobsToRefresh();
        String jobsResultsCount = filtersPage.getSizeOfJobs();
        Assert.assertEquals(jobsCount, jobsResultsCount, "Jobs counts aren't equal");


    }

    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
