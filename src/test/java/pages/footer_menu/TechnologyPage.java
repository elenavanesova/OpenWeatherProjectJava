package pages.footer_menu;

import org.openqa.selenium.WebDriver;
import pages.base_abstract.BaseMainPage;

public class TechnologyPage extends BaseMainPage {

    public TechnologyPage(WebDriver driver) {
        super(driver);
    }

    public TechnologyPage createGeneric() {

        return new TechnologyPage(getDriver());
    }
}
