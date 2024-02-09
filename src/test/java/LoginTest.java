import Utils.Constants;
import Utils.TestResultLogger;
import Utils.WebDriverHandler;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.qameta.allure.SeverityLevel.CRITICAL;

/**
 * The LoginTest class contains test cases for the login functionality of the application.
 * <p>
 * This class includes test method to validate the login process.
 * It uses Selenium WebDriver for browser automation to simulate user interactions.
 * </p>
 */
@Severity(CRITICAL)
@Owner("Isidora Djokic")
@TestMethodOrder(MethodOrderer.MethodName.class)
@ExtendWith(TestResultLogger.class)
public class LoginTest extends WebDriverHandler {
    /**
     * Smoke test that tests the login functionality with valid credentials.
     * <p>
     * This method initializes the LoginPage, navigates to the login page,
     * enters valid username and password, clicks the login button
     * and asserts if the login is successful or not.
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
    @Order(1)
    @Tag("smoke")
    @DisplayName("Valid Login")
    @Description("This test attempts to log into the website using a valid username and a valid password. Fails if any error happens and success message does not appear.")
    public void t001_validLogin() {
        open(Constants.baseURL);
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        SecurePage securePage=loginPage.clickLoginButton();

        securePage.verifyUserLoggedIn();
    }

    /**
     * Negative test that tests the login functionality with invalid credentials.
     * <p>
     * This method initializes the LoginPage, navigates to the login page,
     * enters invalid username and password, clicks the login button,
     * and asserts if the login is unsuccessful or not.
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
    @Order(2)
    @Tag("negative")
    @DisplayName("Invalid Login")
    @Description("This test attempts to log into the website using an invalid username and an invalid password. Fails error massage does not appear.")
    public void t002_invalidLogin() {
        open(Constants.baseURL);
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("invalid_username");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        loginPage.verifyLoginFailed();
    }
}
