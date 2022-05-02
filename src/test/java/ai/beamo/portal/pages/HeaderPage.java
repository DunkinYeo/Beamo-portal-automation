package ai.beamo.portal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class HeaderPage {
    
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public boolean isPresentFTUX(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.findElements(By.xpath("//span[contains(text(),'Got it')]")).size() > 0;
    }

    public WebElement getPageElement(WebDriver driver, String elementText) {
        WebElement element = null;
        switch (elementText) {
            case "BEAMO ICON":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/section/header/div/div[1]/span/span/div/img")));
                break;
            case "ROLE":
                element = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/div[2]/div[1]/span[1]")));
                break;
        }
        return element;
    }
    public void Logout(WebDriver driver, String role) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            //Locate Profile icon
            String xpathRole = "";
            String xpathLogout = "/html/body/div[3]/ul/li[2]";
            //If FTUX is exist
            if (isPresentFTUX(driver) == true) xpathLogout = "/html[1]/body[1]/div[4]/ul[1]/li[2]";

            switch (role) {
                case "MASTER": case "TEAM ADMIN": case "SURVEYOR": case "SITE MANAGER": case "COLLABORATOR":
                        xpathRole = "//header/div[1]/div[2]/span[4]/span[1]/div[1]/span[1]/span[1]/div[1]";
                        break;
                case "VIEWER":
                        xpathRole = "//header/div[1]/div[2]/span[3]/span[1]/div[1]/span[1]/span[1]/div[1]";
                        break;
            }

            WebElement bProfile = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpathRole)));
            bProfile.click();
            //Locate Logout
            WebElement bLogout = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLogout)));
            //Logout
            bLogout.click();
            //Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }
    
}
