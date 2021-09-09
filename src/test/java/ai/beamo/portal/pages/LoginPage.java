package ai.beamo.portal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    public String getPageTitle(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver.getTitle();
    }

    public WebElement getPageElement(WebDriver driver, String elementText) {
        WebElement element = null;
        switch (elementText) {
            case "BEAMO ICON":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[1]/*[1]")));
                break;
        }
        return element;
    }

    public void clickLogin(WebDriver driver, String id, String password) {
        //Space ID field
        driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("test");
        //User Email field
        driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/span[1]/span[1]/div[1]/input[1]")).sendKeys(id);
        //Password button
        driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[1]/span[1]/span[1]/div[1]/input[1]")).sendKeys(password);
        //Click on Login button
        driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/button[1]")).click();

    }
}
