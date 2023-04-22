package pages.components;

import org.openqa.selenium.WebDriver;
import pages.base_abstract.BaseTopMenu;


public class MarketplaceTopMenu extends BaseTopMenu {

    public MarketplaceTopMenu(WebDriver driver) {
        super(driver);
    }

    @Override
    public MarketplaceTopMenu clickSupportMenu() {
        click(supportTopMenu);

        return this;
    }
}
