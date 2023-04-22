package pages;

import org.openqa.selenium.WebDriver;
import pages.base_abstract.BaseMainBreadcrumbsPage;

public class SolarRadiationAPIPage extends BaseMainBreadcrumbsPage {

    public SolarRadiationAPIPage(WebDriver driver) {
        super(driver);
    }

    public SolarRadiationAPIPage createGeneric() {

        return new SolarRadiationAPIPage(getDriver());
    }
}
