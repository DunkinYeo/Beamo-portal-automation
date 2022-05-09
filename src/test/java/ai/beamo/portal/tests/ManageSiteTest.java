package ai.beamo.portal.tests;

import ai.beamo.portal.library.SeleniumBase;
import ai.beamo.portal.library.TestBase;
import ai.beamo.portal.library.ThreadSafeWebDriverStorage;
import ai.beamo.portal.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class ManageSiteTest extends TestBase {

    //private final String SPACE_NAME = "showcase";

    @Test (groups = { "smoke", "site" } )
    public void verifyCreateSite() {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();

        try {
            //Login
            LoginPage pLogin = new LoginPage();
            String loginTitle = pLogin.getPageTitle(driver);
            pLogin.clickLogin(driver, SPACE_NAME, "dh.shin+ta_sitemanager@3i.ai", "qwer1234");

            Thread.sleep(2000);
            //Click "Create Site"
            SiteListPage pSiteList = new SiteListPage();
            WebElement bCreateSite = pSiteList.getPageElement(driver, "CREATE SITE");
            bCreateSite.click();

            Thread.sleep(2000);
            //Input Site Name, Location, Latitude, Longitude
            CreateSiteProfilePage pCreateSiteProfile = new CreateSiteProfilePage();
            WebElement inputSiteName = pCreateSiteProfile.getPageElement(driver, "SITE NAME");
            WebElement inputLocation = pCreateSiteProfile.getPageElement(driver, "LOCATION");
            WebElement inputLatitude = pCreateSiteProfile.getPageElement(driver, "LATITUDE");
            WebElement inputLongitude = pCreateSiteProfile.getPageElement(driver, "LONGITUDE");
            WebElement bCreateSiteInProfile = pCreateSiteProfile.getPageElement(driver, "CREATE SITE");

            inputSiteName.sendKeys("Automated Site");
            inputLocation.sendKeys("216 Sangam-dong, Mapo-gu, Seoul, South Korea");
            inputLatitude.sendKeys("37.5788602");
            inputLongitude.sendKeys("126.8906097");
            bCreateSiteInProfile.click();

            Thread.sleep(2000);
            //Save
            CreateSiteLabelsPage pCreateSiteLabels = new CreateSiteLabelsPage();
            WebElement bSave = pCreateSiteLabels.getPageElement(driver, "SAVE");
            bSave.click();


        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }
    }

    @Test (groups = { "smoke", "site" }, dependsOnMethods = "verifyCreateSite")
    public void verifyDeleteSite() {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            //Login
            LoginPage pLogin = new LoginPage();
            String loginTitle = pLogin.getPageTitle(driver);
            pLogin.clickLogin(driver, SPACE_NAME, "dh.shin+ta_sitemanager@3i.ai", "qwer1234");

            //Check On-boarding Popup
            SiteListPage pSiteList = new SiteListPage();
            if (pSiteList.isElementPresent(driver, "CREATE SITE") == true) {
                driver.findElement(By.xpath("//span[contains(text(),'Got it')]")).click();
            }

            //Search the site
            //SiteListPage pSiteList = new SiteListPage();
            pSiteList.searchSite(driver, "Automated Site");

            //Edit menu
            SiteProfilePage pSiteProfile = new SiteProfilePage(driver);
            WebElement bEditMenu = pSiteProfile.getPageElement(driver, "EDIT MENU");
            bEditMenu.click();

            //Delete
            WebElement bDelete = pSiteProfile.getPageElement(driver, "DELETE");
            bDelete.click();
            //Thread.sleep(2000);

            //Confirm delete

            WebElement bConfirmDelete = pSiteProfile.getPageElement(driver, "CONFIRM DELETE");
            //WebElement bConfirmDelete = driver.findElement(By.xpath("//body/div[2]/div[1]/div[3]/button[2]"));
            //System.out.println("FOUND ELEMENT");
            bConfirmDelete.click();

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }

    }
}
