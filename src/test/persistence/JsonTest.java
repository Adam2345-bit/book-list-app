package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Helper unit test for JsonReader and JsonWriter
// CITATION: code obtained from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //EFFECTS: checks given title, author's full name, status against that of the testBook
    protected void checkBook(String title, String authorFirstName, String authorLastName, String status, Book testBook)
    {
        assertEquals(title, testBook.getTitle());
        assertEquals(authorFirstName, testBook.getAuthorFirstName());
        assertEquals(authorLastName, testBook.getAuthorLastName());
        assertEquals(status, testBook.getStatus());
    }
}
