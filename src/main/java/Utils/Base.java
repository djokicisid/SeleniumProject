package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static Utils.WebDriverHandler.driver;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Base class provides basic methods for interacting with web elements using Selenium WebDriver.
 * It includes methods for clicking buttons, typing text into input fields, and retrieving text from elements.
 */
public class Base {

    /**
     * Clicks on a web element identified by the provided locator.
     *
     * @param button The locator (By object) of the button to be clicked.
     */
    public void click(By button){
        driver.findElement(button).click();
    }

    /**
     * Enters text into an input field identified by the provided locator.
     *
     * @param value    The text value to be entered into the input field.
     * @param fieldName The locator (By object) of the input field.
     */
    public void type(String value, By fieldName){
        driver.findElement(fieldName).sendKeys(value);
    }

    /**
     * Retrieves the text content of a web element identified by the provided locator.
     *
     * @param textField The locator (By object) of the text field or element.
     * @return The text content of the specified element.
     */
    public String getText(By textField){
        return driver.findElement(textField).getText();
    }

    /**
     * This method is used for verification purposes.
     * It checks if the specified element is present on the page.
     *
     * @param locator the By locator of the element to verify
     * @return true if the element is present, false otherwise
     */
    public boolean verifyDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed(); // Check if the element is displayed
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false; // Element not found, verification failed
        }
    }
}
