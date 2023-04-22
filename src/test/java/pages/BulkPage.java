package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.BaseMainPage;

import java.util.ArrayList;
import java.util.List;

public class BulkPage extends BaseMainPage {

    @FindBy(xpath = "//section[@id='how']/h1")
    private WebElement h1Header;

    @FindBy(xpath = "//div[@class='api']/*")
    private List<WebElement> bulkFilesRequests;

    public BulkPage(WebDriver driver) {

        super(driver);
    }

    public BulkPage createGeneric() {

        return new BulkPage(getDriver());
    }

    public String getH1Header() {

        return getText(h1Header);
    }

    public List<String> getBulkFilesRequests() {

        List<String> bulkFilesRequestsText = new ArrayList<>();

        for (int i = 0; i < getListSize(bulkFilesRequests); i += 2) {
            bulkFilesRequestsText.add(getText(bulkFilesRequests.get(i)));
        }

        return bulkFilesRequestsText;
    }
}
