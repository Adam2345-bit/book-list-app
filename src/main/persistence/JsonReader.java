package persistence;


import model.Book;
import model.BookList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads book list from JSON data stored in file
// CITATION: code obtained from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads book list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookList(jsonObject);
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: parses book list from JSON object and returns it
    private BookList parseBookList(JSONObject jsonObject) {
        BookList bl = new BookList();
        addBooks(bl, jsonObject);
        return bl;
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: bl
    // EFFECTS: parses books from JSON object and adds them to book list
    private void addBooks(BookList bl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(bl, nextBook);
        }
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: bl
    // EFFECTS: parses book from JSON object and adds it to book list
    private void addBook(BookList bl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String authorFirstName = jsonObject.getString("authorFirstName");
        String authorLastName = jsonObject.getString("authorLastName");
        String status = jsonObject.getString("status");
        Book book = new Book(title, authorFirstName, authorLastName,status);
        bl.addBook(book);
    }
}
