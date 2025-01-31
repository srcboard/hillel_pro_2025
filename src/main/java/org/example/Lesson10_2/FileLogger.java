package org.example.Lesson10_2;

import java.io.*;
import java.text.*;
import java.util.*;

public class FileLogger {

    private final FileLoggerConfiguration config;
    private File logFile;

    public FileLogger(FileLoggerConfiguration config) {
        this.config = config;

        File directory = new File(System.getProperty("user.dir") + "/" + config.filePath());
        File[] logFiles = directory.listFiles(File::isFile);
        if (logFiles == null || logFiles.length == 0) {
            logFile = createNewLogFile();
        } else {
            Arrays.sort(logFiles, Comparator.comparingLong(File::lastModified).reversed());
            logFile = logFiles[0];
        }
    }

    public void info(String message) throws IOException {
        writeLog(message, LoggingLevel.INFO);
    }

    public void debug(String message) throws IOException {
        writeLog(message, LoggingLevel.DEBUG);
    }

    private void writeLog(String message, LoggingLevel level) throws IOException {
        if (canLog(level)) {
            try {
                if (logFile.length() >= config.maxSize()) {
                    throw new FileMaxSizeReachedException(
                            "Max file size reached. Max size: " + config.maxSize() + " bytes, " +
                            "Current size: " + logFile.length() + " bytes, " +
                            "File path: " + logFile.getAbsolutePath());
                }
            } catch (FileMaxSizeReachedException e){
                System.out.println(e.getMessage());
                logFile = createNewLogFile();
            }

            String formattedMessage = formatMessage(message, level);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                writer.write(formattedMessage);
                writer.newLine();
            }
        }
    }

    private boolean canLog(LoggingLevel messageLevel) {
        LoggingLevel currentLevel = config.level();
        return currentLevel == LoggingLevel.DEBUG
                || (currentLevel == LoggingLevel.INFO && messageLevel == LoggingLevel.INFO);
    }

    private String formatMessage(String message, LoggingLevel level) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
        String currentTime = sdf.format(new Date());
        return config.format()
                .replace("[DATE]", currentTime)
                .replace("[LEVEL]", level.toString())
                .replace("[MESSAGE]", message);
    }

    private File createNewLogFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy-HH_mm_ss");
        String timestamp = sdf.format(new Date());
        String newFileName = "Log_" + timestamp + ".txt";
        File directory = new File(System.getProperty("user.dir") + "/" + config.filePath());
        return new File(directory, newFileName);
    }
}
