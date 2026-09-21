package staffAmTest.filtersPageTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import staffAm.businessPage.JobAnnouncement;
import staffAm.staffAmFilters.FiltersPage;
import staffAm.staffAmFilters.HomepageNew;
import staffAmTest.BaseTest;

import java.util.List;

public class FiltersPageTest extends BaseTest {
    private FiltersPage filtersPage;

    @Test(dataProvider = "JobsFiltersData", dataProviderClass = FiltersPage.class)
    public void testCategoryFilterClick(String headName, String filterName) {
        HomepageNew homepage = new HomepageNew(driver);
        homepage.openPage()
                .acceptCookies();
        filtersPage = homepage.clickJobsButton()
                .clickViewMoreIfExists(headName)
                .selectFilterItem(headName, filterName);
        String jobsCount = filtersPage.getOptionCountText(headName, filterName);
        filtersPage.waitForJobsToRefresh();
        String jobsResultsCount = filtersPage.getSizeOfJobs();
        filtersPage.isNoJobsMessageDisplayedIfEmpty();
        Assert.assertEquals(jobsResultsCount, jobsCount, "Jobs counts aren't equal");
    }


    @Test
    public void testFiltersFunctions() {
        HomepageNew homepage = new HomepageNew(driver);
        homepage.openPage()
                .acceptCookies();
        filtersPage = homepage.clickJobsButton();
        filtersPage.clickViewMoreIfExists("Job category");
        filtersPage.selectFilterItem("Job category", "Banking/credit");
        filtersPage.waitForJobsToRefresh();
        List<JobAnnouncement> jobList = filtersPage.getJobAnnouncementsDetails();
        Assert.assertFalse(jobList.isEmpty(), "JobList is empty!");
        Assert.assertTrue(jobList.getFirst().getCompanyName().toLowerCase().contains("bank"),
                "Company Name does not contain the keyword 'bank'");
    }
}
