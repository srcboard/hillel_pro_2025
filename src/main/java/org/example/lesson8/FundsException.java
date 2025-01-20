package org.example.lesson8;

// Кастомний виняток
public class FundsException extends Exception{

    public FundsException(String message) {
        super(message);
    }
}
