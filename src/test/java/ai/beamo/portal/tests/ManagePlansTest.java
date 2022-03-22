package ai.beamo.portal.tests;

import ai.beamo.portal.library.TestBase;
import ai.beamo.portal.library.ThreadSafeWebDriverStorage;
import ai.beamo.portal.pages.LoginPage;
import ai.beamo.portal.pages.SiteListPage;
import ai.beamo.portal.pages.SiteProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class ManagePlansTest extends TestBase {

    final String SITE_PLANS = "Test Automation Site - Plans";

    @Test(groups = {"smoke", "plan"})
    public void verifyAddOutdoorPlan() {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();

        try {
            //Login
            LoginPage pLogin = new LoginPage();
            pLogin.clickLogin(driver, SPACE_NAME, "dh.shin+ta_admin@3i.ai", "qwer1234");

            //Search the site
            SiteListPage pSiteList = new SiteListPage();
            pSiteList.searchSite(driver, SITE_PLANS);

            //Add Outdoor Plan
            SiteProfilePage pSiteProfile = new SiteProfilePage(driver);
            pSiteProfile.clickOutdoorPlanBtn();

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }
    }
}