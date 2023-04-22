package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CurrentWeatherPage;
import pages.StartPage;
import utils.TestData;
import utils.TestUtils;

import java.util.List;

public class StartTest extends BaseTest {

    @Test
    public void testH2Header_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";

        StartPage startPage = openBaseURL();

        final String oldCityCountryName = startPage.getCityCountryName();

        startPage.clickSearchCityField();
        startPage.inputSearchCriteria(cityName);
        startPage.clickSearchButton();
        startPage.clickParisInDropDownList();
        startPage.waitForCityCountryNameChanged(oldCityCountryName);

        String newCityCountryNames = startPage.getCityCountryName();

        Assert.assertEquals(newCityCountryNames, expectedCityCountryNames);
    }

    @Test
    public void testH2Header_WhenSearchingCityCountryChain() {
        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";

        StartPage startPage = openBaseURL();

        final String oldCityCountryName = startPage.getCityCountryName();

        String newCityCountryNames =
                startPage
                        .clickSearchCityField()
                        .inputSearchCriteria(cityName)
                        .clickSearchButton()
                        .clickParisInDropDownList()
                        .waitForCityCountryNameChanged(oldCityCountryName)
                        .getCityCountryName();

        Assert.assertEquals(newCityCountryNames, expectedCityCountryNames);
    }

    @Test
    public void testH1HeaderColorAndFontSize() {
        final String expectedH1Color = "rgba(0, 0, 0, 0)";
        final String expectedH1FontSize = "45px";

        StartPage startPage = openBaseURL();

        String actualH1Color = startPage.getH1Color();
        String actualH1FontSize = startPage.getH1FontSize();

        Assert.assertEquals(actualH1Color, expectedH1Color);
        Assert.assertEquals(actualH1FontSize, expectedH1FontSize);
    }

    @Test
    public void testAmountOfIcons_OnDifferentWeatherPopUp() {
        final int expectedAmountOfIcons = 9;

        int actualIconsAmount =
                openBaseURL()
                        .clickDifferentWeatherButton()
                        .waitUntilDifferentWeatherPopUpIsVisible()
                        .getAmountOfIconsOnDifferentWeatherPopUp();

        Assert.assertEquals(actualIconsAmount, expectedAmountOfIcons);
    }

    @Test
    public void testIconsHighlighted_OnDifferentWeatherPopUp() {
        final String expectedValueOfAttribute = "activeIcon";

        StartPage startPage = openBaseURL();

        List<WebElement> iconsDifferentWeather =
                startPage
                        .clickDifferentWeatherButton()
                        .waitUntilDifferentWeatherPopUpIsVisible()
                        .getListOfIconsOnDifferentWeatherPopUp();

        for (WebElement icon : iconsDifferentWeather) {
            String actualValueAttribute =
                    startPage
                            .clickIconOnDifferentWeatherPopUp(icon)
                            .getClassAttribute(icon);

            Assert.assertEquals(actualValueAttribute, expectedValueOfAttribute);
        }
    }

    @Test
    public void testRefreshPage() {
        final String expectedTextWhenLoading = "Loading";

        String actualTextWhenLoading =
                openBaseURL()
                        .getTopMenu()
                        .clickLogo()
                        .getLoadingText("aria-label");

        Assert.assertEquals(actualTextWhenLoading, expectedTextWhenLoading);
    }

    @Test
    public void testCurrentLocationButton() {
        final String cityName = "Norway";
        final String expectedCityCountryName = "London, GB";

        String actualCityCountryName =
                openBaseURL()
                        .clickSearchCityField()
                        .inputSearchCriteria(cityName)
                        .clickSearchButton()
                        .clickCityNorway()
                        .clickLocationButton()
                        .getCityCountryName();

        Assert.assertEquals(actualCityCountryName, expectedCityCountryName);
    }

    @Test
    public void testXButtonIsDisplayed_OnDifferentWeatherPopUp() {
        StartPage startPage =
                openBaseURL()
                        .clickDifferentWeatherButton();

        Assert.assertTrue(startPage.isXButtonDisplayed(), "X button is not displayed");
    }

    @Test
    public void testTempUnitChangedToC_WhenSwitchingToMetric() {
        final String expectedTempUnit = "°C";

        String actualTempUnit = openBaseURL()
                .switchToMetric()
                .waitForGreyContainerDisappeared()
                .getTempUnit();

        Assert.assertEquals(actualTempUnit, expectedTempUnit);
    }

    @Test
    public void testTempUnitChangedToF_WhenSwitchingToImperial() {
        final String expectedTempUnit = "°F";

        String actualTempUnit = openBaseURL()
                .switchToImperial()
                .waitForGreyContainerDisappeared()
                .getTempUnit();

        Assert.assertEquals(actualTempUnit, expectedTempUnit);
    }

    @Test
    public void testSendButtonIsDisplayed_OnDifferentWeatherPopUp() {
        StartPage startPage =
                openBaseURL()
                        .clickDifferentWeatherButton();

        Assert.assertTrue(startPage.isSendButtonDisplayed(), "Send button is not displayed");
    }

    @Test
    public void testErrorMessageWhenCityDoesNotExists() {
        final String cityName = TestUtils.getRandomName();
        final String expectedErrorText = "No results for " + cityName;

        String actualErrorText =
                openBaseURL()
                        .clickSearchCityField()
                        .inputSearchCriteria(cityName)
                        .clickSearchButton()
                        .getErrorText();

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @Test
    public void testSearchErrorMessageBackgroundColor() {
        final String cityName = TestUtils.getRandomName();
        final String expectedSearchErrorMessageColor = "rgba(120, 203, 191, 0.8)";

        String actualSearchErrorMessageColor =
                openBaseURL()
                        .clickSearchCityField()
                        .inputSearchCriteria(cityName)
                        .clickSearchButton()
                        .getErrorButtonBackgroundColor();

        Assert.assertEquals(actualSearchErrorMessageColor, expectedSearchErrorMessageColor);
    }

    @Test
    public void testOneIconHighlightedByDefault_OnDifferentWeatherPopUp() {
        final String expectedIconColor = "#ececed";
        StartPage startPage = openBaseURL();

        int activeIcon =
                startPage
                        .clickDifferentWeatherButton()
                        .waitUntilDifferentWeatherPopUpIsVisible()
                        .countActiveIconsInDifferentWeatherContainer();

        final String activeIconColor = startPage.getActiveIconBackgroundColorInHEX();

        Assert.assertEquals(activeIcon, 1);
        Assert.assertEquals(activeIconColor, expectedIconColor);
    }

    @Test
    public void testDayListTempInC_WhenSwitchingToMetric() {
        final List<String> expectedResult = List.of("°C", "°C", "°C", "°C", "°C", "°C", "°C", "°C");

        StartPage startPage =
                openBaseURL()
                        .switchToMetric()
                        .waitForGreyContainerDisappeared();

        Assert.assertEquals(startPage.getTempsAndUnitsList(), expectedResult);
    }

    @Test
    public void testDayListTempInF_WhenSwitchingToImperial() {
        final List<String> expectedResult = List.of("°F", "°F", "°F", "°F", "°F", "°F", "°F", "°F");

        StartPage startPage =
                openBaseURL()
                        .switchToImperial()
                        .waitForGreyContainerDisappeared();

        Assert.assertEquals(startPage.getTempsAndUnitsList(), expectedResult);
    }

    @Test
    public void testDifferentWeatherButtonOpensDifferentWeatherContainer() {
        final String expectedHeader = "Different weather";

        String actualHeader =
                openBaseURL()
                        .clickDifferentWeatherButton()
                        .getHeaderForDifferentWeatherContainer();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testTextInCookiesAgreement() {
        final String expectedTextPanel =
                "We use cookies which are essential for the site to work. We also use non-essential "
                        + "cookies to help us improve our services. Any data collected is anonymised. "
                        + "You can allow all cookies or manage them individually.";

        String actualTextPanel =
                openBaseURL()
                        .waitForFooterPanelToBeVisible()
                        .waitForElementToBeVisible()
                        .getBottomPanelText();

        Assert.assertEquals(actualTextPanel, expectedTextPanel);
    }

    @Test
    public void testTemperatureValueIncreasedByOne_WhenClickingUp() {
        StartPage startPage = openBaseURL();

        int temperatureValueInContainer = startPage
                .clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .clickMoreOptionsDropDown()
                .getTemperatureValueInDifferentWeatherContainer();

        startPage.clickUpKeyInTemperatureInput();

        int increasedTemperatureValueInContainer = startPage.getTemperatureValueInDifferentWeatherContainer();

        Assert.assertEquals(increasedTemperatureValueInContainer - temperatureValueInContainer, 1);
    }

    @Test
    public void testLocationButtonIsDisplayedAndClickable() {
        final String expectedNotificationMessage = "Location unavailable. Displaying default location: London";

        StartPage startPage = openBaseURL();
        Assert.assertTrue(startPage.isLocationButtonDisplayed());

        String actualNotificationMessage =
                startPage
                        .clickLocationButton()
                        .getNotificationMessage();

        Assert.assertEquals(actualNotificationMessage, expectedNotificationMessage);
    }

    @Test
    public void testAPIIconsAreDisplayed() {
        final int expectedAPIIconsQuantity = 5;
        final List<String> expectedAPIIconsNames = List.of(
                "current\nweather\n(current)",
                "hourly\nforecast\n(4 days)",
                "daily\nforecast\n(16 days)",
                "climatic\nforecast\n(30 days)",
                "historical\nweather\n(1 month, 1 year)"
        );

        StartPage startPage =
                openBaseURL()
                        .scrollByCurrentWeatherIcon();

        Assert.assertEquals(startPage.getDisplayedAPIIcons(), startPage.getApiIcons());
        Assert.assertEquals(startPage.getAPIIconsQuantity(), expectedAPIIconsQuantity);
        Assert.assertEquals(startPage.getAPIIconsNames(), expectedAPIIconsNames);
    }

    @Test
    public void testMainPageHeaders() {
        final String expectedH1Header = "OpenWeather";
        final String expectedH2Header = "Weather forecasts, nowcasts and history in a fast and elegant way";

        StartPage startPage = openBaseURL();

        Assert.assertEquals(startPage.getH1Header(), expectedH1Header);
        Assert.assertEquals(startPage.getH2Header(), expectedH2Header);
    }

    @Test
    public void testDataSourceOptions() {
        final List<String> expectedDataSourceOptionsTexts = List.of(
                "Personal feelings",
                "Own weather station or devices",
                "Local weather provider",
                "Global weather provider",
                "Other"
        );

        List<String> actualDataSourceOptionsTexts =
                openBaseURL()
                        .clickDifferentWeatherButton()
                        .waitUntilDifferentWeatherPopUpIsVisible()
                        .clickMoreOptionsDropDown()
                        .clickDataSourceDropDown()
                        .getDataSourceOptionsTexts();

        Assert.assertEquals(actualDataSourceOptionsTexts, expectedDataSourceOptionsTexts);
    }

    @Test
    public void testChosenDataSourceOptionIsSavedAfterClickingLessOptions() {
        StartPage startPage = openBaseURL();

        String dataSourceDropDownTextBefore =
                startPage
                        .clickDifferentWeatherButton()
                        .waitUntilDifferentWeatherPopUpIsVisible()
                        .clickMoreOptionsDropDown()
                        .clickDataSourceDropDown()
                        .clickFirstDataSourceOption()
                        .getDataSourceDropDownText();

        String dataSourceDropDownTextAfter =
                startPage
                        .clickLessOptionsDropDown()
                        .clickMoreOptionsDropDown()
                        .getDataSourceDropDownText();

        Assert.assertEquals(dataSourceDropDownTextAfter, dataSourceDropDownTextBefore);
    }

    @Test
    public void testEmailAndAdditionalInfoIsNotSavedAfterClickingOutOfContainer() {
        StartPage startPage = openBaseURL();

        String emailTextBefore =
                startPage
                        .clickDifferentWeatherButton()
                        .waitUntilDifferentWeatherPopUpIsVisible()
                        .clickMoreOptionsDropDown()
                        .inputTextInEmailTextBox()
                        .getEmailTextBoxText();

        String additionalInfoTextBefore =
                startPage
                        .inputTextInAdditionalInfoTextArea()
                        .getAdditionalInfoText();

        String emailTextAfter =
                startPage
                        .clickOutOfDifferentWeatherContainer()
                        .clickDifferentWeatherButton()
                        .waitUntilDifferentWeatherPopUpIsVisible()
                        .clickMoreOptionsDropDown()
                        .getEmailTextBoxText();

        String additionalInfoTextAfter =
                startPage
                        .getAdditionalInfoText();

        Assert.assertNotEquals(emailTextAfter, emailTextBefore);
        Assert.assertNotEquals(additionalInfoTextAfter, additionalInfoTextBefore);
    }

    @Test
    public void testCorrect8DaysForecastCalendarSequence() {

        List<String> listOfEightDaysData = openBaseURL().getListOfEightDaysDataText();

        String expectedResult = new StartPage(getDriver()).getEightDaysForecastCalendarSequenceText();

        String actualResult = listOfEightDaysData.toString().substring(1, listOfEightDaysData.toString().length() - 1);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCurrentWeatherAPIIconNavigatesToCorrespondingPage() {
        final String basePageTitle = "Сurrent weather and forecast - OpenWeatherMap";
        final String expectedUrl = "https://openweathermap.org/current";
        final String expectedTitle = "Current weather data - OpenWeatherMap";

        CurrentWeatherPage currentWeatherPage =
                openBaseURL()
                        .scrollByOrangeBackground()
                        .clickCurrentWeatherIcon();

        Assert.assertNotEquals(currentWeatherPage.getTitle(), basePageTitle);
        Assert.assertEquals(currentWeatherPage.getCurrentURL(), expectedUrl);
        Assert.assertEquals(currentWeatherPage.getTitle(), expectedTitle);
    }

    @Test(dataProvider = "ApiIconsMainPage", dataProviderClass = TestData.class)
    public void testAPIIconsNavigateToCorrespondingPages(
            int index, String iconName, String iconDescription, String href, String expectedURL, String expectedTitle) {

        StartPage startPage = openBaseURL();

        final String oldURL = startPage.getCurrentURL();
        final String oldTitle = startPage.getTitle();

        startPage
                .scrollByOrangeBackground()
                .clickApiIcon(index);

        String actualURL = getDriver().getCurrentUrl();
        String actualTitle = getDriver().getTitle();

        Assert.assertNotEquals(oldURL, actualURL);
        Assert.assertNotEquals(oldTitle, actualTitle);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
