package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.StartPage;
import pages.top_menu.PartnersPage;

import java.util.List;

public class PartnersTest extends BaseTest {

    @Test
    public void testH1Header() {
        final String expectedHeader = "Partners and solutions";

        String actualHeader = openBaseURL()
                .getTopMenu()
                .clickPartnersMenu()
                .getBreadcrumbs()
                .getH1Header();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testSeeOnTheWebsiteButtonNavigatesToApacheWebsite() {
        final String expectedURL = "https://camel.apache.org/components/next/weather-component.html";

        PartnersPage partnersPage = openBaseURL()
                .getTopMenu()
                .clickPartnersMenu();

        String oldURL = partnersPage.getCurrentURL();

        partnersPage
                .clickApacheCamelLink()
                .clickSeeOnWebsiteButton();

        new StartPage(getDriver()).switchToExternalPage();

        Assert.assertNotEquals(oldURL, getExternalPageURL());
        Assert.assertEquals(getExternalPageURL(), expectedURL);
    }

    @Test
    public void testLinksTextsAreAsExpected() {
        final List<String> expectedRightSideLinks = List.of(
                "Google Weather-Based Campaign Management with OpenWeatherMap API"
                , "Google Maps JavaScript API based on OpenWeatherMap API"
                , "OpenWeather current weather data in Mozilla's IoT project"
                , "Ubuntu"
                , "Android"
                , "Leaflet"
                , "Java"
                , "Go (golang)"
                , "JavaScript"
                , "CMS"
                , "Raspberry Pi"
                , "Python"
                , "PHP"
                , "Apache Camel"
                , "Desktop"
                , "Mobile applications"
                , "Big library on GitHub"
        );

        List<String> actualRightSideLinks = openBaseURL()
                .getTopMenu()
                .clickPartnersMenu()
                .getRightSideLinksText();

        Assert.assertEquals(actualRightSideLinks, expectedRightSideLinks);
    }
}
