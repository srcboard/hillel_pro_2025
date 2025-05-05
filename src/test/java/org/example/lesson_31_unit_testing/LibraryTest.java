package org.example.lesson_31_unit_testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setup() {
        library = new Library();
        book1 = new Book("Book1", "Author1");
        book2 = new Book("Book2", "Author2");
    }

    @Test
    void testAddBook() {
        library.addBook(book1);
        assertEquals(1, library.getBookCount());
        assertTrue(library.getBooks().contains(book1));
    }

    @Test
    void testAddNullBookThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> library.addBook(null));
    }

    @Test
    void testRemoveBook() {
        library.addBook(book1);
        boolean removed = library.removeBook(book1);
        assertTrue(removed);
        assertEquals(0, library.getBookCount());
    }

    @Test
    void testRemoveBookThatDoesNotExist() {
        boolean removed = library.removeBook(book1);
        assertFalse(removed);
    }

    @Test
    void testRemoveNullBook() {
        library.addBook(book1);
        boolean removed = library.removeBook(null);
        assertFalse(removed);
        assertEquals(1, library.getBookCount());
    }

    @Test
    void testGetBooksReturnsUnmodifiableList() {
        library.addBook(book1);
        List<Book> books = library.getBooks();
        assertThrows(UnsupportedOperationException.class, () -> books.add(book2));
    }

    @Test
    void testGetBookCount() {
        assertEquals(0, library.getBookCount());
        library.addBook(book1);
        library.addBook(book2);
        assertEquals(2, library.getBookCount());
    }
}
