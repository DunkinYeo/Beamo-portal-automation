package ai.beamo.portal.pages;

import ai.beamo.portal.library.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreateSiteProfilePage {

    public WebElement getPageElement(WebDriver driver, String elementText) {

        WebElement element = null;
        switch (elementText) {
            case "SITE NAME":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/section/main/div/div[1]/main/div[3]/div[3]/div/div[2]/div/div[2]/div[1]/div/form/div/div[1]/div[1]/div/div[1]/input")));
                break;
            case "LOCATION":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/section/main/div/div[1]/main/div[3]/div[3]/div/div[2]/div/div[2]/div[1]/div/form/div/div[2]/div[1]/div/div/input")));
                break;
            case "LATITUDE":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/section/main/div/div[1]/main/div[3]/div[3]/div/div[2]/div/div[2]/div[1]/div/form/div/div[2]/div[2]/div[1]/div/div/div/input")));
                break;
            case "LONGITUDE":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/section/main/div/div[1]/main/div[3]/div[3]/div/div[2]/div/div[2]/div[1]/div/form/div/div[2]/div[2]/div[2]/div/div/div/input")));
                break;
            case "CREATE SITE":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/section/main/div/div[1]/main/div[3]/div[3]/div/div[3]/div/button[2]")));
                break;
        }
        return element;
    }
}
