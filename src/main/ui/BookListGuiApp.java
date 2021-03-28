package ui;

import java.io.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Book;
import model.BookList;
import persistence.JsonReader;
import persistence.JsonWriter;

//Represent GUI for BookListApp
public class BookListGuiApp extends JFrame implements ActionListener {
    private static final String SAVE_FILE_NAME = "./data/booklist.json";

    // Labels
    private JLabel lblTitle;
    private JLabel lblFname;
    private JLabel lblLname;
    private JLabel lblStatus;
    private JLabel lblDisplay;

    //TextFields
    private JTextField txtTitle;
    private JTextField txtFname;
    private JTextField txtLname;
    private JTextField txtStatus;
    private JTextArea txtDisplay;

    //Buttons
    private JButton addBook;
    private JButton saveList;
    private JButton loadList;
    private JButton displayList;
    private JRadioButton all;
    private JRadioButton read;
    private JRadioButton unread;

    private ButtonGroup group;

    private BookList bookList = new BookList();

    //EFFECTS: creates buttons, initial display area and frame attributes
    public BookListGuiApp() {
        super("BookList App");

        // Creating Buttons
        addLabels();
        addText();
        addBookButton();
        addRadioButton();
        addSaveBookListButton();
        addLoadBookListButton();
        addDisplayBookListButton();

        //Display Area
        txtDisplay = new JTextArea();
        txtDisplay.setBounds(600, 50, 300, 400);
        txtDisplay.setEditable(false);
        txtDisplay.setLineWrap(true);
        txtDisplay.setWrapStyleWord(true);
        add(txtDisplay);

        // Frame Functionality
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when we click the x button the JFrame will be closed
        setLayout(null);                                //sets frame layout
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(1000, 600);                 // set frame size
        setVisible(true);                               // display frame

    }

    //EFFECTS: creates a Display Booklist Button
    private void addDisplayBookListButton() {
        displayList = new JButton("Display Booklist");
        displayList.setBounds(350, 140, 150, 20);
        displayList.setActionCommand("DisplayBooklist");
        displayList.addActionListener(this);          //makes this button to react to the action command
        add(displayList);

    }

    //EFFECTS: creates a Load List From File Button
    private void addLoadBookListButton() {
        loadList = new JButton("Load List from File");
        loadList.setBounds(350, 240, 150, 20);
        loadList.setActionCommand("LoadBooklist");
        loadList.addActionListener(this);               //makes this button to react to the action command
        add(loadList);
    }

    //EFFECTS: creates Save List to File Button
    private void addSaveBookListButton() {
        saveList = new JButton("Save List to File");
        saveList.setBounds(350, 200, 150, 20);
        saveList.setActionCommand("SaveBooklist");
        saveList.addActionListener(this);                //makes this button to react to the action command
        add(saveList);
    }

    //EFFECTS: creates Display All Books, Display Read Books, and Display Unread Books Selection Buttons
    private void addRadioButton() {
        all = new JRadioButton("Display All Books");
        all.setBounds(350, 50, 160, 25);
        all.setSelected(true);
        add(all);

        read = new JRadioButton("Display Read Books");
        read.setBounds(350, 75, 160, 25);
        add(read);

        unread = new JRadioButton("Display Unread Books");
        unread.setBounds(350, 100, 160, 25);
        add(unread);

        // Add the radio buttons to a group
        // so that only one can be selected at a time
        group = new ButtonGroup();
        group.add(all);
        group.add(read);
        group.add(unread);
    }

    //EFFECTS: creates Add Book Button
    private void addBookButton() {
        addBook = new JButton("Add Book");
        addBook.setBounds(120, 260, 100, 20);
        addBook.setActionCommand("AddBook");
        addBook.addActionListener(this);//makes this button to react to the action command
        add(addBook);
    }

    //EFFECTS: creates Text Fields for title, first and last names, and status
    private void addText() {
        txtTitle = new JTextField();
        txtTitle.setBounds(140, 66, 200, 20);
        add(txtTitle);

        txtFname = new JTextField();
        txtFname.setBounds(140, 116, 100, 20);
        add(txtFname);

        txtLname = new JTextField();
        txtLname.setBounds(140, 166, 100, 20);
        add(txtLname);

        txtStatus = new JTextField();
        txtStatus.setBounds(140, 216, 100, 20);
        add(txtStatus);
    }

