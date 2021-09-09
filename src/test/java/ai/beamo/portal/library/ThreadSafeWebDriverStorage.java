package ai.beamo.portal.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ThreadSafeWebDriverStorage extends SeleniumBase {

    static ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<WebDriver>();

    public static void startWebDriverSession(String hubhost, String browser, String baseurl) throws MalformedURLException {

        ChromeOptions chromeOptions = new ChromeOptions();
        threadLocalWebDriver.set(new RemoteWebDriver(new URL(hubhost), chromeOptions));

        threadLocalWebDriver.get().get(baseurl);
    }

    public static void closeWebDriverSession() {
        threadLocalWebDriver.get().quit();
        threadLocalWebDriver.remove();
    }

    public static WebDriver getDriver() {
        if (isRunningInGrid) {
            return threadLocalWebDriver.get();
        } else {
            return driver;
        }
    }

}
