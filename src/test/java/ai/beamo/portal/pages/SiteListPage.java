package ai.beamo.portal.pages;

import ai.beamo.portal.library.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SiteListPage {

    public Boolean isElementPresent(WebDriver driver, String elementText) {

        Boolean isPresent = false;
        List<WebElement> elements = null;

        switch (elementText) {
            case "CREATE SITE":
                isPresent = driver.findElements(By.className("create-site")).size() > 0;
                break;
        }
        return isPresent;
    }

    public WebElement getPageElement(WebDriver driver, String elementText) {
        WebElement element = null;
        switch (elementText) {
            case "CREATE SITE":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.className("create-site")));
                break;
            case "SEARCH BAR":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/section/main/div/div[1]/main/div[1]/div/input")));
                break;
        }
        return element;
    }

    public void searchSite(WebDriver driver, String keyword) {
        WebElement searchBar = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/section/main/div/div[1]/main/div[1]/div/input")));
        searchBar.sendKeys(keyword);
        WebElement site = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + keyword +"')]")));
        site.click();
    }

}
