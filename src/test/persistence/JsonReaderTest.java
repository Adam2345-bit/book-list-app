package persistence;

import model.Book;
import model.BookList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Unit test for JsonReader
// CITATION: code obtained from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookList bl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testReaderEmptyBookList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookList.json");
        try {
            BookList bl = reader.read();
            assertEquals(0, bl.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testReaderTwoBooksInBookList() {
        JsonReader reader = new JsonReader("./data/testReaderTwoBooksInBookList.json");
        try {
            BookList bl = reader.read();
            List<Book> bookList = bl.getBookList();
            assertEquals(2, bookList.size());
            checkBook("Physics", "Brandon", "Brown","read", bookList.get(0));
            checkBook("Biophysics", "Dan", "Johnson","unread", bookList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
