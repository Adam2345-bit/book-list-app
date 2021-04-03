package model;

import model.exceptions.InvalidStatusException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents a list of Books
public class BookList {
    private List<Book> bookList;

    // EFFECTS: creates an empty list of books
    public BookList() {
        bookList = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: adds a book to a book list, unless it is already there, in which case does nothing
    public void addBook(Book b) {
        for (Book a : bookList) {
            if (a.getTitle().equals(b.getTitle())) {
                return;
            }
        }
        bookList.add(b);
    }

    //MODIFIES: this
    //EFFECTS: deletes a book from a book list, if it is already there. If not, does nothing
    public void deleteBook(String title) {
        int index = -1;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equals(title)) {
                index = i;
            }
        }
        if (index != -1) {
            bookList.remove(index);
        }
    }

    // MODIFIES: Book
    // EFFECTS: changes the status of the book from read to unread and back
    public void changeStatus(String title) throws InvalidStatusException {
        for (Book a : bookList) {
            if (a.getTitle().equals(title)) {
                if (a.getStatus().equals("read")) {
                    a.setStatus("unread");
                } else if (a.getStatus().equals("unread")) {
                    a.setStatus("read");
                } else {
                    a.setStatus("unread");
                    throw new InvalidStatusException();
                }
            }
        }
    }

    public List<Book> getBookList() {
        return bookList;
    }

    // EFFECTS: returns the number of books in a list
    public int size() {
        return bookList.size();
    }

    // EFFECTS: returns true if a book is in list, and false otherwise
    public boolean contains(Book b) {
        return bookList.contains(b);
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns books in a book list as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("books", booksToJson());
        return json;
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns books in a book list as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : bookList) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }
}

