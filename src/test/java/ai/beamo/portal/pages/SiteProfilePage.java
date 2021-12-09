package ai.beamo.portal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SiteProfilePage {

    public WebElement getPageElement(WebDriver driver, String elementText) {
        WebElement element = null;
        switch (elementText) {
            case "EDIT MENU":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='control-panel']/main/div[2]/header/div[2]/div[1]/div/div/button")));
                break;
            case "DELETE":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Delete')]")));
                break;
            case "CONFIRM DELETE":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[2]/div[1]/div[3]/button[2]")));
                break;
        }
        return element;
    }
}
