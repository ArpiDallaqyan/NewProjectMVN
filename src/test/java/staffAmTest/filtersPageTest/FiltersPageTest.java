package staffAmTest.filtersPageTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import staffAm.businessPage.JobAnnouncement;
import staffAm.staffAmFilters.FiltersGroupName;
import staffAm.staffAmFilters.FiltersPage;
import staffAm.staffAmFilters.HomepageNew;
import staffAmTest.BaseTest;

import java.util.List;

public class FiltersPageTest extends BaseTest {
    private FiltersPage filtersPage;

    @Test(dataProvider = "JobsFiltersData", dataProviderClass = FiltersPage.class)
    public void testCategoryFilterClick(FiltersGroupName filterGroup, String filterName) {
        HomepageNew homepage = new HomepageNew(driver);
        homepage.acceptCookies();
        String headName = filterGroup.getNameInJobsPage();
        filtersPage = homepage.clickJobsButton()
                .clickViewMoreIfExists(headName)
                .selectFilterItem(headName, filterName);
        filtersPage.waitForJobsToRefresh();
        if (!filtersPage.isNoJobsMessageDisplayedWhenEmpty()) {
            filtersPage.clickFirstJob();
            boolean isFilterValid = filtersPage.isFilterCorrectInJobDetails(filterGroup, filterName);
            Assert.assertTrue(isFilterValid,
                    "Mismatch in Job Details! Filter Group: " + headName + " | Expected: " + filterName);
        }
    }
}
