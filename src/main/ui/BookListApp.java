package ui;

import model.Book;
import model.BookList;
import model.exceptions.InvalidStatusException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

// Represents BookList application
public class BookListApp {
    private static final String SAVE_FILE_NAME = "./data/booklist.json";
    private Scanner userInput = new Scanner(System.in);
    private BookList bookList = new BookList();


    // EFFECTS: runs the BookList application
    public BookListApp() {
        runBookList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBookList() {
        boolean startApp = true;
        String command;
        while (startApp) {
            System.out.println();
            displayBookList();
            System.out.println();
            displayMenu();
            command = userInput.nextLine();
            processCommand(command);
            if (command.equals("q")) {
                startApp = false;
                System.out.println("BookList App was terminated");
            }
        }
    }

    // EFFECT: displays a list of books
    private void displayBookList() {
        if (bookList.size() == 0) {
            System.out.println("Your book list is empty.");
        } else {
            for (Book b : bookList.getBookList()) {
                System.out.println(b.getTitle() + " by " + b.getAuthorFirstName() + " " + b.getAuthorLastName()
                        + " - " + b.getStatus());
            }
        }
    }

    // EFFECTS: displays menu of options
    public void displayMenu() {
        System.out.println("a - add a book");
        System.out.println("d - delete a book");
        System.out.println("c - change status of a book");
        System.out.println("s - save the list of books");
        System.out.println("l - load the previous list of books");
        System.out.println("q - quit");
        System.out.print("Enter your choice: ");
    }

    // MODIFIES: this
    // EFFECTS: prompts a user to create a new book and adds it to a book list
    public void addNewBook() {
        System.out.print("Enter title: ");
        String title = userInput.nextLine();
        System.out.print("Enter author's first name: ");
        String firstName = userInput.nextLine();
        System.out.print("Enter author's last name: ");
        String lastName = userInput.nextLine();
        System.out.print("Enter status (read/unread): ");
        String status = userInput.nextLine();

        Book newBook = new Book(title, firstName, lastName, status);
        bookList.addBook(newBook);

    }

    // MODIFIES: this
    // EFFECTS: deletes a book from a book list based on its title
    public void deleteUserBook() {
        System.out.print("Enter title: ");
        String title = userInput.nextLine();
        bookList.deleteBook(title);
    }

    // MODIFIES: this
    // EFFECTS: changes the status of the book
    public void changeStatusUserBook() {
        System.out.print("Enter title: ");
        String title = userInput.nextLine();
        try {
            bookList.changeStatus(title);
        } catch (InvalidStatusException e) {
            System.out.println("The status of your book was invalid, so it was changed to a default unread status.");
        }
    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves book list to file
    public void saveBookList() {
        JsonWriter myWriter = new JsonWriter(SAVE_FILE_NAME);
        try {
            myWriter.open();
            myWriter.write(bookList);
            myWriter.close();
            System.out.println("Saved book list to " + SAVE_FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write book list data file: " + SAVE_FILE_NAME);
        }

    }

    // CITATION: code obtained from JsonSerializationDemo
    //           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads book list from file
    public void loadBookList() {
        JsonReader myReader = new JsonReader(SAVE_FILE_NAME);
        try {
            bookList = myReader.read();
            System.out.println("Loaded book list from " + SAVE_FILE_NAME);
        } catch (IOException e) {
            System.out.println("Unable to read book list data file: " + SAVE_FILE_NAME);
        }

    }

    // MODIFIES: this
    // EFFECTS: process user command
    public void processCommand(String command) {
        switch (command) {
            case "a":
                addNewBook();
                break;
            case "d":
                deleteUserBook();
                break;
            case "c":
                changeStatusUserBook();
                break;
            case "s":
                saveBookList();
                break;
            case "l":
                loadBookList();
                break;
        }
    }

}

