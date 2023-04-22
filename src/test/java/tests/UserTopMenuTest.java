package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.home.HomePage;
import utils.TestUtils;

import java.util.List;

public class UserTopMenuTest extends BaseTest {

    @Test
    public void testUserTopMenusAmount() {
        final int expectedHomeTopMenusAmount = 9;

        HomePage homePage = new HomePage(getDriver());

        int actualHomeTopMenusAmount = openBaseURL()
                .getTopMenu()
                .signIn()
                .getUserTopMenu()
                .getUserTopMenusAmount();

        Assert.assertEquals(actualHomeTopMenusAmount, expectedHomeTopMenusAmount);
    }

    @Test
    public void testUserTopMenus() {
        List<String> expectedURLs = List.of(
                "https://home.openweathermap.org/",
                "https://home.openweathermap.org/myservices",
                "https://home.openweathermap.org/api_keys",
                "https://home.openweathermap.org/subscriptions",
                "https://home.openweathermap.org/payments",
                "https://home.openweathermap.org/blocks",
                "https://home.openweathermap.org/marketplace/my_orders",
                "https://home.openweathermap.org/home",
                "https://home.openweathermap.org/questions");

        List<String> actualURLs = openBaseURL()
                .getTopMenu()
                .signIn()
                .getUserTopMenu()
                .clickUserTopMenus();

        TestUtils.waitForPageLoaded(getDriver());

        Assert.assertEquals(actualURLs, expectedURLs);
    }

    @Test
    public void testDropdownMenuTexts() {
        final List<String> expectedDropdownMenuTexts = List.of(
                "My services",
                "My API keys",
                "My payments",
                "My profile",
                "Logout"
        );

        List<String> actualDropdownMenuTexts = openBaseURL()
                .getTopMenu()
                .signIn()
                .clickUserNameMenu()
                .getUserNameDropdownMenuTexts();

        Assert.assertEquals(actualDropdownMenuTexts, expectedDropdownMenuTexts);
    }
}
