package ai.beamo.portal.library;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.io.File;

public class TestBase extends SeleniumBase {

    //public WebDriver driver;
    /*
        To-Do : Any config for Remote Selenium Server in @BeforeTest
     */
    @Parameters({ "configfile" })
    @BeforeSuite(groups = { "abstract" })
    public void initFramework(@Optional("src/test/resources/config.properties") String configfile) throws Exception {
        // Initialize configuration
        Configurations configs = new Configurations();
        // Read data from this file
        File propertiesFile = new File(configfile);
        //FileBasedConfigurationBuilder<PropertiesConfiguration> builder = configs.propertiesBuilder(propertiesFile);
        PropertiesConfiguration conf = configs.properties(propertiesFile);

        config = conf;
    }
    @BeforeTest(groups = { "abstract" })
    public void initTests() throws Exception{
        // Start Selenium Server
        //super.startSeleniumServer();
    }

    // Create global WebDriver
    @BeforeMethod(groups = { "abstract", "non-grid" })
    public void setUpTests() {
        //WebDriver driver;
        try {
            super.openBrowser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Close WebDriver
    @AfterMethod(groups = { "abstract", "non-grid" })
    public void tearDownTests() throws Exception{
        try {
            super.closeBrowser();
        } catch (Exception e) {
            //
        }
    }

    // Create global WebDriver in Grid Mode
    @BeforeMethod(groups = { "abstract", "grid" })
    public void setUpTestsInGrid() {
        //WebDriver driver;
        try {
            super.openBrowserInGrid();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Close WebDriver in Grid Mode
    @AfterMethod(groups = { "abstract", "grid" })
    public void tearDownTestsInGrid() throws Exception{
        try {
            super.closeBrowserInGrid();
        } catch (Exception e) {
            //
        }
    }

    @AfterTest(groups = { "abstract" })
    public void cleanUp() {
        try {


        } catch (Exception e) {

        }
    }
/*
    public WebDriver getDriver() {
        return driver; // static handle to Selenium object
    }
*/
}
