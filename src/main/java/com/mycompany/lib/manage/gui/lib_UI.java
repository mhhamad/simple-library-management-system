/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lib.manage.gui;
import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 *
 * @author mahmoud
 */
public class lib_UI {
    IBook_handler book_handler = new Book_handler("Books.txt");
    
    private final JFrame frame = new JFrame("Library Manager");
    private final JPanel northPanel = new JPanel(new GridLayout(0, 4, 10, 10));
    private final JPanel southPanel = new JPanel(new GridLayout(0, 4, 10, 10));
    private final JTextArea outputArea = new JTextArea(10, 40);
    private final JTextField nameField = new JTextField();

    private final JButton addButton = new JButton("Add Book");
    private final JButton insertButton = new JButton("Insert Book");
    private final JButton displayButton = new JButton("Display Books");
    private final JButton searchByNameButton = new JButton("Search by Name");
    private final JButton searchButton = new JButton("Search");
    private final JButton removeButton = new JButton("Remove Book");
    private final JButton deleteButton = new JButton("Delete");
    
    
    private final JLabel nameLabel = new JLabel("Book Name : ");
    
    public lib_UI(){
    config();
    listeners();
    }
    public void start(){
    SwingUtilities.invokeLater(()-> frame.setVisible(true));
    }
    private void config(){
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
         
        outputArea.setFont(new Font("Serif", Font.PLAIN, 18));
        outputArea.setText("Welcome to the library management system\n");
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        northPanel.add(addButton);
        northPanel.add(displayButton);
        northPanel.add(searchByNameButton);
        northPanel.add(removeButton);
        frame.add(northPanel, BorderLayout.NORTH);
    
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.add(southPanel, BorderLayout.SOUTH);
    }
    private void listeners(){
        displayButton.addActionListener(e -> displayBooks());
        searchByNameButton.addActionListener(e -> prepareSearchForm());
        searchButton.addActionListener(e -> executeSearch());
        addButton.addActionListener(e -> prepareAddForm());
        insertButton.addActionListener(e -> executeInsert());
        removeButton.addActionListener(e->prepareRemoveForm());
        deleteButton.addActionListener(e->executeDelete());
//        
    }

    private void displayBooks() {
        clearSouthPanel();
        outputArea.setText("Books:\n");
        try {
           java.util.List<Book> books = book_handler.getAllBooks();
           if (books.size()==0)outputArea.setText("No books found\n");
           for (int i = 0; i < books.size(); i++) {
                outputArea.append(i+1 + "- " + books.get(i).getName()+'\n');
            }
            
        }catch (Exception e){outputArea.setText("Error 404" + e.getMessage());}
    
    }

    private void prepareSearchForm() {
    clearSouthPanel();
    southPanel.add(nameLabel);
    southPanel.add(nameField);
    southPanel.add(searchButton);
    refreshSouthPanel();   
    outputArea.setText("Enter a book name and press Search…");
    }
    
    private void executeSearch() {
    String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a book name first.");
            return;
        }
        try {
            boolean exists = book_handler.bookExists(name);
            outputArea.setText(exists ?
                    name + " was found in the bookshelf." :
                    name + " was NOT found in the bookshelf.");
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
        nameField.setText("");
        clearSouthPanel();
    }
    
        private void prepareAddForm() {
        clearSouthPanel();
        southPanel.add(nameLabel);
        southPanel.add(nameField);
        southPanel.add(insertButton);
        refreshSouthPanel();
        outputArea.setText("Enter a book name and press Insert…");
    }

    private void executeInsert() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a book name first.");
            return;
        }
        try {
            if (book_handler.bookExists(name)) {
                outputArea.setText("The book is already on the shelf.");
            } else {
                book_handler.addBook(new Book(name));
                outputArea.setText("Inserted successfully.");
            }
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
        nameField.setText("");
        clearSouthPanel();
    }
    
      private void clearSouthPanel() {
        southPanel.removeAll();
        refreshSouthPanel();
    }

    private void refreshSouthPanel() {
        southPanel.revalidate();
        southPanel.repaint();
    }

    private void prepareRemoveForm() {
    clearSouthPanel();
    southPanel.add(nameLabel);
    southPanel.add(nameField);
    southPanel.add(deleteButton);
    refreshSouthPanel();   
    outputArea.setText("Enter a book name and press Delete…");}

    private void executeDelete() {
    String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a book name first.");
            return;
        }
        try {
            if(!book_handler.bookExists(name)){JOptionPane.showMessageDialog(frame, "Error : The book is not in the book shelf\n please try again");
            prepareRemoveForm() ;}
            else {book_handler.removeBook(name);outputArea.setText(name +" has been removed successfully");}
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
        nameField.setText("");
        
        clearSouthPanel();
    }

   

    
}
