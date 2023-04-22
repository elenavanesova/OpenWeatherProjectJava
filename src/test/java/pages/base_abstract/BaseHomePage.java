package pages.base_abstract;

import org.openqa.selenium.WebDriver;
import pages.components.FooterMenu;
import pages.components.TopMenu;
import pages.components.UserTopMenu;

public abstract class BaseHomePage extends BasePage {

    protected BaseHomePage(WebDriver driver) {
        super(driver);
    }

    public TopMenu getTopMenu() {
        return new TopMenu(getDriver());
    }

    public UserTopMenu getUserTopMenu() {
        return new UserTopMenu(getDriver());
    }

    public FooterMenu getFooterMenu() {
        return new FooterMenu(getDriver());
    }
}
