package pages.base_abstract;

import org.openqa.selenium.WebDriver;
import pages.components.MarketplaceFooter;
import pages.components.MarketplaceTopMenu;

public abstract class BaseMarketplacePage extends BasePage {

    protected BaseMarketplacePage(WebDriver driver) {
        super(driver);
    }

    public MarketplaceTopMenu getMarketplaceTopMenu() {
        return new MarketplaceTopMenu(getDriver());
    }

    public MarketplaceFooter getMarketplaceFooter() {
        return new MarketplaceFooter(getDriver());
    }
}
