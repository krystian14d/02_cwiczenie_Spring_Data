package pl.javastart.devicerent.app;

import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ConsoleLogger {
    private static final Logger logger = Logger.getLogger(ConsoleLogger.class.getName());

    public void logInfo(String text) {
        logger.log(Level.INFO, text);
    }

    public void logErr(String text) {
        logger.log(Level.WARNING, text);
    }
}
