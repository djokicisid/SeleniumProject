import Utils.LoggerUtil;
import Utils.TestResultLogger;
import Utils.WebDriverHandler;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * The LoginTest class contains test cases for the login functionality of the application.
 * <p>
 * This class includes test method to validate the login process.
 * It uses Selenium WebDriver for browser automation to simulate user interactions.
 * </p>
 */
@ExtendWith(TestResultLogger.class)
public class LoginTest extends WebDriverHandler {
    private LoginPage loginPage;

    /**
     * Smoke test that tests the login functionality with valid credentials.
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
     * <p>
     * Tags:
     * -@smoke: Indicates that this test is part of the smoke test suite.
     * </p>
     * @see LoginPage
     */
    @Test
    @Tag("smoke")
    public void validLogin() {
        loginPage = new LoginPage(driver);
        driver.get("https://the-internet.herokuapp.com/login");

        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        // Assert that login is successful based on the success message
        assertNotNull(loginPage.getSecurePageMessage());

        LOGGER.info("Login successful.");
    }

    /**
     * Negative test that tests the login functionality with invalid credentials.
     * <p>
     * This method initializes the LoginPage, navigates to the login page,
     * enters invalid username and password, clicks the login button,
     * and logs information if the login is unsuccessful.
     * </p>
     * <p>
     * Preconditions:
     * The WebDriver instance (driver) must be initialized.
     * The LoginPage class must be set up and properly implemented.
     * </p>
     * <p>
     * Tags:
     * - @negative: Indicates that this test is part of the negative test scenarios.
     * </p>
     * @see LoginPage
     */
    @Test
    @Tag("negative")
    public void invalidLogin() {
        loginPage = new LoginPage(driver);
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage.enterUsername("invalid_username");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        // Assert that login is not successful based on the error message
        assertNotNull(loginPage.getErrorMessage());

        LOGGER.info(loginPage.getErrorMessage());
        LOGGER.info("Invalid login attempt.");
    }
}

