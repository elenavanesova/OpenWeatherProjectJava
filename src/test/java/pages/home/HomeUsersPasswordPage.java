package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import pages.base_abstract.BaseMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ProjectConstants;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomeUsersPasswordPage extends BaseMainPage {

    @FindBy(xpath = "//h3")
    private WebElement h3Header;
    @FindBy(id = "user_password")
    private WebElement passwordField;
    @FindBy(id = "user_password_confirmation")
    private WebElement repeatPasswordField;
    @FindBy(name = "commit")
    private WebElement changePasswordButton;

    public HomeUsersPasswordPage(WebDriver driver) {
        super(driver);
    }

    public HomeUsersSignInPage clickChangePasswordButton() {
        try {
            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofMillis(30000));
            click(changePasswordButton);

        }catch (org.openqa.selenium.TimeoutException exception){
            exception.printStackTrace();
        }
        finally {
            FluentWait<WebDriver> wait = new FluentWait<>(getDriver());
            wait.withTimeout(Duration.ofSeconds(15) );
            wait.pollingEvery(Duration.ofSeconds(2));
            wait.ignoring(org.openqa.selenium.TimeoutException.class);
            wait.until(ExpectedConditions.urlToBe("https://home.openweathermap.org/users/sign_in"));
        }

        return new HomeUsersSignInPage(getDriver());
    }

    public HomeUsersPasswordPage inputPassword(String password) {
        input(password, passwordField);

        return this;
    }

    public HomeUsersPasswordPage inputRepeatPassword(String password) throws InterruptedException {
        Thread.sleep(500);
        input(password, repeatPasswordField);

        return new HomeUsersPasswordPage(getDriver());
    }

    public String getH3Header() {
        return h3Header.getText();
    }

    public String getAlertMessage() {
        WebElement alert = getDriver().findElement(By.xpath("//div[@class='panel-body']"));
        getWait20().until(ExpectedConditions.visibilityOf(alert));
        return getText(alert);
    }

    public void waitForElementsLoaded(int milliseconds) {
        getDriver().manage().timeouts().implicitlyWait(milliseconds, TimeUnit.MILLISECONDS);
    }


}
