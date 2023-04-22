package pages.top_menu;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base_abstract.BasePage;
import pages.components.TopMenu;

import java.util.List;

public class WeatherMapsPage extends BasePage {

    @FindBy(xpath = "//a[@class='leaflet-control-zoom-in']")
    private WebElement zoomInLoupe;

    @FindBy(xpath = "//a[@class='leaflet-control-zoom-out']")
    private WebElement zoomOutLoupe;

    @FindBy(xpath = "//div[@id='desktop-menu']//a[@href='/weathermap']")
    private WebElement searchMapMenu;

    @FindBy(xpath = "//div[@id='map']//label/span")
    private List<WebElement> weatherControlMenus;

    @FindBy(xpath = "//div[@class='leaflet-control-container']//form")
    private WebElement loopDisplayBlock;

    @FindBy(xpath = "//div[@class='leaflet-control-container']//form/input")
    private WebElement searchLoopField;

    @FindBy(xpath = "//a[@title='Nominatim Search']")
    private WebElement nominatimSearchButton;

    private final TopMenu topMenu;

    public WeatherMapsPage(WebDriver driver) {
        super(driver);
        this.topMenu = new TopMenu(getDriver());
    }

    public TopMenu getTopMenu() {
        return topMenu;
    }

    public String getZoomOutText() {

        return getText(zoomOutLoupe);
    }

    public String getZoomInText() {

        return getText(zoomInLoupe);
    }

    public List<String> getMenusTexts() {

        return getTexts(weatherControlMenus);
    }

    public WeatherMapsPage clickZoomInLoupe() {
        click(zoomInLoupe);

        return this;
    }

    public WeatherMapsPage clickZoomOutLoupe() {
        click(zoomOutLoupe);

        return this;
    }

    public WeatherMapsPage clickLoopDisplayBlock() {
        click(loopDisplayBlock);

        return this;
    }

    public WeatherMapsPage clickSearchButton() {
        click(nominatimSearchButton);

        return this;
    }

    protected void inputAndEnter(WebElement element, String text) {
        getWait10().until(ExpectedConditions.visibilityOf(searchLoopField));
        element.sendKeys(text, Keys.ENTER);
    }

    public boolean isLoopDisplayBlockDisplayed() {

        return isElementDisplayed(loopDisplayBlock);
    }

    public WeatherMapsPage waitUntilUrlContains(String text) {
        waitForUrlContains(text);

        return this;
    }
}
