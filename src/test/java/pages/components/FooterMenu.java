package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.WeatherStationsPage;
import pages.base_abstract.BaseFooter;
import pages.footer_menu.TechnologyPage;
import pages.footer_menu.WidgetsPage;
import pages.home.HomeAskQuestionPage;
import pages.top_menu.PricePage;
import pages.top_menu.WeatherDashboardPage;

import java.util.List;

public class FooterMenu extends BaseFooter {

    @FindBy(xpath = FOOTER_MENU + "//a[@href='/weather-dashboard']")
    private WebElement weatherDashboardFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//a[@href='/technology']")
    private WebElement ourTechnologyFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//a[@href='/widgets-constructor']")
    private WebElement widgetsFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//a[@href='/stations']")
    private WebElement connectYourWeatherStationFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//a[@href='/price']")
    private WebElement pricingFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//p[text()='Subscription']")
    private WebElement subscription;

    @FindBy(xpath = FOOTER_MENU + "//p[text()='Subscription']/parent::div/div/ul/li")
    private List<WebElement> subscriptionList;

    @FindBy(xpath = FOOTER_MENU + "//a[@href='https://openweather.co.uk/']")
    private WebElement openWeatherForBusinessFooterMenuLink;

    @FindBy(xpath = FOOTER_MENU + "//p[text()='Product Collections']")
    private WebElement productCollections;

    @FindBy(xpath = FOOTER_MENU + "//p[text()='Product Collections']//parent::div/div/ul/li")
    private List<WebElement> productCollectionsList;

    public FooterMenu(WebDriver driver) {
        super(driver);
    }

    @Override
    public BaseFooter createFooter() {
        return this;
    }

    public WebElement getSubscriptionFooterMenu() {

        return subscription;
    }

    public List<String> getSubscriptionMenusTexts() {

        return getTexts(subscriptionList);
    }

    public WebElement getFooterMenu() {

        return footerMenu;
    }

    public WeatherDashboardPage clickWeatherDashboardFooterMenu() {
        click(weatherDashboardFooterMenu);

        return new WeatherDashboardPage(getDriver());
    }

    public TechnologyPage clickOurTechnologyFooterMenu() {
        click(ourTechnologyFooterMenu);

        return new TechnologyPage(getDriver());
    }

    public PricePage clickPricingFooterMenu() {
        click20(pricingFooterMenu);

        return new PricePage(getDriver());
    }

    public WidgetsPage clickWidgetsPageFooterMenu() {
        click(widgetsFooterMenu);

        return new WidgetsPage(getDriver());
    }

    public WeatherStationsPage clickConnectYourWeatherStationFooterMenu() {
        click(connectYourWeatherStationFooterMenu);

        return new WeatherStationsPage(getDriver());
    }

    public void clickOpenWeatherForBusinessFooterMenuLink() {
        click(openWeatherForBusinessFooterMenuLink);
    }

    public HomeAskQuestionPage switchToHomeAskQuestionPage() {
        switchToAnotherWindow();

        return new HomeAskQuestionPage(getDriver());
    }

    public WebElement getProductCollectionsFooterMenu() {

        return productCollections;
    }

    public List<String> getProductCollectionsFooterMenuText() {

        return getTexts(productCollectionsList);
    }
}
