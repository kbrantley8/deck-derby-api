package deckderby.utils.logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class LoggerContext {
    private static LoggerContext instance;
    private final LambdaLogger lambdaLogger;
    private final ApplicationLogger ApplicationLogger;

    private LoggerContext(Context context) {
        lambdaLogger = context.getLogger();
        ApplicationLogger = new ApplicationLogger(lambdaLogger);
    }

    public static void initialize(Context context) {
        instance = new LoggerContext(context);
    }

    public static LoggerContext getInstance() {
        if (instance == null) {
            throw new IllegalStateException("LoggerContext has not been initialized.");
        }
        return instance;
    }

    public LambdaLogger getLambdaLogger() {
        return lambdaLogger;
    }

    public ApplicationLogger getLogger() {
        return ApplicationLogger;
    }
}

