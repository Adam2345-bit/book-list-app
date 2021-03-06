package persistence;

import model.Book;
import model.BookList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Unit test for JsonWriter
// CITATION: code obtained from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testWriterInvalidFile() {
        try {
            BookList bl = new BookList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testWriterEmptyBookList() {
        try {
            BookList bl = new BookList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookList.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookList.json");
            bl = reader.read();
            assertEquals(0, bl.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Test
    void testWriterTwoBooksInBookList() {
        try {
            BookList bl = new BookList();
            bl.addBook(new Book("Physics", "Brandon", "Brown","read"));
            bl.addBook(new Book("Biophysics", "Dan", "Johnson","unread"));
            JsonWriter writer = new JsonWriter("./data/testWriterTwoBooksInBookList.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTwoBooksInBookList.json");
            bl = reader.read();
            List<Book> bookList = bl.getBookList();
            assertEquals(2, bookList.size());
            checkBook("Physics", "Brandon", "Brown","read", bookList.get(0));
            checkBook("Biophysics", "Dan", "Johnson","unread", bookList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}