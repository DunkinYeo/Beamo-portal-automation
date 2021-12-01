package ai.beamo.portal.pages;

import ai.beamo.portal.library.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SiteListPage {

    public Boolean isElementPresent(WebDriver driver, String elementText) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Boolean isPresent = false;
        List<WebElement> elements = null;

        switch (elementText) {
            case "CREATE SITE":
                isPresent = driver.findElements(By.className("create-site")).size() > 0;
                break;
        }
        return isPresent;
    }

}
