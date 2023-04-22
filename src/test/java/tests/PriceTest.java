package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.top_menu.PricePage;

import java.util.Arrays;
import java.util.List;

public class PriceTest extends BaseTest {

    @Test
    public void testTransparentButtonsAmount() {
        final int expectedTransparentButtons = 18;

        int actualTransparentButtons = openBaseURL()
                .getTopMenu()
                .clickPricingMenu()
                .waitAllElementsVisibleAndClickable()
                .getTransparentButtonsAmount();

        Assert.assertEquals(actualTransparentButtons, expectedTransparentButtons);
    }

    @Test
    public void testH1Header() {
        final String expectedHeader = "Pricing";

        String actualHeader = openBaseURL()
                .getTopMenu()
                .clickPricingMenu()
                .getBreadcrumbs()
                .getH1Header();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testCurrentWeatherAndForecastsCollectionsNames() {
        final List<String> expectedNames = Arrays.asList(
                "Free", "Startup", "Developer", "Professional", "Enterprise"
        );

        List<String> collectionsNames = openBaseURL()
                .getTopMenu()
                .clickPricingMenu()
                .getCollectionsNames();

        Assert.assertEquals(collectionsNames, expectedNames);
    }

    @Test
    public void testH2HeaderOnAlertsTable() {
        final String expectedH2Header = "Special products";

        String actualH2Header = openBaseURL()
                .getTopMenu()
                .clickPricingMenu()
                .getAlertsH2Header();

        Assert.assertEquals(actualH2Header, expectedH2Header);
    }

    @Test
    public void testH4HeadersOnAlertsTable() {
        final List<String> expectedH4Headers = Arrays.asList(
                "Solar Radiation API",
                "Global Weather Alerts Push notifications",
                "Road Risk API (advanced configuration)",
                "Global Precipitation Map - Forecast and historical data",
                "Weather Maps 2.0 with 1-hour step"
        );

        List<String> actualH4Headers = openBaseURL()
                .getTopMenu()
                .clickPricingMenu()
                .getH4Headers();

        Assert.assertEquals(actualH4Headers, expectedH4Headers);
    }

    @Test
    public void testTransparentButtonsLabels() {
        final List<String> expectedTransparentButtonsLabels = Arrays.asList(
                "Get API key", "Subscribe", "Subscribe", "Subscribe", "Subscribe",
                "Get access", "Get access", "Get access", "Get access", "Get access",
                "Get", "Get", "Subscribe", "Subscribe", "Get",
                "Get access", "Get access", "Learn more");

        List<String> actualTransparentButtonsLabels = openBaseURL()
                .getTopMenu()
                .clickPricingMenu()
                .getTransparentButtonsLabels();

        Assert.assertEquals(actualTransparentButtonsLabels, expectedTransparentButtonsLabels);
    }

    @Test
    public void testH2Headers() {
        final int expectedAmountH2Headers = 7;
        final List<String> expectedH2Headers = List.of(
                "\"One Call by Call\" subscription plan",
                "Professional collections",
                "Current weather and forecasts collection",
                "Special products",
                "Historical weather collection",
                "Special products",
                "Free data for students"
        );

        List<String> actualH2Headers = openBaseURL()
                .getTopMenu()
                .clickPricingMenu()
                .getH2Headers();

        PricePage pricePage = new PricePage(getDriver());

        int actualAmountH2Headers = pricePage
                .getH2HeadersAmount();

        Assert.assertEquals(actualH2Headers, expectedH2Headers);
        Assert.assertEquals(actualAmountH2Headers, expectedAmountH2Headers);
    }

    @Test
    public void testDetailedPricingButtons() {
        final int expectedDetailedPricingButtons = 2;
        final List<String> expectedLabels = Arrays.asList("Detailed pricing", "Detailed pricing");

        PricePage pricePage = openBaseURL().getTopMenu().clickPricingMenu();

        Assert.assertEquals(pricePage.getDetailedPricingButtonsAmount(), expectedDetailedPricingButtons);
        Assert.assertEquals(pricePage.getDetailedPricingButtonsLabels(), expectedLabels);
    }
}