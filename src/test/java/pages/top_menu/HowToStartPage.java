package pages.top_menu;

import org.openqa.selenium.WebDriver;
import pages.base_abstract.BaseMainBreadcrumbsPage;

public class HowToStartPage extends BaseMainBreadcrumbsPage {

    public HowToStartPage(WebDriver driver) {
        super(driver);
    }

    public HowToStartPage createGeneric() {

        return new HowToStartPage(getDriver());
    }
}
