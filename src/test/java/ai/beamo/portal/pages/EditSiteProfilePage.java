package ai.beamo.portal.pages;

import org.eclipse.jetty.websocket.api.WebSocketBehavior;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditSiteProfilePage extends BasePage {

    public EditSiteProfilePage(WebDriver driver) {
        super (driver);
    }

    @FindBy(xpath = "//*[@id=\"project-info-pane\"]/form/div/div[1]/div[1]/div/div/input")
    WebElement inputSiteName;

    @FindBy(xpath = "//*[@id=\"project-info-pane\"]/form/div/div[2]/div[1]/div/div/input")
    WebElement inputLocation;

    @FindBy(xpath = "")
    WebElement inputLatitude;

    @FindBy(xpath = "")
    WebElement inputLongitude;

    @FindBy(xpath = "//*[@id=\"control-panel\"]/main/div[2]/div[2]/div[2]/div/div[3]/div/button[2]/span")
    WebElement btnSave;

//Tokyo tower address : 4 Chome-2-8 Shibakoen, Minato City, Tokyo 105-0011, Japan

    public void updateSiteName(String text) {
        clear(inputSiteName);
        inputSiteName.sendKeys(text);
    }

    public void updateLocation(String text) {
        clear(inputLocation);
        inputLocation.sendKeys(text);
    }

    public void clickSave() {
        click(btnSave);
    }






}
