package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Book class
class BookTest {
    private Book testBook1;
    private Book testBook2;

    @BeforeEach
    public void runBefore() {
        testBook1 = new Book("Crime and Punishment", "Fyodor", "Dostoyevsky",
                "unread");
        testBook2 = new Book("A Tale of Two Cities", "Charles", "Dickens",
                "read");
    }

    @Test
    public void testConstructorUnread() {
        assertEquals("Crime and Punishment", testBook1.getTitle());
        assertEquals("Fyodor", testBook1.getAuthorFirstName());
        assertEquals("Dostoyevsky", testBook1.getAuthorLastName());
        assertEquals("unread", testBook1.getStatus());
    }

    @Test
    public void testConstructorRead() {
        assertEquals("A Tale of Two Cities", testBook2.getTitle());
        assertEquals("Charles", testBook2.getAuthorFirstName());
        assertEquals("Dickens", testBook2.getAuthorLastName());
        assertEquals("read", testBook2.getStatus());
    }
}
