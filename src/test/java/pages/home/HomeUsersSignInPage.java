package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base_abstract.BaseMainPage;
import utils.ProjectConstants;

public class HomeUsersSignInPage extends BaseMainPage {

    @FindBy(xpath = "//div[@class = 'panel-body']")
    WebElement notification;
    @FindBy(id = "user_email")
    private WebElement userEmail;
    @FindBy(id = "user_password")
    private WebElement userPassword;
    @FindBy(name = "commit")
    private WebElement submitButton;
    @FindBy(xpath = "//a[@href='/users/sign_up']")
    private WebElement createAccountLink;
    @FindBy(xpath = "//a[@href='#']")
    private WebElement clickHereToRecoverLink;
    @FindBy(xpath = "//h3")
    private WebElement h3Header;
    @FindBy(xpath = "//div[@id='desktop-menu']//li[@class='user-li']/a")
    private WebElement signInTopMenu;
    @FindBy(id = "user_remember_me")
    private WebElement rememberMe;
    @FindBy(xpath = "//div[@class='form-group email optional user_email']/input")
    private WebElement resetPasswordEmail;
    @FindBy(xpath = "//input[@type='submit'][@value='Send']")
    private WebElement sendButton;
    @FindBy(name = "commit")
    private WebElement changePasswordButton;


    public HomeUsersSignInPage(WebDriver driver) {
        super(driver);
    }

    public HomeUsersSignInPage createGeneric() {

        return new HomeUsersSignInPage(getDriver());
    }

    public String getNotification() {

        return getText(notification);
    }

    public String getWelcomeMessage() {

        return getText(h3Header);
    }

    public String getSignInText() {

        return getText(signInTopMenu);
    }

    public HomeUsersSignInPage clickClearInputRegularUserEmail() {
        click(userEmail);
        userEmail.clear();
        String email = "jka59433@xcoxc.com";
        input(email, userEmail);

        return this;
    }

    public HomeUsersSignInPage clickClearInputRegularUserEmail(String email) {
        click(userEmail);
        userEmail.clear();
        input(email, userEmail);

        return this;
    }

    public HomeUsersSignInPage clickClearInputRegularUserPassword() {
        click(userPassword);
        userPassword.clear();
        String password = "Tester12#";
        input(password, userPassword);

        return this;
    }

    public HomeUsersSignInPage clickClearInputRegularUserPassword(String password) {
        click(userPassword);
        userPassword.clear();
        input(password, userPassword);

        return this;
    }

    public void clickSubmitButton() {
        click20(submitButton);
    }

    public void signInAsRegularUser() {
        clickClearInputRegularUserEmail();
        clickClearInputRegularUserPassword();
        clickSubmitButton();

        new HomePage(getDriver());
    }

    public HomeUsersSignUpPage clickCreateAnAccountLink() {
        click(createAccountLink);

        return new HomeUsersSignUpPage(getDriver());
    }

    public HomeUsersSignInPage checkRememberMeCheckBox() {
        if (!rememberMe.isSelected())
            click(rememberMe);
        return this;
    }

    public HomeUsersSignInPage clickRecoverLink() {
        click(clickHereToRecoverLink);
        getWait20().until(ExpectedConditions.visibilityOf(resetPasswordEmail));

        return this;
    }

    public HomeUsersSignInPage inputRecoverPasswordEmail() {
        input(ProjectConstants.GMAIL_EMAIL, resetPasswordEmail);
        getWait20().until(ExpectedConditions.elementToBeClickable(sendButton));

        return this;
    }

    public HomeUsersSignInPage clickSendButton() {
        click(sendButton);
        return this;
    }

    public HomeUsersSignInPage clickClearRecoverPasswordEmail() {
        click(resetPasswordEmail);
        clear(resetPasswordEmail);

        return this;
    }

    public HomeUsersSignInPage clickClearInputGmailUserEmail() {
        click(userEmail);
        userEmail.clear();
        String email = ProjectConstants.GMAIL_EMAIL;
        input(email, userEmail);

        return this;
    }

    public HomeUsersSignInPage clickClearInputGmailUserNewPassword() {
        click(userPassword);
        userPassword.clear();
        String password = ProjectConstants.NEW_USER_PASSWORD;
        input(password, userPassword);

        return this;
    }

    public String getAlertMessage() {
        WebElement alert = getDriver().findElement(By.xpath("//div[@class='panel-body']"));
        getWait20().until(ExpectedConditions.visibilityOf(alert));
        return getText(alert);
    }

    public void signInAsGmailUserNewPassword() {
        clickClearInputGmailUserEmail();
        clickClearInputGmailUserNewPassword();
        clickSubmitButton();
        new HomePage(getDriver());
    }
}
