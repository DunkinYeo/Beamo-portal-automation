package ai.beamo.portal.library;

import org.apache.commons.configuration2.PropertiesConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.util.Locale;

/* To-Do for Selenium Grid */
public class SeleniumBase {

    public static WebDriver driver;
    public static String SPACE_NAME = "d-en-en";

    // To-Do : Getting parameters from external config file
    public static PropertiesConfiguration config = null;
    public static boolean isRunningInGrid = false;

    public void openBrowser() throws MalformedURLException {
        String browser = config.getString("BROWSER");
        String chromedriverwin = config.getString("CHROMEDRIVERWIN");
        String chromedrivermac = config.getString("CHROMEDRIVERMAC");
        String ffdriverwin = config.getString("FFDRIVERWIN");
        String ffdrivermac = config.getString("FFDRIVERMAC");

        String targetEnv = System.getProperty("environment");
        String baseurl = "https://accounts.beamo.dev/";
        if (targetEnv.contains("DEV")) {
            baseurl = config.getString("BASEURLDEV");
            SPACE_NAME = "d-en-en";
        } else if (targetEnv.contains("STAG")) {
            baseurl = config.getString("BASEURLSTAG");
            SPACE_NAME = "s-en-en";
        } else if (targetEnv.contains("LIVE")) {
            baseurl = config.getString("BASEURLLIVE");
            SPACE_NAME = "test";
        }
        //String baseurl = config.getString("BASEURL");

        String osType = System.getProperty("os.name").toLowerCase();
        String chromedriver = null;
        String ffdriver = null;
        if (osType.contains("mac")) {
            chromedriver = chromedrivermac;
            ffdriver = ffdrivermac;
        } else if (osType.contains("win")) {
            chromedriver = chromedriverwin;
            ffdriver = ffdriverwin;
        }

        /* Local Mode*/
        switch (browser) {
            case "CH": //Chrome
                System.setProperty("webdriver.chrome.driver", chromedriver);
                driver = new ChromeDriver();
                break;
            case "IE":
                //driver = new InternetExplorerDriver();
                break;
            case "FF":
                System.setProperty("webdriver.gecko.driver", ffdriver);
                driver = new FirefoxDriver();
                break;
        }

        driver.get(baseurl);
        driver.manage().window().maximize();
    }

    public void closeBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Grid Mode*/
    public void openBrowserInGrid() throws MalformedURLException {
        isRunningInGrid = true;
        String hubhost = config.getString("HUBHOST");
        String browser = config.getString("BROWSER");
        String chromedriverwin = config.getString("CHROMEDRIVERWIN");
        String chromedrivermac = config.getString("CHROMEDRIVERMAC");
        String ffdriverwin = config.getString("FFDRIVERWIN");
        String ffdrivermac = config.getString("FFDRIVERMAC");

        String targetEnv = System.getProperty("environment");
        String baseurl = "https://accounts.beamo.dev/";
        if (targetEnv.contains("DEV")) {
            baseurl = config.getString("BASEURLDEV");
            SPACE_NAME = "d-en-en";
        } else if (targetEnv.contains("STAG")) {
            baseurl = config.getString("BASEURLSTAG");
            SPACE_NAME = "showcase";
        } else if (targetEnv.contains("LIVE")) {
            baseurl = config.getString("BASEURLLIVE");
            SPACE_NAME = "test";
        }
        //String baseurl = config.getString("BASEURL");

        String osType = System.getProperty("os.name").toLowerCase();
        String chromedriver = null;
        String ffdriver = null;
        if (osType.contains("mac")) {
            chromedriver = chromedrivermac;
            ffdriver = ffdrivermac;
        } else if (osType.contains("win")) {
            chromedriver = chromedriverwin;
            ffdriver = ffdriverwin;
        }

        /* Selenium Grid Mode*/
        switch (browser) {
            case "CH": //Chrome
                ChromeOptions chromeOptions = new ChromeOptions();
                ThreadSafeWebDriverStorage.startWebDriverSession(hubhost, browser, baseurl);
                break;
            case "IE":
                //driver = new InternetExplorerDriver();
                break;
            case "FF":
                //driver = new FirefoxDriver();
                break;
        }

        ThreadSafeWebDriverStorage.getDriver().manage().window().maximize();
    }

    public void closeBrowserInGrid() {
        try {
            ThreadSafeWebDriverStorage.closeWebDriverSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void startSeleniumServer() throws Exception {

    }

    public void stopSeleniumServer() {
        //seleniumServer.stop();
    }



}
