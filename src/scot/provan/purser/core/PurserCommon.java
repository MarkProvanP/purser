package scot.provan.purser.core;

/**
 * Created by Mark on 11/06/2015.
 */
public class PurserCommon {
    public enum LogLevel {
        OFF, FATAL, ERROR, WARN, INFO, DEBUG, TRACE
    }
    public static void log(LogLevel logLevel, String message) {
        String logFormat = "%s: %s";
        switch (logLevel) {
            case OFF:
                break;
            case FATAL:
            case ERROR:
                System.err.printf(logFormat, logLevel.toString(), message);
                break;
            case WARN:
            case INFO:
                System.out.printf(logFormat, logLevel.toString(), message);
                break;
            case DEBUG:
            case TRACE:
        }
    }
}
