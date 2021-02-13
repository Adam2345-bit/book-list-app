package model;

//Represents a book that has a title, an author (full name), and status (read/unread)
public class Book {
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String status;

    // EFFECT: creates a book with a title, author's full name, and status
    public Book(String title, String authorFirstName, String authorLastName, String status) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}



