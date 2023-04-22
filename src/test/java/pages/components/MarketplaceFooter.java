package pages.components;

import org.openqa.selenium.WebDriver;
import pages.base_abstract.BaseFooter;

public class MarketplaceFooter extends BaseFooter {

    public MarketplaceFooter(WebDriver driver) {
        super(driver);
    }

    @Override
    public BaseFooter createFooter() {
        return this;
    }
}
