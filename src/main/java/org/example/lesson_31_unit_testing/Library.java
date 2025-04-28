package org.example.lesson_31_unit_testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        books.add(book);
    }

    public boolean removeBook(Book book) {
        if (book == null) {
            return false;
        }
        return books.remove(book);
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public int getBookCount() {
        return books.size();
    }
}
