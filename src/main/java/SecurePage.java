import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The SecurePage class represents a page object for handling the secure page functionality.
 * It is written using the Page Object design pattern.
 * This class exposes methods that represent actions that can be performed on the secure web page.
 * Elements on the page (e.g., success messages) are encapsulated within the page class.
 *
 * Example usage:
 * <pre>{@code
 * SecurePage securePage = new SecurePage(driver);
 * String successMessage = securePage.getSecurePageMessage();
 * }</pre>
 *
 * Note: This class assumes that the underlying WebDriver instance (driver) is properly initialized.
 *
 * @see <a href="https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/">Page Object models documentation</a>
 */
public class SecurePage {
    private WebDriver driver;
    private By securePageMessage = By.cssSelector(".flash.success");

    /**
     * This method is the constructor for the SecurePage class.
     * Constructs a new SecurePage object with the specified WebDriver instance.
     * <p>
     * This constructor initializes the SecurePage with the provided WebDriver instance.
     * </p>
     * @param driver The WebDriver instance used for browser automation.
     */
    public SecurePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method is used to retrieve the text content of the success message web element.
     * @return Success Message text
     */
    public String getSecurePageMessage() {
        return driver.findElement(securePageMessage).getText();
    }
}
