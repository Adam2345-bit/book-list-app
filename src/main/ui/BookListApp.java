package ui;

import model.Book;
import model.BookList;

import java.util.Scanner;

// Represents BookList application
public class BookListApp {
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
            switch (command) {
                case "1":
                    addNewBook();
                    break;
                case "2":
                    deleteUserBook();
                    break;
                case "3":
                    changeStatusUserBook();
                    break;
                case "q":
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
        System.out.println("1 - add a book");
        System.out.println("2 - delete a book");
        System.out.println("3 - change status of a book");
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
        bookList.changeStatus(title);
    }

}

