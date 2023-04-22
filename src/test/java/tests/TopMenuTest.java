package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.OurInitiativesPage;
import pages.StartPage;
import pages.home.HomeMarketplacePage;
import pages.home.HomeUsersSignInPage;
import pages.top_menu.*;
import utils.ProjectConstants;
import utils.TestData;

import java.util.List;

public class TopMenuTest extends BaseTest {

    @Test
    public void testAPIMenuNavigatesToAPIPage() {
        final String expectedURL = "https://openweathermap.org/api";
        final String expectedTitle = "Weather API - OpenWeatherMap";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickAPIMenu()
                .getCurrentURL();

        String actualTitle = new APIPage(getDriver()).getTitle();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testMarketplaceMenuNavigatesToMarketplacePage() {
        final String expectedURL = "https://home.openweathermap.org/marketplace";
        final String expectedTitle = "Marketplace: History Bulk, History Forecast Bulk, "
                + "Historical Weather Data by State for all ZIP codes, USA - OpenWeather";

        final String oldURL = openBaseURL().getCurrentURL();

        StartPage startPage = new StartPage(getDriver());

        HomeMarketplacePage homeMarketplacePage = startPage
                .getTopMenu()
                .clickMarketplaceMenu()
                .switchToMarketplaceWindow();


        String actualURL = homeMarketplacePage
                .getCurrentURL();

        String actualTitle = homeMarketplacePage.getTitle();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testGuideMenuNavigatesToGuidePage() {
        final String expectedURL = "https://openweathermap.org/guide";
        final String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickGuideMenu()
                .getCurrentURL();

        String actualTitle = new GuidePage(getDriver()).getTitle();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testMapsMenuNavigatesToWeatherMapsPage() {
        final String expectedURL = "https://openweathermap.org/weathermap";
        final String expectedTitle = "Interactive weather maps - OpenWeatherMap";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickMapsMenu()
                .getFormattedURL();

        String actualTitle = new WeatherMapsPage(getDriver()).getTitle();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testHowToStartMenuNavigatesToHowToStartPage() {
        final String expectedURL = "https://openweathermap.org/appid";
        final String expectedTitle = "How to start to work with Openweather API - OpenWeatherMap";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickSupportMenu()
                .clickHowToStartSupportSubmenu()
                .getCurrentURL();

        String actualTitle = new HowToStartPage(getDriver()).getTitle();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testPlaceholderIsAvailable_TopMenu() {
        final String expectedInnerTextOfPlaceholder = "Weather in your city";
        final String attribute = "placeholder";

        StartPage startPage = openBaseURL();
        Assert.assertTrue(startPage.getTopMenu().isPlaceholderDisplayed());

        String actualInnerTextOfPlaceholder = startPage
                .getTopMenu()
                .getInnerTextOfPlaceholder(attribute);

        Assert.assertEquals(actualInnerTextOfPlaceholder, expectedInnerTextOfPlaceholder);
    }

    @Test
    public void testOurInitiativesMenuNavigatesToOurInitiativesPage() {
        final String expectedURL = "https://openweathermap.org/our-initiatives";
        final String expectedTitle = "Our Initiatives - OpenWeatherMap";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickOurInitiativesMenu()
                .getCurrentURL();

        String actualTitle = new OurInitiativesPage(getDriver()).getTitle();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testSupportMenuLinksTexts() {
        final List<String> expectedList = List.of(
                "FAQ",
                "How to start",
                "Ask a question"
        );

        List<String> actualList =
                openBaseURL()
                        .getTopMenu()
                        .clickSupportMenu()
                        .getLinksText();

        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testSupportMenuIsClickable() {
        final String expectedIfVisible = "dropdown-menu dropdown-visible";
        final String expectedIfNotVisible = "dropdown-menu";

        openBaseURL();

        String actualIfVisible = new StartPage(getDriver())
                .getTopMenu()
                .clickSupportMenu()
                .getSupportMenuIsActiveValue();

        Assert.assertTrue(new StartPage(getDriver()).getTopMenu().isSupportDropdownContainerDisplayed());
        Assert.assertEquals(actualIfVisible, expectedIfVisible);

        String actualIfNotVisible = new StartPage(getDriver())
                .getTopMenu()
                .clickSupportMenu()
                .getSupportMenuIsActiveValue();

        Assert.assertEquals(actualIfNotVisible, expectedIfNotVisible);
    }

    @Test
    public void testPartnersMenuNavigatesToPartnersPage() {
        final String expectedURL = "https://openweathermap.org/examples";
        final String expectedTitle = "Partners and solutions - OpenWeatherMap";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickPartnersMenu()
                .getCurrentURL();

        String actualTitle = new PartnersPage(getDriver()).getTitle();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testTopMenuLinkAmount() {
        final int expectedTopMenuLinkAmount = 12;

        int actualTopMenuLinkAmount =
                openBaseURL()
                        .getTopMenu()
                        .topMenuLinkAmount();

        Assert.assertEquals(actualTopMenuLinkAmount, expectedTopMenuLinkAmount);
    }

    @Test
    public void testCompanyLogoNavigatesToBaseURL() {
        final String expectedURL = "https://openweathermap.org/";
        final String expectedTitle = "Сurrent weather and forecast - OpenWeatherMap";

        openBaseURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickLogo()
                .getCurrentURL();

        String actualTitle = new StartPage(getDriver()).getTitle();

        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testFAQSupportSubmenuNavigatesToFAQPage() {
        final String expectedURL = "https://openweathermap.org/faq";
        final String expectedTitle = "Frequently Asked Questions - OpenWeatherMap";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickSupportMenu()
                .clickFAQSupportSubmenu()
                .getCurrentURL();

        String actualTitle = new FAQPage(getDriver()).getTitle();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testDashboardMenuNavigatesToDashboardPage() {
        final String expectedURL = "https://openweathermap.org/weather-dashboard";
        final String expectedTitle = "Weather dashboard - OpenWeatherMap";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickDashboardMenu()
                .getCurrentURL();

        String actualTitle = new WeatherDashboardPage(getDriver()).getTitle();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testPricingMenuNavigatesToPricePage() {
        final String expectedURL = "https://openweathermap.org/price";
        final String expectedTitle = "Pricing - OpenWeatherMap";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickPricingMenu()
                .getCurrentURL();

        String actualTitle = new PricePage(getDriver()).getTitle();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(dataProvider = "TopMenuTestData", dataProviderClass = TestData.class)
    public void testEachTopMenuNavigatesToCorrespondingPage(
            int index, String menuName, String href, String expectedURL, String expectedTitle) {

        StartPage startPage = openBaseURL();

        String oldURL = startPage.getCurrentURL();
        String oldTitle = startPage.getTitle();

        if (index < 12) {
            startPage.getTopMenu().clickTopMenu(index);
        } else {
            index -= 12;
            startPage.getTopMenu().clickSupportMenu().clickSupportMenuLinks(index);
        }

        String actualURL = getDriver().getCurrentUrl();
        String actualTitle = getDriver().getTitle();

        if (index != 0) {
            Assert.assertNotEquals(actualURL, oldURL);
            Assert.assertNotEquals(actualTitle, oldTitle);
        }

        if (index != 6) {
            Assert.assertEquals(actualURL, expectedURL);
        } else {
            Assert.assertTrue(actualURL.contains(expectedURL));
        }

        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(dataProvider = "ExternalTopMenuTestData", dataProviderClass = TestData.class)
    public void testExternalTopMenuNavigatesToCorrespondingPage(
            int index, String menuName, String href, String expectedURL, String expectedTitle) {

        StartPage startPage = openBaseURL();

        String oldURL = startPage.getCurrentURL();
        String oldTitle = startPage.getTitle();

        startPage.getTopMenu().clickTopMenuExternalLink(index);

        String actualURL = getExternalPageURL();
        String actualTitle = getExternalPageTitle();

        if (index != 0) {
            Assert.assertNotEquals(actualURL, oldURL);
            Assert.assertNotEquals(actualTitle, oldTitle);
            Assert.assertEquals(actualURL, expectedURL);
            Assert.assertEquals(actualTitle, expectedTitle);
        }
    }

    @Test
    public void testHamburgerMenuIsAvailableAndHasOptions_TopMenu() {
        final int expectedNumberOfOptionsHamburgerMenu = 12;
        final List<String> expectedHamburgerMenuList = List.of(
                "Guide", "API", "Dashboard", "Marketplace", "Pricing",
                "Maps", "Our Initiatives", "Partners", "Blog", "For Business",
                "Ask a question", "Sign in"
        );
        openBaseURL()
                .getTopMenu()
                .setWindowWithHamburgerMenu(ProjectConstants.WIDTH_HAMBURGER_MENU, ProjectConstants.HEIGHT_HAMBURGER_MENU)
                .getTopMenu()
                .clickHamburgerMenuIcon();

        int actualNumberOfOptionsHamburgerMenu = new StartPage(getDriver())
                .getTopMenu()
                .getNumberOfOptionsHamburgerMenu();

        List<String> actualHamburgerMenuList = new StartPage(getDriver())
                .getTopMenu()
                .getHamburgerMenuList();

        Assert.assertTrue(new StartPage(getDriver()).getTopMenu().isHamburgerIconDisplayed());
        Assert.assertTrue(new StartPage(getDriver()).getTopMenu().getNumberOfOptionsHamburgerMenu() > 0);
        Assert.assertEquals(actualNumberOfOptionsHamburgerMenu, expectedNumberOfOptionsHamburgerMenu);
        Assert.assertEquals(actualHamburgerMenuList, expectedHamburgerMenuList);
    }

    @Test
    public void testHamburgerMenuHasLogo() {
        final String expectedURL = "https://openweathermap.org/";

        openBaseURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .setWindowWithHamburgerMenu(ProjectConstants.WIDTH_HAMBURGER_MENU, ProjectConstants.HEIGHT_HAMBURGER_MENU)
                .getTopMenu()
                .clickLogo().getCurrentURL();

        Assert.assertTrue(new StartPage(getDriver()).getTopMenu().isLogoIconDisplayed());

        Assert.assertEquals(actualURL, expectedURL);
    }

    @Test
    public void testNavigationBarSearchField_NavigatesToFindPage() {
        final String expectedURL = "https://openweathermap.org/find?q=Barcelona";
        final String expectedName = "Barcelona";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickSearchFieldTopMenu()
                .inputSearchCriteriaAndEnter(expectedName)
                .getCurrentURL();

        Assert.assertNotEquals(oldURL, actualURL);
        Assert.assertEquals(actualURL, expectedURL);
    }

    @Test
    public void testSignInMenuNavigatesToSignInPage() {
        final String expectedURL = "https://home.openweathermap.org/users/sign_in";
        final String expectedWelcomeMessage = "Sign In To Your Account";

        final String oldURL = openBaseURL().getCurrentURL();

        String actualURL = new StartPage(getDriver())
                .getTopMenu()
                .clickSignInMenu()
                .getCurrentURL();

        String actualWelcomeMessage = new HomeUsersSignInPage(getDriver()).getWelcomeMessage();

        Assert.assertNotEquals(actualURL, oldURL);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualWelcomeMessage, expectedWelcomeMessage);
    }
}
