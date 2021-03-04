package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String title, String authorFirstName, String authorLastName, String status, Book testBook)
    {
        assertEquals(title, testBook.getTitle());
        assertEquals(authorFirstName, testBook.getAuthorFirstName());
        assertEquals(authorLastName, testBook.getAuthorLastName());
        assertEquals(status, testBook.getStatus());
    }
}
