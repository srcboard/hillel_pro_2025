package org.example.lesson10_2;

public record FileLoggerConfiguration(String filePath, LoggingLevel level, long maxSize, String format) {
}
