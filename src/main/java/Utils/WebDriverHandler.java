package Utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
     * This method initializes an instance of the Chrome WebDriver
     * It has @BeforeAll JUnit annotation which means that it will be executed before all the tests within the test class
     * -@BeforeAll methods are executed before any test methods in the test class, and they need to be called on the class itself,
     * not on an instance of the class. The static keyword indicates that the method belongs to the class itself, and it can be
     * invoked without creating an instance of the class.
     * <p>
     *     The method sets up the Chrome WebDriver with headless mode enabled along with additional
     *     options to configure the driver for optimal performance and compatibility.
     * </p>
     *
     * <p>
     *     The following ChromeOptions are applied:
     *     <ul>
     *         <li>{@code --headless}: Enables headless mode, running Chrome without a graphical interface.</li>
     *         <li>{@code --disable-gpu}: Disables the GPU hardware acceleration.</li>
     *         <li>{@code --disable-dev-shm-usage}: Disables the use of /dev/shm (shared memory) for temporary files.</li>
     *         <li>{@code --no-sandbox}: Disables the Chrome sandbox, which is necessary when running Chrome in a container environment.</li>
     *     </ul>
     * </p>
     *
     * <p>
     *     The method initializes the ChromeDriver instance with the configured options.
     *     Upon successful initialization, an INFO log message is generated indicating that the WebDriver is initialized.
     * </p>
     */
    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        // Initialize ChromeDriver
        driver = new ChromeDriver(options);
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

    /**
     * This method opens the specified URL in the browser.
     * <p>
     * This method navigates the WebDriver instance to the provided URL.
     * </p>
     * <p>
     * Example usage:
     * <pre>{@code
     * driver.open("https://example.com");
     * }</pre>
     * </p>
     * @param url The URL to be opened in the browser.
     */
    public void open(String url){
        driver.get(url);
    }
}

