package Utils;

import org.openqa.selenium.By;

/**
 * The Locator class contains static references to locators used in web elements.
 * These locators are commonly used in page objects to interact with specific elements on web pages.
 * By encapsulating the locators in a separate class, it provides a centralized location
 * for managing and updating them, which improves code maintainability.
 */
public class Locator {
    public static By usernameField = By.id("username");
    public static By passwordField = By.id("password");
    public static By loginButton = By.cssSelector("button[type='submit']");
    public static By errorMessage = By.cssSelector(".flash.error");
    public static final By securePageMessage = By.cssSelector(".flash.success");
}
