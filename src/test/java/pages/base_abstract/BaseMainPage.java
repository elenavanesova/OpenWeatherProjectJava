package pages.base_abstract;

import org.openqa.selenium.WebDriver;
import pages.components.FooterMenu;
import pages.components.TopMenu;

public abstract class BaseMainPage extends BasePage {

    protected BaseMainPage(WebDriver driver) {
        super(driver);
    }

    public TopMenu getTopMenu() {
        return new TopMenu(getDriver());
    }

    public FooterMenu getFooterMenu() {
        return new FooterMenu(getDriver());
    }
}
