package org.example.lesson10_2;

import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try {
            String configFilePath = Objects.requireNonNull(
                    Main.class.getClassLoader().getResource("config.properties")).getPath();
            FileLoggerConfiguration config = FileLoggerConfigurationLoader.load(configFilePath);

            FileLogger logger = new FileLogger(config);

            logger.info("This is an informational message");
            logger.debug("This is a debug message");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
