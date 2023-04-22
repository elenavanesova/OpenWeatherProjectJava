package pages.base_abstract;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import pages.StartPage;
import pages.home.HomeAskQuestionPage;
import pages.home.HomeMarketplacePage;
import pages.home.HomePage;
import pages.home.HomeUsersSignInPage;
import pages.top_menu.FAQPage;
import pages.top_menu.HowToStartPage;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTopMenu<Top extends BaseTopMenu> extends BaseComponents {

    protected static final String TOP_MENU_ID = "//div[@id='desktop-menu']";
    protected static final String SUPPORT_DROPDOWN_ID = "//ul[@id='support-dropdown-menu']";
    @FindBy(id = "support-dropdown")
    protected WebElement supportTopMenu;
    @FindBy(xpath = "//li[@class='logo']/a")
    protected WebElement logo;
    @FindBy(xpath = TOP_MENU_ID + "//a[@href='https://home.openweathermap.org/marketplace']")
    private WebElement marketplaceTopMenu;
    @FindBy(xpath = TOP_MENU_ID + "//li[@class='user-li']/a")
    private WebElement signInTopMenu;
    @FindBy(xpath = "//li[@class='with-dropdown']//ul")
    private WebElement supportTopMenuDropdown;

    @FindBy(xpath = "//li[@class='with-dropdown']//li/a")
    protected List<WebElement> supportTopMenuDropdownLinks;

    @FindBy(xpath = SUPPORT_DROPDOWN_ID + "//li/a[@href='/faq']")
    private WebElement faqSupportSubmenu;

    @FindBy(xpath = SUPPORT_DROPDOWN_ID + "//li/a[@href='/appid']")
    private WebElement howToStartSupportSubmenu;

    @FindBy(xpath = SUPPORT_DROPDOWN_ID + "//li/a[@href='https://home.openweathermap.org/questions']")
    private WebElement askQuestionSupportSubmenu;

    @FindBy(xpath = "//nav/ul/li[@id='hamburger']/img")
    private WebElement hamburgerTopMenuIcon;

    @FindBy(xpath = "//ul[@id='mobile-menu']/li/a")
    private List<WebElement> hamburgerTopMenuDropdownLinks;

    protected BaseTopMenu(WebDriver driver) {
        super(driver);
    }

    public abstract Top clickSupportMenu();

    public HomeUsersSignInPage clickSignInMenu() {
        click20(signInTopMenu);

        return new HomeUsersSignInPage(getDriver());
    }

    public StartPage clickLogo() {
        click(logo);

        return new StartPage(getDriver());
    }

    public boolean isLogoIconDisplayed() {

        return isElementDisplayed(logo);
    }

    public HomeUsersSignInPage signOut() {
        click(getDriver().findElement(By.id("user-dropdown")));
        click(getDriver().findElement(By.xpath("//a[@href='/users/sign_out']")));
        Reporter.log(getDriver().findElement(By.xpath("//h3")).getText(), true);

        return new HomeUsersSignInPage(getDriver());
    }

    public HomePage signIn() {
        clickSignInMenu()
                .clickClearInputRegularUserEmail()
                .clickClearInputRegularUserPassword()
                .clickSubmitButton();

        return new HomePage(getDriver());
    }

    public HomeMarketplacePage clickMarketplaceMenu() {
        click(marketplaceTopMenu);

        return new HomeMarketplacePage(getDriver());
    }

    public List<String> getLinksText() {

        return getTexts(supportTopMenuDropdownLinks);
    }

    public FAQPage clickFAQSupportSubmenu() {
        click(faqSupportSubmenu);

        return new FAQPage(getDriver());
    }

    public void setOriginalHandle() {

        getDriver().getWindowHandle();
    }

    public HowToStartPage clickHowToStartSupportSubmenu() {
        click(howToStartSupportSubmenu);
        setOriginalHandle();

        return new HowToStartPage(getDriver());
    }

    public void clickHamburgerMenuIcon() {
        click(hamburgerTopMenuIcon);
    }

    public HomeAskQuestionPage clickAskQuestionSupportSubmenu() {
        click(askQuestionSupportSubmenu);
        switchToAnotherWindow();

        return new HomeAskQuestionPage(getDriver());
    }

    public boolean isSupportDropdownContainerDisplayed() {

        return isElementDisplayed(supportTopMenuDropdown);
    }

    public boolean isHamburgerIconDisplayed() {

        return isElementDisplayed(hamburgerTopMenuIcon);
    }

    public StartPage setWindowWithHamburgerMenu(int width, int height) {
        setWindowDimensions(width, height);

        return new StartPage(getDriver());
    }

    public List<String> getHamburgerMenuList() {

        return getTexts(hamburgerTopMenuDropdownLinks);
    }

    public int getNumberOfOptionsHamburgerMenu() {

        return getListSize(hamburgerTopMenuDropdownLinks);
    }

    public String getSupportMenuIsActiveValue() {

        return getAttribute(supportTopMenuDropdown, "class");
    }

    public void clickSupportMenuLinks(int index) {
        List<WebElement> menusSupport = new ArrayList<>();
        menusSupport.addAll(supportTopMenuDropdownLinks);

        click(menusSupport.get(index));

        if (getDriver().getWindowHandles().size() > 1) {
            switchToAnotherWindow();
        }
    }
}
