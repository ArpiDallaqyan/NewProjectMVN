package staffAmJobsTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import staffAmJobs.JobDescription;
import staffAmJobs.JobsResultsPage;

public class StaffAmJobsTest {
    WebDriver driver;
    JobsResultsPage jobsResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        String url = "https://staff.am/jobs";
        driver.get(url);
    }
    @Test
    public void testJobsResults() throws InterruptedException {
        jobsResultsPage = new JobsResultsPage(driver);
        jobsResultsPage.acceptCookies();
        jobsResultsPage.clickToJobsAdd();
        JobDescription jobDescription = new JobDescription(driver);
        Assert.assertEquals(jobsResultsPage.getJobTitle(), jobDescription.getJobTitle());
    }

    @AfterMethod
    public void closeWebPage(){
        driver.quit();
        driver = null;
    }

}
