import Utils.LoggerUtil;
import Utils.TestResultLogger;
import Utils.WebDriverHandler;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * The LoginTest class contains test cases for the login functionality of the application.
 * <p>
 * This class includes test method to validate the login process.
 * It uses Selenium WebDriver for browser automation to simulate user interactions.
 * </p>
 */
@Severity(CRITICAL)
@Owner("Isidora Djokic")
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
    @DisplayName("Valid Login")
    @Description("This test attempts to log into the website using a valid username and a valid password. Fails if any error happens and success message does not appear.")
    public void validLogin() {
        loginPage = new LoginPage(driver);
        SecurePage securePage;

        driver.get("https://the-internet.herokuapp.com/login");

        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        securePage=loginPage.clickLoginButton();

        // Assert that login is successful based on the success message
        assertNotNull(securePage.getSecurePageMessage());

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
    @DisplayName("Invalid Login")
    @Description("This test attempts to log into the website using an invalid username and an invalid password. Fails error massage does not appear.")
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

