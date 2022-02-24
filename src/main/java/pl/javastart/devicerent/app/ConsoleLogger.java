package pl.javastart.devicerent.app;

import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ConsoleLogger {
    private static final Logger logger = Logger.getLogger(ConsoleLogger.class.getName());

    void logInfo(String text){
        logger.log(Level.INFO, text);
    }
}
