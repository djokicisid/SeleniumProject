import Utils.WebDriverHandler;
import org.junit.jupiter.api.Test;

/**
 * The LoginTest class contains test cases for the login functionality of the application.
 * <p>
 * This class includes test method to validate the login process.
 * It uses Selenium WebDriver for browser automation to simulate user interactions.
 * </p>
 */
public class LoginTest extends WebDriverHandler {
    private LoginPage loginPage;

    /**
     * Tests the login functionality with valid credentials.
     * <p>
     * This method initializes the LoginPage, navigates to the login page,
     * enters valid username and password, clicks the login button,
     * and logs information if the login is successful.
     * </p>
     * <p>
     * Preconditions:
     * The WebDriver instance (driver) must be initialized.
     * The LoginPage class must be set up and properly implemented.
     * </p>
     * @see LoginPage
     */
    @Test
    public void validLogin() {
        loginPage = new LoginPage(driver);
        driver.get("https://the-internet.herokuapp.com/login");

        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();
        LOGGER.info("Login successful.");
    }
}

