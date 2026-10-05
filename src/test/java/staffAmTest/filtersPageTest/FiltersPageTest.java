package staffAmTest.filtersPageTest;

import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
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

    @Description("Verify that user can filter jobs by category")
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
