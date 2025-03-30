package org.example.lesson24designpattern;

public class Main {
    public static void main(String[] args) {
        SingletonLogger logger1 = SingletonLogger.getInstance();
        SingletonLogger logger2 = SingletonLogger.getInstance();

        logger1.logInfo("First message");
        logger2.logInfo("Second message");

        System.out.println("Are the instances the same? " + (logger1 == logger2));
    }
}
