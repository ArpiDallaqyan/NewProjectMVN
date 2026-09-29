package staffAmTest.filtersPageTest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import staffAm.staffAmFilters.FiltersGroupName;
import staffAm.staffAmFilters.FiltersPage;
import staffAm.staffAmFilters.HomepageNew;
import staffAmTest.BaseTest;
import staffAmTest.utils.RetryAnalyzer;
import staffAmTest.utils.TestListener;

@Listeners(TestListener.class)
public class FiltersPageTest extends BaseTest {
    private FiltersPage filtersPage;

    @Test(dataProvider = "JobsFiltersData", dataProviderClass = FiltersPage.class, retryAnalyzer = RetryAnalyzer.class)
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
