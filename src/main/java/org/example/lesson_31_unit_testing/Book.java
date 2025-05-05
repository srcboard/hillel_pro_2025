package org.example.lesson_31_unit_testing;

public record Book(String title, String author) {
    public Book {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or blank");
        }
    }
}
