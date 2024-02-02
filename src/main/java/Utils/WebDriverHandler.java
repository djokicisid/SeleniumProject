package Utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Logger;

/**
 * This class creates an instance of a web driver and configures it.
 * The WebDriverHandler class provides utility methods for managing WebDriver instances.
 * <p>
 * This class encapsulates the setup and teardown of WebDriver instances,
 * allowing for easier management and configuration of browser automation.
 * </p>
 */
public abstract class WebDriverHandler {

    protected static WebDriver driver;
    protected static final Logger LOGGER = LoggerUtil.getLogger();

    /**
     * This method creates an instance of a Chrome web driver
     * It has @BeforeAll JUnit annotation which means that it will be executed before all the tests within the test class
     * -@BeforeAll methods are executed before any test methods in the test class, and they need to be called on the class itself,
     * not on an instance of the class. The static keyword indicates that the method belongs to the class itself, and it can be
     * invoked without creating an instance of the class.
     */
    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        LOGGER.info("WebDriver initialized.");
    }

    /**
     * This method tears down the instance of a Chrome web driver
     * It has @AfterAll JUnit annotation which means that it will be executed after all the tests within the test class have finished
     * In JUnit, methods annotated with @AfterAll are used for cleanup tasks that should be executed once after all test methods in
     * a test class have run. Since these methods are called on the class itself and not on an instance of the class,
     * they must be declared as static.
     */
    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            LOGGER.info("WebDriver quit.");
        }
    }
}

