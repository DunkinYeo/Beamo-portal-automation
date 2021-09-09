package ai.beamo.portal.tests;

import ai.beamo.portal.library.TestBase;
import ai.beamo.portal.library.ThreadSafeWebDriverStorage;
import ai.beamo.portal.pages.HeaderPage;
import ai.beamo.portal.pages.LoginPage;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.concurrent.TimeUnit;

public class LoginTest extends TestBase {

    @Test(groups = { "smoke" })
    public void VerifyLoginAsViewer() {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();
        //Page loading timeout (10s)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        try {
            //Input Space ID, Email, Password and Click on Login button
            LoginPage pLogin = new LoginPage();
            //Check Login Page Title
            String loginTitle = pLogin.getPageTitle(driver);
            assertEquals(loginTitle, "Beamo - Accounts");
            //Log in
            pLogin.clickLogin(driver, "dh.shin+viewer@3i.ai", "1234qwer");

            HeaderPage pHeader = new HeaderPage();
            //Check Beamo Icon
            assertTrue(pHeader.getPageElement(driver, "BEAMO ICON") != null);
            //Check Viewer text
            assertTrue(pHeader.getPageElement(driver, "ROLE").getText().contains("Viewer"));
            //Logout
            pHeader.Logout(driver, "VIEWER");

            assertTrue(pLogin.getPageElement(driver, "BEAMO ICON") != null);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }
    }

    @Test(groups = { "smoke" })
    public void VerifyLoginAsSuperAdmin() {
        WebDriver driver = ThreadSafeWebDriverStorage.getDriver();
        //Page loading timeout (10s)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            //Input Space ID, Email, Password and Click on Login button
            LoginPage pLogin = new LoginPage();
            //Check Login Page Title
            String loginTitle = pLogin.getPageTitle(driver);
            assertEquals(loginTitle, "Beamo - Accounts");
            //Log in
            pLogin.clickLogin(driver, "tmaster", "1234qwer");

            HeaderPage pHeader = new HeaderPage();
            //Check Beamo Icon
            assertTrue(pHeader.getPageElement(driver, "BEAMO ICON") != null);
            //Check Viewer text
            assertTrue(pHeader.getPageElement(driver, "ROLE").getText().contains("Master"));
            //Logout
            pHeader.Logout(driver, "TMASTER");

            assertTrue(pLogin.getPageElement(driver, "BEAMO ICON") != null);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail();
        }
    }

}
