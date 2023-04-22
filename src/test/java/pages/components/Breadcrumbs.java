package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.StartPage;
import pages.base_abstract.BaseComponents;

public class Breadcrumbs extends BaseComponents {

    @FindBy(xpath = "//h1[@class ='breadcrumb-title']")
    private WebElement h1Header;

    @FindBy(xpath = "//ol/li/a[@href = '/']")
    private WebElement homeLink;

    public Breadcrumbs(WebDriver driver) {
        super(driver);
    }

    public String getH1Header() {

        return getText(h1Header);
    }

    public WebElement getHomeLink() {

        return homeLink;
    }

    public StartPage clickHomeLink() {
        click(getHomeLink());

        return new StartPage(getDriver());
    }
}
