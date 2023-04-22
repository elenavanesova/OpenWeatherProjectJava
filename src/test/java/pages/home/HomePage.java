package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.BaseHomePage;

import java.util.List;

public class HomePage extends BaseHomePage {

    @FindBy(xpath = "//div[@class = 'panel-body']")
    WebElement notification;

    @FindBy(id = "user-dropdown")
    WebElement userTopMenu;

    @FindBy(xpath = "//ul[@id='user-dropdown-menu']/li")
    List<WebElement> userDropdownMenuLinks;

    @FindBy(xpath = "//ul[@id='myTab']//a[@href='/api_keys']")
    private WebElement apiKeysTab;

    @FindBy(xpath = "//h2")
    private List<WebElement> h2Headers;

    @FindBy(className = "btn_like")
    private List<WebElement> orangeButtons;

    @FindBy(xpath = "//ul[@class='nav nav-tabs pull-left']//a")
    private List<WebElement> navTabs;

    @FindBy(id = "password_form_password")
    private WebElement passwordMyProfileField;

    @FindBy(id = "password_form_password_confirmation")
    private WebElement confirmPasswordMyProfileField;

    @FindBy(xpath = "//input[@value='Change Password']")
    private WebElement changePasswordButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage createGeneric() {

        return new HomePage(getDriver());
    }

    public String getNotification() {

        return getText(notification);
    }

    public List<String> getUserNameDropdownMenuTexts() {

        return getTexts(userDropdownMenuLinks);
    }

    public HomeAPIKeysPage clickAPIKeysTab() {
        click(apiKeysTab);

        return new HomeAPIKeysPage(getDriver());
    }

    public List<String> getListH2Headers() {

        return getTexts(h2Headers);
    }

    public String getUserMenuText() {

        return getText(userTopMenu);
    }

    public HomePage clickUserNameMenu() {
        click(userTopMenu);

        return this;
    }

    public List<WebElement> getOrangeButtons() {

        return orangeButtons;
    }

    public void waitUntilButtonIsClickable(WebElement button) {

        wait10ElementToBeClickable(button);
    }

    public HomePage clickClearInputPasswordMyProfile() {
        inputAfterClear(passwordMyProfileField, "Tester12#");

        return this;
    }

    public HomePage clickClearInputConfirmPasswordMyProfile() {
        inputAfterClear(confirmPasswordMyProfileField, "Tester12#");

        return this;
    }

    public HomePage clickChangePasswordButton() {
        click(changePasswordButton);

        return this;
    }
}

