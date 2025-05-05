package org.example.lesson_32_logging_debugging;

public class Main {
    public static void main(String[] args) {
        CoffeeOrderBoard board = new CoffeeOrderBoard();
        board.add("Alen");
        board.add("Yoda");
        board.add("Obi-van");
        board.add("John Snow");

        board.draw();
        board.deliver();
        board.deliver(5);
        board.draw();
    }
}
