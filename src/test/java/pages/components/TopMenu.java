package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.FindPage;
import pages.OurInitiativesPage;
import pages.base_abstract.BaseTopMenu;
import pages.home.HomePage;
import pages.top_menu.*;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class TopMenu extends BaseTopMenu {

    @FindBy(xpath = TOP_MENU_ID + "//a")
    private List<WebElement> topMenuLinks;

    @FindBy(xpath = TOP_MENU_ID + "//form[@role='search']")
    private WebElement searchBoxTopMenu;

    @FindBy(xpath = TOP_MENU_ID + "//input[@name='q']")
    private WebElement searchFieldTopMenu;

    @FindBy(xpath = TOP_MENU_ID + "//a[@href='/guide']")
    private WebElement guideTopMenu;

    @FindBy(xpath = TOP_MENU_ID + "//li/a[@href='/api']")
    private WebElement apiTopMenu;

    @FindBy(xpath = TOP_MENU_ID + "//a[@href='/price']")
    private WebElement pricingTopMenu;

    @FindBy(xpath = TOP_MENU_ID + "//a[@href='/weathermap']")
    private WebElement mapsTopMenu;

    @FindBy(xpath = TOP_MENU_ID + "//a[@href='/our-initiatives']")
    private WebElement ourInitiativesTopMenu;

    @FindBy(xpath = TOP_MENU_ID + "//a[@href='/examples']")
    private WebElement partnersTopMenu;

    @FindBy(xpath = TOP_MENU_ID + "/ul/li")
    private List<WebElement> topMenus;

    @FindBy(xpath = TOP_MENU_ID + "//a[@href='/weather-dashboard']")
    private WebElement dashboardTopMenu;

    @FindBy(xpath = TOP_MENU_ID + "//a[@href='https://openweathermap.org/weather-dashboard']")
    private WebElement homeDashboardTopMenu;

    public TopMenu(WebDriver driver) {
        super(driver);
    }

    public int topMenuLinkAmount() {

        return getListSize(topMenus);
    }

    public String getInnerTextOfPlaceholder(String attribute) {

        return getAttribute(searchFieldTopMenu, attribute);
    }

    public List<WebElement> getTopMenuLinks() {

        return topMenuLinks;
    }

    public void clickTopMenuExternalLink(int index) {
        click(getTopMenuLinks().get(index));
        if (getDriver().getWindowHandles().size() > 1) {
            switchToAnotherWindow();
            TestUtils.waitForPageLoaded(getDriver());
        }
    }

    public GuidePage clickGuideMenu() {
        click(guideTopMenu);

        return new GuidePage(getDriver());
    }

    public APIPage clickAPIMenu() {
        click(apiTopMenu);

        return new APIPage(getDriver());
    }

    public PricePage clickPricingMenu() {
        click(pricingTopMenu);

        return new PricePage(getDriver());
    }

    public WeatherMapsPage clickMapsMenu() {
        click(mapsTopMenu);

        return new WeatherMapsPage(getDriver());
    }

    public OurInitiativesPage clickOurInitiativesMenu() {
        click(ourInitiativesTopMenu);

        return new OurInitiativesPage(getDriver());
    }

    public PartnersPage clickPartnersMenu() {
        click(partnersTopMenu);

        return new PartnersPage(getDriver());
    }

    public WeatherDashboardPage clickDashboardMenu() {
        click(dashboardTopMenu);

        return new WeatherDashboardPage(getDriver());
    }

    public TopMenu clickSearchFieldTopMenu() {
        click(searchFieldTopMenu);

        return this;
    }

    public TopMenu inputSearchCriteriaIntoSearchField(String text) {
        if (!getText(searchFieldTopMenu).isEmpty() && !getText(searchFieldTopMenu).isBlank()) {
            clear(searchFieldTopMenu);
        }
        input(text, searchFieldTopMenu);

        return this;
    }

    public FindPage clickEnter() {
        clickEnter(searchFieldTopMenu);

        return new FindPage(getDriver());
    }

    public boolean isPlaceholderDisplayed() {

        return isElementDisplayed(searchBoxTopMenu);
    }

    public String getEnteredValue() {

        return getAttribute(searchFieldTopMenu, "value");
    }

    public FindPage inputSearchCriteriaAndEnter(String text) {
        inputSearchCriteriaIntoSearchField(text);
        clickEnter();

        return new FindPage(getDriver());
    }

    @Override
    public TopMenu clickSupportMenu() {
        click(supportTopMenu);

        return this;
    }

    public HomePage signInGmailUserNewPassword() {
        clickSignInMenu().signInAsGmailUserNewPassword();

        return new HomePage(getDriver());
    }

    public void clickTopMenu(int index) {
        List<WebElement> menus = new ArrayList<>();
        menus.add(logo);
        menus.addAll(topMenus);

        click(menus.get(index));

        if (getDriver().getWindowHandles().size() > 1) {
            switchToAnotherWindow();
        }
    }
}
