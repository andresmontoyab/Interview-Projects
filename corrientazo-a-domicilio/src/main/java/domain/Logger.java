package domain;

public class Logger {

    private static java.util.logging.Logger instance = java.util.logging.Logger.getLogger(Logger.class.getName());

    public static java.util.logging.Logger getLogger() {
        return instance;
    }
}
