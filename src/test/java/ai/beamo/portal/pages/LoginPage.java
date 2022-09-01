package ai.beamo.portal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    public void inputSpaceID(WebDriver driver, String space) {
        WebElement weSpace = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]")));
        weSpace.sendKeys(space);

        WebElement btnContinue = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/button[1]")));
        btnContinue.click();
    }

    public void inputEmail(WebDriver driver, String email) {
        WebElement weEmail = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]")));
        weEmail.sendKeys(email);

        WebElement btnContinue = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/button[1]")));
        btnContinue.click();
    }

    public void inputPasswordAndLogin(WebDriver driver, String password) {
        WebElement weEmail = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/input[1]")));
        weEmail.sendKeys(password);

        WebElement btnLogin = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[4]/div[1]/button[1]")));
        btnLogin.click();
    }

    //Deprecated by UI changes to SSO
    /*
    public void clickLogin(WebDriver driver, String space, String id, String password) {
        //Space field
        WebElement weSpace = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]")));
        //User ID field
        WebElement weID = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/span[1]/span[1]/div[1]/input[1]")));
        //Password button
        WebElement wePassword = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[1]/span[1]/span[1]/div[1]/input[1]")));
        //Click on Login button
        WebElement weLogin = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/button[1]")));

        weSpace.sendKeys(space);
        weID.sendKeys(id);
        wePassword.sendKeys(password);

        Actions actions = new Actions(driver);
        actions.moveToElement(weLogin).click().perform();
        //weLogin.click();
    }
    */
}
