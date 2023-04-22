package pages.base_abstract;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.footer_menu.AboutUsPage;
import pages.home.HomeAskQuestionPage;
import pages.top_menu.FAQPage;

import java.util.List;

public abstract class BaseFooter<Footer extends BaseFooter> extends BaseComponents {

    protected static final String FOOTER_MENU = "//div[@class = 'inner-footer-container']";

    @FindBy(xpath = FOOTER_MENU)
    protected WebElement footerMenu;

    @FindBy(xpath = FOOTER_MENU + "//ul/li/a")
    private List<WebElement> innerFooterMenuLink;

    @FindBy(xpath = FOOTER_MENU + "//a[@href='https://openweather.co.uk/privacy-policy']")
    private WebElement privacyPolicyFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//a[contains(@href, '/about-us')]")
    private WebElement aboutUsFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//a[@href='https://apps.apple.com/gb/app/openweather/id1535923697'] "
            + "[@target='_blank']")
    private WebElement downloadOnTheAppStoreLinkFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//div[contains(@class, 'social')]/a")
    private List<WebElement> socialPanelIconsFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//a[@href='https://github.com/search?q=openweathermap&ref=cmdform']")
    private WebElement githubIconFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//p[text()='Download OpenWeather app']")
    private WebElement downloadOpenWeatherAppText;

    @FindBy(xpath = FOOTER_MENU + "//a[contains(@href, '/questions')]")
    private WebElement askQuestionFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//div[@class='my-5']/div/a")
    private List<WebElement> storeIcons;

    @FindBy(xpath = FOOTER_MENU + "//a[@href='https://play.google.com/store/apps/details?id=uk.co.openweather']")
    private WebElement iconGooglePlay;

    @FindBy(xpath = FOOTER_MENU + "//*[contains(text(), 'All rights reserved')]")
    private WebElement copyright;

    @FindBy(xpath = FOOTER_MENU + "//a[@href='https://www.facebook.com/groups/270748973021342']")
    private WebElement iconFacebook;

    @FindBy(xpath = FOOTER_MENU + "//a")
    private List<WebElement> footerMenuLinks;

    @FindBy(xpath = "//div[contains(@class, 'social')]")
    private WebElement socialPanelFooterMenu;

    @FindBy(xpath = FOOTER_MENU + "//a[contains(@href, '/faq')]")
    private WebElement faqFooterMenu;

    protected BaseFooter(WebDriver driver) {
        super(driver);
    }

    public abstract Footer createFooter();

    public List<WebElement> getInnerFooterMenuLinks() {

        return innerFooterMenuLink;
    }

    public void clickFooterMenuExternalLink(int index) {
        click(getInnerFooterMenuLinks().get(index));
        switchToAnotherWindow();
        getWait20().until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    public AboutUsPage clickAboutUsFooterMenu() {
        click20(aboutUsFooterMenu);

        return new AboutUsPage(getDriver());
    }

    public void clickPrivacyPolicyFooterMenu() {
        click(privacyPolicyFooterMenu);
    }

    public void clickAppStoreIcon() {
        click20(downloadOnTheAppStoreLinkFooterMenu);
    }

    public int getSocialPanelSize() {

        return getListSize(socialPanelIconsFooterMenu);
    }

    public void clickGitHubIcon() {
        click20(githubIconFooterMenu);
    }

    public Footer clickAskQuestionFooterMenu() {
        wait10ElementToBeClickable(askQuestionFooterMenu);
        click20(askQuestionFooterMenu);

        return createFooter();
    }

    public HomeAskQuestionPage switchToHomeAskQuestionPage() {
        switchToAnotherWindow();

        return new HomeAskQuestionPage(getDriver());
    }

    public void clickGooglePlayIcon() {
        click20(iconGooglePlay);
    }

    public String getCopyright() {

        return getText(copyright);
    }

    public void clickFacebookIcon() {
        click20(iconFacebook);
    }

    public int getFooterMenuLinksCount() {
        areAllElementsVisibleAndClickable(footerMenuLinks);

        return getListSize(footerMenuLinks);
    }

    public int getStoresIconsCount() {

        return getListSize(storeIcons);
    }

    public boolean isStorePanelDisplayed() {

        return areElementsInListDisplayed(storeIcons);
    }

    public boolean isSocialPanelDisplayed() {

        return isElementDisplayed(socialPanelFooterMenu);
    }

    public FAQPage clickFAQFooterMenu() {
        click20(faqFooterMenu);

        return new FAQPage(getDriver());
    }

    public void clickFooterMenu(int index) {
        click(getInnerFooterMenuLinks().get(index));
        if (getDriver().getWindowHandles().size() > 1) {
            switchToAnotherWindow();
        }
    }
}
