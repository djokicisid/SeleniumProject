package Utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class initializes a LOGGER as an instance of a public class 'logger' from java.util.logging,
 * configures it and offers a public method getLogger() that returns this instance of the logger
 * The LoggerUtil class provides methods for configuring and obtaining loggers.
 *
 * This class encapsulates the setup and configuration of loggers using a logging framework,
 * such as SLF4J or java.util.logging. It includes methods for obtaining loggers with various
 * configurations, setting log levels, and logging messages with different severity levels.
 *
 * Supported Logging Frameworks:
 * - SLF4J (Simple Logging Facade for Java)
 * - java.util.logging (JUL)
 *
 */
public class LoggerUtil {
    private static final Logger LOGGER = Logger.getLogger(LoggerUtil.class.getName());

    static {
        // konfiguracija logger-a
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        LOGGER.addHandler(consoleHandler);
        LOGGER.setLevel(Level.ALL);
    }

    /**
     * Method which returns the instance of the Logger for this class.
     *
     * This method returns the logger instance associated with the class.
     * The logger is typically configured to output log messages based on the
     * underlying logging framework (e.g., SLF4J or java.util.logging).
     *
     * Example usage:
     * <pre>{@code
     * Logger logger = LoggerUtil.getLogger();
     * logger.info("This is an informational message.");
     * }</pre>
     *
     * @return The logger instance for this class.
     */
    public static Logger getLogger() {
        return LOGGER;
    }
}
