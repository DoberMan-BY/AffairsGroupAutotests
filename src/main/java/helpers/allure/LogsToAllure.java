package helpers.allure;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.WriterAppender;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.StringWriter;

public class LogsToAllure {

    private static StringWriter logWriter;
    private static WriterAppender appender;

    public static void setupLogging() {
        if (logWriter == null) {
            // 1. Initialize StringWriter for capturing logs (only once)
            logWriter = new StringWriter();

            // 2. Define the log format (PatternLayout)
            PatternLayout layout = PatternLayout.newBuilder()
                    .withPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n")
                    .build();

            // 3. Create a filter for INFO level logs
            Filter infoFilter = ThresholdFilter.createFilter(Level.INFO, Filter.Result.ACCEPT, Filter.Result.DENY);

            // 4. Create the WriterAppender for capturing INFO logs
            appender = WriterAppender.newBuilder()
                    .setTarget(logWriter)
                    .setName("LogToAllureAppender")
                    .setLayout(layout)
                    .setFilter(infoFilter)
                    .build();
            appender.start();

            // 5. Attach the appender to the root logger
            LoggerContext context = (LoggerContext) LogManager.getContext(false);
            org.apache.logging.log4j.core.Logger coreLogger = context.getLogger(LogManager.ROOT_LOGGER_NAME);
            coreLogger.addAppender(appender);
            coreLogger.setAdditive(true);
        } else {
            // Clear the StringWriter buffer before each test
            logWriter.getBuffer().setLength(0);
        }
    }


    public static void attachLogsToAllure() {
        // 6. Attach logs to the Allure report
        String logs = logWriter.toString();
        if (!logs.isEmpty()) {
            Allure.addAttachment("INFO Logs", "text/plain", logs, ".txt");
        }

        // 7. Stop the appender to release resources
        appender.stop();

        // 8. Clear logs after each test
        logWriter.getBuffer().setLength(0);  // Clear the log buffer after attaching
    }
}

