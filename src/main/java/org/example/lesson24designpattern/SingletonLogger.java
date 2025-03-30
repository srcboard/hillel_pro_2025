package org.example.lesson24designpattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SingletonLogger {
    private static volatile SingletonLogger instance;
    private final Logger logger;

    private SingletonLogger() {
        logger = LoggerFactory.getLogger(SingletonLogger.class);
    }

    public static SingletonLogger getInstance() {
        if (instance == null) {
            synchronized (SingletonLogger.class) {
                if (instance == null) {
                    instance = new SingletonLogger();
                }
            }
        }
        return instance;
    }

    public void logInfo(String message) {
        logger.info(message);
    }
}
