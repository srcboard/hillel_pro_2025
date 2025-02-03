package org.example.lesson10_2;

import java.io.*;
import java.util.*;

public class FileLoggerConfigurationLoader {

    public static FileLoggerConfiguration load(String configFilePath) throws IOException{
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String filePath = properties.getProperty("FILE");
        LoggingLevel level = LoggingLevel.valueOf(properties.getProperty("LEVEL"));
        long maxSize = Long.parseLong(properties.getProperty("MAX-SIZE"));
        String format = properties.getProperty("FORMAT");

        return new FileLoggerConfiguration(filePath, level, maxSize, format);
    }
}
