package ui;

import java.io.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import model.Book;
import model.BookList;
import persistence.JsonReader;
import persistence.JsonWriter;


public class BookListGuiApp extends JFrame implements ActionListener {
    private static final String SAVE_FILE_NAME = "./data/booklist.json";
    private JLabel lblTitle;
    private JLabel lblFname;
    private JLabel lblLname;
    private JLabel lblStatus;
    private JLabel lblDisplay;

    private JTextField txtTitle;
    private JTextField txtFname;
    private JTextField txtLname;
    private JTextField txtStatus;
    private JTextArea txtDisplay;


    private JButton addBook;
    private JButton saveList;
    private JButton loadList;
    private JButton displayList;

    private JRadioButton all;
    private JRadioButton read;
    private JRadioButton unread;
    ButtonGroup group;

    private BookList bookList = new BookList();

    public BookListGuiApp() {
        super("BookList App");

        // Input Functionality
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

        addBook = new JButton("Add Book");
        addBook.setBounds(120, 260, 100, 20);
        addBook.setActionCommand("AddBook");
        addBook.addActionListener(this);//makes this button to react to the action command
        add(addBook);

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

        // File Operations functionality
        saveList = new JButton("Save List to File");
        saveList.setBounds(350, 200, 150, 20);
        saveList.setActionCommand("SaveBooklist");
        saveList.addActionListener(this);//makes this button to react to the action command
        add(saveList);

        loadList = new JButton("Load List from File");
        loadList.setBounds(350, 240, 150, 20);
        loadList.setActionCommand("LoadBooklist");
        loadList.addActionListener(this);//makes this button to react to the action command
        add(loadList);

        // Display Functionality
        displayList = new JButton("Display Booklist");
        displayList.setBounds(350, 140, 150, 20);
        displayList.setActionCommand("DisplayBooklist");
        displayList.addActionListener(this);//makes this button to react to the action command
        add(displayList);

        lblDisplay = new JLabel("Display");
        lblDisplay.setBounds(600, 10, 50, 50);
        add(lblDisplay);

        txtDisplay = new JTextArea();
        txtDisplay.setBounds(600, 50, 300, 400);
        txtDisplay.setEditable(false);
        txtDisplay.setLineWrap(true);
        txtDisplay.setWrapStyleWord(true);
        add(txtDisplay);

        //Create JScrollPane that will be use as JTextArea scrollbar from JTextArea object
        /* JScrollPane scrollBar = new JScrollPane(txtDisplay,
                                               JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollBar); */

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when we click the x button the jframe will be closed

        setLayout(null);//sets frame layout
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(1000, 600); // set frame size
        setVisible(true); // display frame

    }

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

    private void addNewBook() {

        String title = txtTitle.getText();
        String firstName = txtFname.getText();
        String lastName = txtLname.getText();
        String status = txtStatus.getText();

        Book newBook = new Book(title, firstName, lastName, status);
        bookList.addBook(newBook);

        // Clear of the old values
        txtTitle.setText(null);
        txtFname.setText(null);
        txtLname.setText(null);
        txtStatus.setText(null);

    }

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
                    if (b.getStatus().toLowerCase().equals("read"))
                        printBook(b);
                } else if (unread.isSelected() == true) {
                    if (b.getStatus().toLowerCase().equals("unread"))
                        printBook(b);
                }

            }
        }
    }

    private void printBook(Book b) {
        txtDisplay.append((b.getTitle() + " by " + b.getAuthorFirstName() + " " + b.getAuthorLastName() + " - " + b.getStatus() + "\n"));
    }


    public void saveBookList() {
        JsonWriter myWriter = new JsonWriter(SAVE_FILE_NAME);
        try {

            myWriter.open();
            myWriter.write(bookList);
            myWriter.close();
            playSound("./data/saveNotify.wav");
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

    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            txtDisplay.setText(null);
            txtDisplay.append("Error with playing sound.");
            ex.printStackTrace();
        }
    }


}