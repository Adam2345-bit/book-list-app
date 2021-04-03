package model;

import model.exceptions.InvalidStatusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for BookList class
class BookListTest {
    private BookList testBookList;
    private Book testBook1;
    private Book testBook2;
    private Book testBook3;
    private Book testBook4;

    @BeforeEach
    public void runBefore() {
        testBookList = new BookList();
        testBook1 = new Book("A Tale of Two Cities", "Charles", "Dickens",
                "read");
        testBook2 = new Book("Crime and Punishment", "Fyodor", "Dostoyevsky",
                "unread");
        testBook3 = new Book("The Lord of the Rings", "John", "Tolkien",
                "read");
        testBook4 = new Book("Dummy Book", "Bob", "Jonson",
                "something");
    }

    private void addThreeBooks() {
        testBookList.addBook(testBook1);
        testBookList.addBook(testBook2);
        testBookList.addBook(testBook3);
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
        addThreeBooks();
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
        addThreeBooks();
        assertEquals(3, testBookList.size());
        testBookList.deleteBook("A Tale of Two Cities");
        assertEquals(2, testBookList.size());
        assertFalse(testBookList.contains(testBook1));
    }

    @Test
    // delete a book that is not in the list
    public void testDeleteBookNotInList() {
        testBookList.addBook(testBook1);
        testBookList.addBook(testBook2);
        testBookList.deleteBook("The Lord of the Rings");
        assertEquals(2, testBookList.size());
        assertTrue(testBookList.contains(testBook1));
        assertTrue(testBookList.contains(testBook2));


    }

    @Test
    public void testChangeStatusException() {
        try {
            testBookList.addBook(testBook4);
            testBookList.changeStatus("Dummy Book");
            fail("Error");
        } catch (InvalidStatusException e) {
            //pass
        }
    }

    @Test
    public void testChangeStatusNoException() {

        try {
            testBookList.addBook(testBook1);
            testBookList.changeStatus("A Tale of Two Cities");
            assertEquals("unread", testBook1.getStatus());
        } catch (InvalidStatusException e) {
            fail("Error");
        }

        try {
            testBookList.addBook(testBook2);
            testBookList.changeStatus("Crime and Punishment");
            assertEquals("read", testBook2.getStatus());
        } catch (InvalidStatusException e) {
            fail("Error");
        }

    }

    @Test
    public void testSize() {
        assertEquals(0,testBookList.size());
        addThreeBooks();
        assertEquals(3,testBookList.size());
    }

    @Test
    public void testContains() {
        addThreeBooks();
        assertTrue(testBookList.contains(testBook1));
        assertTrue(testBookList.contains(testBook2));
        assertTrue(testBookList.contains(testBook3));
    }

    @Test
    public void testGetBookList() {
       List<Book> listOfBooks =  testBookList.getBookList();
       assertEquals(listOfBooks,listOfBooks);
    }
}



