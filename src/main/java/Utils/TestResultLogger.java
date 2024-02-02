package Utils;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The TestResultLogger class is a JUnit 5 extension that implements the TestWatcher and AfterAllCallback
 * interfaces to provide callbacks for test execution events. It logs information about test status and
 * provides a summary after all tests in a test container have been executed.
 */
public class TestResultLogger implements TestWatcher, AfterAllCallback {

    /**
     * The logger instance for this class.
     */
    protected static final Logger LOGGER = LoggerUtil.getLogger();

    /**
     * List to store the status of each test.
     */
    private List<TestResultStatus> testResultsStatus = new ArrayList<>();

    /**
     * Enum representing the possible test result statuses.
     */
    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED;
    }


    /**
     * Called when a test is disabled.
     *
     * @param context The extension context for the disabled test.
     * @param reason  An optional reason why the test is disabled.
     */
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        LOGGER.info("Test Disabled for test: " + context.getDisplayName() + " with reason: " + reason.orElse("No reason provided"));
        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    /**
     * Called when a test is successful.
     *
     * @param context The extension context for the successful test.
     */
    @Override
    public void testSuccessful(ExtensionContext context) {
        LOGGER.info("Test passed: " + context.getDisplayName());
        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    /**
     * Called when a test is aborted.
     *
     * @param context The extension context for the aborted test.
     * @param cause   The throwable cause of the test abortion.
     */
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LOGGER.warning("Test aborted for test: " + context.getDisplayName() + " with cause: " + cause.getMessage());
        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    /**
     * Called when a test fails.
     *
     * @param context The extension context for the failed test.
     * @param cause   The throwable cause of the test failure.
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOGGER.severe("Test failed for test: " + context.getDisplayName() + " with cause: " + cause.getMessage());
        testResultsStatus.add(TestResultStatus.FAILED);
    }

    /**
     * Callback method that is invoked after all tests in a test container have been executed.
     * It provides a summary of the test results, including counts for each test result status.
     *
     * @param context The extension context for the executed tests.
     * @throws Exception If an exception occurs during the execution of this method.
     */
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        LOGGER.info("Test result summary for: "+context.getDisplayName()+summary.toString());
    }
}
