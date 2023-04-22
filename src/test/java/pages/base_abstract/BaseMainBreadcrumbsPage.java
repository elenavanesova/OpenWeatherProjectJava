package pages.base_abstract;

import org.openqa.selenium.WebDriver;
import pages.components.Breadcrumbs;
import pages.components.FooterMenu;
import pages.components.TopMenu;

public abstract class BaseMainBreadcrumbsPage extends BasePage {

    protected BaseMainBreadcrumbsPage(WebDriver driver) {
        super(driver);
    }

    public TopMenu getTopMenu() {
        return new TopMenu(getDriver());
    }

    public Breadcrumbs getBreadcrumbs() {
        return new Breadcrumbs(getDriver());
    }

    public FooterMenu getFooterMenu() {
        return new FooterMenu(getDriver());
    }
}
