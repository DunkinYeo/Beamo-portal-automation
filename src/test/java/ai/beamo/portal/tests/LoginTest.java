package ai.beamo.portal.tests;

import ai.beamo.portal.library.TestBase;
import ai.beamo.portal.library.ThreadSafeWebDriverStorage;
import ai.beamo.portal.pages.HeaderPage;
import ai.beamo.portal.pages.LoginPage;

import ai.beamo.portal.pages.SiteListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.concurrent.TimeUnit;

public class LoginTest extends TestBase {

    //private final String SPACE_NAME = "showcase";

    @DataProvider(name = "Credentials")
    public Object[][] loginCredentials() {
        return new Object[][] {
                { "qatest@3i.ai", "1234@qwer", "SUPER ADMIN" },
                { "dh.shin+ta_sitemanager@3i.ai", "qwer1234", "SITE MANAGER" },
                { "dh.shin+ta_admin@3i.ai", "qwer1234", "TEAM ADMIN" },
                { "dh.shin+ta_surveyor@3i.ai", "qwer1234", "SURVEYOR" },
                { "dh.shin+ta_collaborator@3i.ai", "qwer1234", "COLLABORATOR" },
                { "dh.shin+ta_viewer@3i.ai", "qwer1234", "VIEWER" }
        };
    }

    @Test (dataProvider = "Credentials", groups = { "smoke", "login" } )
    public void verifyLoginByRoles(String email, String password, String role) {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            //Using a different super admin account on Live env.
            if (SPACE_NAME == "test" && role == "SUPER ADMIN") {
                email = "dh.shin+ta_superadmin@3i.ai";
            }

            System.out.println(email);

            System.out.println(role);
            verifyLogin(driver, email, password, role);
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }

    }

    public void verifyLogin(WebDriver driver, String email, String password, String role) throws Exception {
        //Page loading timeout (10s)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //Input Space ID, Email, Password and click on login button
        //Check Login page (Title, elements, etc.)
        //Log in
        LoginPage pLogin = new LoginPage();
        String loginTitle = pLogin.getPageTitle(driver);
        assertEquals(loginTitle, "Beamo - Accounts");
        // Removed by Login flow changed to SSO
        // pLogin.clickLogin(driver, SPACE_NAME, id, password);
        //New SSO UI
        pLogin.inputSpaceID(driver, SPACE_NAME);
        Thread.sleep(3000);
        pLogin.inputEmail(driver, email);
        Thread.sleep(3000);
        pLogin.inputPasswordAndLogin(driver, password);
        Thread.sleep(5000);

        //Check Beamo App loaded
        //Check the name of the role
        HeaderPage pHeader = new HeaderPage();
        assertTrue(pHeader.getPageElement(driver, "BEAMO ICON") != null);
        assertTrue(pHeader.getPageElement(driver, "ROLE").getText().toUpperCase().contains(role));

        //Check "Create Site" button
        SiteListPage pSiteList = new SiteListPage();

        if ( role.equals("MASTER") || role.equals("SUPER ADMIN") || role.equals("SITE MANAGER") ) {
            assertTrue(pSiteList.isElementPresent(driver, "CREATE SITE") == true);
        } else {
            assertTrue(pSiteList.isElementPresent(driver, "CREATE SITE") == false);
        }

        //Logout
        //Check Login page again
        pHeader.Logout(driver, role);
        Thread.sleep(5000);
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
   */
}
