package ai.beamo.portal.pages;

import ai.beamo.portal.library.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreateSiteLabelsPage {

    public WebElement getPageElement(WebDriver driver, String elementText) {

        WebElement element = null;
        switch (elementText) {
            case "SAVE":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Save')]")));
                break;
        }
        return element;
    }
}