    //EFFECTS: creates Labels for title, first and last names, status, and display
    private void addLabels() {
        lblTitle = new JLabel("Title", SwingConstants.RIGHT);
        lblTitle.setBounds(25, 50, 100, 50);
        add(lblTitle);

        lblFname = new JLabel("Author First Name");
        lblFname.setBounds(25, 100, 125, 50);
        add(lblFname);

        lblLname = new JLabel("Author Last Name");
        lblLname.setBounds(25, 150, 125, 50);
        add(lblLname);

        lblStatus = new JLabel("Status", SwingConstants.RIGHT);
        lblStatus.setBounds(25, 200, 100, 50);
        add(lblStatus);

        lblDisplay = new JLabel("Display");
        lblDisplay.setBounds(600, 10, 50, 50);
        add(lblDisplay);
    }

    //MODIFES: this
    //EFFECTS: adds button functionality
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("AddBook")) {
            addNewBook();
        } else if (e.getActionCommand().equals("SaveBooklist")) {
            saveBookList();
        } else if (e.getActionCommand().equals("LoadBooklist")) {
            loadBookList();
        } else if (e.getActionCommand().equals("DisplayBooklist")) {
            displayBookList();
        }
    }

    //MODIFES: this
    //EFFECTS: adds book to a book list
    private void addNewBook() {
        String title = txtTitle.getText();
        String firstName = txtFname.getText();
        String lastName = txtLname.getText();
        String status = txtStatus.getText();

        Book newBook = new Book(title, firstName, lastName, status);
        bookList.addBook(newBook);

        clearBook();

    }

    //MODIFES: this
    //EFFECTS: clears old title, first and last names, and status
    private void clearBook() {
        txtTitle.setText(null);
        txtFname.setText(null);
        txtLname.setText(null);
        txtStatus.setText(null);
    }

    //MODIFIES: this
    //EFFECTS: displays a list of books
    private void displayBookList() {
        if (bookList.size() == 0) {
            txtDisplay.setText(null);
            txtDisplay.setText("Your book list is empty.");
        } else {
            txtDisplay.setText(null);
            for (Book b : bookList.getBookList()) {
                if (all.isSelected() == true) {
                    printBook(b);
                } else if (read.isSelected() == true) {
                    if (b.getStatus().toLowerCase().equals("read")) {
                        printBook(b);
                    }
                } else if (unread.isSelected() == true) {
                    if (b.getStatus().toLowerCase().equals("unread")) {
                        printBook(b);
                    }
                }

            }
        }
    }

    //MODIFIES: this
    //EFFECTS: prints book to display
    private void printBook(Book b) {
        String title = b.getTitle();
        String first = b.getAuthorFirstName();
        String last = b.getAuthorLastName();
        String status = b.getStatus();
        txtDisplay.append(title + " by " + first + " " + last + " - " + status + "\n");
    }


    //EFFECTS: saves book list to a file
    public void saveBookList() {
        JsonWriter myWriter = new JsonWriter(SAVE_FILE_NAME);
        try {
            myWriter.open();
            myWriter.write(bookList);
            myWriter.close();
            showImage("./data/mysample_v2.png");
            txtDisplay.append("Saved book list to " + SAVE_FILE_NAME);
        } catch (FileNotFoundException e) {
            txtDisplay.append("Unable to write book list data file: " + SAVE_FILE_NAME);
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
            txtDisplay.setText(null);
            txtDisplay.append("Loaded book list from " + SAVE_FILE_NAME);
        } catch (IOException e) {
            txtDisplay.setText(null);
            txtDisplay.append("Unable to read book list data file: " + SAVE_FILE_NAME);
        }
    }

    //EFFECTS: displays image when saving a book list
    private void showImage(String imageName) {
        ImageIcon icon = new ImageIcon(imageName);
        JOptionPane.showMessageDialog(this, new JLabel("Your list has been saved", icon, JLabel.LEFT),
                "Save List to File", JOptionPane.INFORMATION_MESSAGE);
    }


}