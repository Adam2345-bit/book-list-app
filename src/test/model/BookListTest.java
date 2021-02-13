package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for BookList class
class BookListTest {
    private BookList testBookList;
    private Book testBook1;
    private Book testBook2;
    private Book testBook3;

    @BeforeEach
    public void runBefore() {
        testBookList = new BookList();
        testBook1 = new Book("A Tale of Two Cities", "Charles", "Dickens",
                "read");
        testBook2 = new Book("Crime and Punishment", "Fyodor", "Dostoyevsky",
                "unread");
        testBook3 = new Book("The Lord of the Rings", "John", "Tolkien",
                "read");
    }

    @Test
    // empty book list
    public void testConstructor() {
        assertEquals(testBookList, testBookList);
    }

    @Test
    // add one book that is not in a book list
    public void testAddOneBook() {
        assertFalse(testBookList.contains(testBook1));
        testBookList.addBook(testBook1);
        assertTrue(testBookList.contains(testBook1));
        assertEquals(1, testBookList.size());
    }

    @Test
    // add several books that are not in a book list
    public void testAddSeveralBooks() {
        testBookList.addBook(testBook1);
        testBookList.addBook(testBook2);
        testBookList.addBook(testBook3);
        assertTrue(testBookList.contains(testBook1));
        assertTrue(testBookList.contains(testBook2));
        assertTrue(testBookList.contains(testBook3));
        assertEquals(3, testBookList.size());
    }

    @Test
    // add a book that is already in the list
    public void testAddExistingBook() {
        assertEquals(0, testBookList.size());
        testBookList.addBook(testBook1);
        testBookList.addBook(testBook1);
        assertEquals(1, testBookList.size());
        assertTrue(testBookList.contains(testBook1));
    }

    @Test
    // delete a book that is already in the list
    public void testDeleteBook() {
        testBookList.addBook(testBook1);
        testBookList.addBook(testBook2);
        testBookList.addBook(testBook3);
        assertEquals(3, testBookList.size());
        testBookList.deleteBook("A Tale of Two Cities");
        assertEquals(2, testBookList.size());
        assertFalse(testBookList.contains(testBook1));
    }

    @Test
    public void testChangeStatus() {
        testBookList.addBook(testBook1);
        testBookList.changeStatus("A Tale of Two Cities");
        assertEquals("unread", testBook1.getStatus());
        testBookList.addBook(testBook2);
        testBookList.changeStatus("Crime and Punishment");
        assertEquals("read", testBook2.getStatus());
    }
}



