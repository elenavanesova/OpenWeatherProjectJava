package pages;

import org.openqa.selenium.WebDriver;
import pages.base_abstract.BaseMainBreadcrumbsPage;

public class RoadRiskAPIPage extends BaseMainBreadcrumbsPage {

    public RoadRiskAPIPage(WebDriver driver) {
        super(driver);
    }

    public RoadRiskAPIPage createGeneric() {

        return new RoadRiskAPIPage(getDriver());
    }
}
