package ai.beamo.portal.tests;

import ai.beamo.portal.library.TestBase;
import ai.beamo.portal.library.ThreadSafeWebDriverStorage;
import ai.beamo.portal.pages.HeaderPage;
import ai.beamo.portal.pages.LoginPage;

import ai.beamo.portal.pages.SiteListPage;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.concurrent.TimeUnit;

public class LoginTest extends TestBase {

    @DataProvider(name = "Credentials")
    public Object[][] loginCredentials() {
        return new Object[][] {
                { "tmaster", "1234qwer", "MASTER" },
                { "dh.shin+ta_sitemanager@3i.ai", "qwer1234", "SITE MANAGER" },
                { "dh.shin+ta_admin@3i.ai", "qwer1234", "TEAM ADMIN" },
                { "dh.shin+ta_surveyor@3i.ai", "qwer1234", "SURVEYOR" },
                { "dh.shin+ta_collaborator@3i.ai", "qwer1234", "COLLABORATOR" },
                { "dh.shin+ta_viewer@3i.ai", "qwer1234", "VIEWER" }
        };
    }

    @Test (dataProvider = "Credentials", groups = { "smoke" } )
    public void verifyLoginByRoles(String id, String password, String role) {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();

        try {
            verifyLogin(driver, id, password, role);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }

    }

    public void verifyLogin(WebDriver driver, String id, String password, String role) {
        //Page loading timeout (10s)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //Input Space ID, Email, Password and click on login button
        //Check Login page (Title, elements, etc.)
        //Log in
        LoginPage pLogin = new LoginPage();
        String loginTitle = pLogin.getPageTitle(driver);
        assertEquals(loginTitle, "Beamo - Accounts");
        pLogin.clickLogin(driver, "showcase", id, password);

        //Check Beamo Icon
        //Check "Viewer" role text
        HeaderPage pHeader = new HeaderPage();
        assertTrue(pHeader.getPageElement(driver, "BEAMO ICON") != null);
        assertTrue(pHeader.getPageElement(driver, "ROLE").getText().toUpperCase().contains(role));

        //Logout
        //Check Login page again
        pHeader.Logout(driver, role);
        assertTrue(pLogin.getPageElement(driver, "BEAMO ICON") != null);
    }

    /*
    @Test(groups = { "smoke" })
    public void VerifyLoginAsSuperAdmin() {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();
        //Page loading timeout (10s)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            //Input Space ID, Email, Password and click on login button
            //Check Login page (Title, elements, etc.)
            //Log in
            LoginPage pLogin = new LoginPage();
            String loginTitle = pLogin.getPageTitle(driver);
            assertEquals(loginTitle, "Beamo - Accounts");
            pLogin.clickLogin(driver, "test", "tmaster", "1234qwer");

            //Check Beamo Icon
            //Check "Super Admin" or "Master" role text
            HeaderPage pHeader = new HeaderPage();
            assertTrue(pHeader.getPageElement(driver, "BEAMO ICON") != null);
            assertTrue(pHeader.getPageElement(driver, "ROLE").getText().contains("Master"));

            //Check "Create Site" button
            SiteListPage pSiteList = new SiteListPage();
            assertTrue(pSiteList.getPageElement(driver, "CREATE SITE").isDisplayed());

            //Logout
            //Check Login page again
            pHeader.Logout(driver, "TMASTER");
            assertTrue(pLogin.getPageElement(driver, "BEAMO ICON") != null);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }
    }

    @Test(groups = { "smoke" })
    public void VerifyLoginAsTeamAdmin() {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();
        //Page loading timeout (10s)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            //Input Space ID, Email, Password and click on login button
            //Check Login page (Title, elements, etc.)
            //Log in
            LoginPage pLogin = new LoginPage();
            String loginTitle = pLogin.getPageTitle(driver);
            assertEquals(loginTitle, "Beamo - Accounts");
            pLogin.clickLogin(driver, "test", "dh.shin+admin@3i.ai", "1234qwer");

            //Check Beamo Icon
            //Check "Viewer" role text
            HeaderPage pHeader = new HeaderPage();
            assertTrue(pHeader.getPageElement(driver, "BEAMO ICON") != null);
            assertTrue(pHeader.getPageElement(driver, "ROLE").getText().contains("Team Admin"));

            //Logout
            //Check Login page again
            pHeader.Logout(driver, "TEAM ADMIN");
            assertTrue(pLogin.getPageElement(driver, "BEAMO ICON") != null);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }
    }

    @Test(groups = { "smoke" })
    public void VerifyLoginAsSurveyor() {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();
        //Page loading timeout (10s)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            //Input Space ID, Email, Password and click on login button
            //Check Login page (Title, elements, etc.)
            //Log in
            LoginPage pLogin = new LoginPage();
            String loginTitle = pLogin.getPageTitle(driver);
            assertEquals(loginTitle, "Beamo - Accounts");
            pLogin.clickLogin(driver, "test", "dh.shin+surveyor@3i.ai", "1234qwer");

            //Check Beamo Icon
            //Check "Viewer" role text
            HeaderPage pHeader = new HeaderPage();
            assertTrue(pHeader.getPageElement(driver, "BEAMO ICON") != null);
            assertTrue(pHeader.getPageElement(driver, "ROLE").getText().contains("Surveyor"));

            //Logout
            //Check Login page again
            pHeader.Logout(driver, "SURVEYOR");
            assertTrue(pLogin.getPageElement(driver, "BEAMO ICON") != null);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }
    }

    //@Test(groups = { "smoke" })
    public void VerifyLoginAsSiteManager() {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();
        //Page loading timeout (10s)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            //Input Space ID, Email, Password and click on login button
            //Check Login page (Title, elements, etc.)
            //Log in
            LoginPage pLogin = new LoginPage();
            String loginTitle = pLogin.getPageTitle(driver);
            assertEquals(loginTitle, "Beamo - Accounts");
            pLogin.clickLogin(driver, "test", "dh.shin+sitemanager@3i.ai", "1234qwer");

            //Check Beamo Icon
            //Check "Viewer" role text
            HeaderPage pHeader = new HeaderPage();
            assertTrue(pHeader.getPageElement(driver, "BEAMO ICON") != null);
            assertTrue(pHeader.getPageElement(driver, "ROLE").getText().contains("Site Manager"));

            //Logout
            //Check Login page again
            pHeader.Logout(driver, "SITE MANAGER");
            assertTrue(pLogin.getPageElement(driver, "BEAMO ICON") != null);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }
    }
*/
}
