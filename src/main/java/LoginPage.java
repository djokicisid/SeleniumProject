import Utils.Base;
import io.qameta.allure.Step;
import Utils.Locator;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class represents a page object for handling login functionality. It is written using the Page Object design pattern.
 * This class exposes methods that represent actions that can be performed on the login web page.
 * Elements on the page (e.g., buttons, text fields) are encapsulated within the page class.
 *
 * This class encapsulates the interactions and elements related to the login page,
 * providing methods to enter credentials, click the login button, and perform login-related actions.
 * It is designed to be used in conjunction with Selenium WebDriver for browser automation.
 *
 * Example usage:
 * <pre>{@code
 * LoginPage loginPage = new LoginPage(driver);
 * loginPage.enterUsername("exampleUser");
 * loginPage.enterPassword("securePassword");
 * loginPage.clickLoginButton();
 * }</pre>
 *
 * Note: This class assumes that the underlying WebDriver instance (driver) is properly initialized.
 *
 * @see <a href="https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/">Page Object models documentation</a>
 */
public class LoginPage extends Base {
    private WebDriver driver;

    /**
     * This method is the constructor for the LoginPage class.
     * Constructs a new LoginPage object with the specified WebDriver instance.
     * <p>
     * This constructor initializes the LoginPage with the provided WebDriver instance.
     * </p>
     * @param driver The WebDriver instance used for browser automation.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method enters the username data into web element for usernameField
     * <p>
     * This method simulates entering the provided username into the username input field.
     * </p>
     * @param username The username to be entered.
     * @return The LoginPage object
     */
    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        type(username, Locator.usernameField);
        return this;
    }

    /**
     * This method enters password data into web element for passwordField
     * <p>
     * This method simulates entering the provided password into the password input field.
     * </p>
     * @param password The password to be entered.
     * @return The LoginPage object
     */
    @Step("Enter password:")
    public LoginPage enterPassword(String password) {
        type(password, Locator.passwordField);
        return this;
    }

    /**
     * This method is used to simulate a user clicking on a Login button element on a web page.
     * <p>
     * This method simulates clicking the login button to submit the login form.
     * </p>
     * @return A new SecurePage object after clicking the login button.
     */
    @Step("Click login button")
    public SecurePage clickLoginButton() {
        click(Locator.loginButton);
        return new SecurePage(driver);
    }

    /**
     * This method is used to retrieve the text content of the error message web element.
     * @return Error Message text
     */
    public String getErrorMessage() {
        return getText(Locator.errorMessage);
    }

    /**
     * Verifies that the login failed by checking if the error message contains either "Your username is invalid!" or "Your password is invalid!".
     * <p>
     * This method asserts that the error message contains either of the specified texts.
     * If the assertion fails, it indicates that the login didn't fail.
     * </p>
     */
    public void verifyLoginFailed() {
        assertTrue(getErrorMessage().contains("Your username is invalid!") || getErrorMessage().contains("Your password is invalid!"), "Invalid login.");
    }
}
