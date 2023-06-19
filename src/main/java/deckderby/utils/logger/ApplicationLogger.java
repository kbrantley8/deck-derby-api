package deckderby.utils.logger;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class ApplicationLogger {
    private final LambdaLogger lambdaLogger;
    private final String msgFormat = "[%s] => %s";

    public ApplicationLogger(LambdaLogger lambdaLogger) {
        this.lambdaLogger = lambdaLogger;
    }

    public void info(String message) {
        lambdaLogger.log(String.format(msgFormat, "INFO", message));
    }

    public void debug(String message) {
        lambdaLogger.log(String.format(msgFormat, "DEBUG", message));
    }

    public void error(String message) {
        lambdaLogger.log(String.format(msgFormat, "ERROR", message));
    }
}