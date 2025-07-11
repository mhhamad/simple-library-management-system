package com.mycompany.lib.manage.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author mahmoud hamad
 */

public class LibManageGUI {

    public static void main(String[] args) {
       lib_UI UI = new lib_UI();
       
       UI.start();
    }
    
    public static void controlGUI(){
     JFrame frame= new JFrame("Library Manager");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel (new GridLayout(0,5,10,10));
        JPanel buttonPanel = new JPanel (new GridLayout(0,3,10,10));
        
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // adding name Field
        JTextField nameField = new JTextField();
       
        
       
        // adding buttons
        JButton addButton = new JButton("Add Book");
        JButton insertButton = new JButton("insert Book");
        JButton DisplayButton = new JButton("Display Books");
        JButton SearchByNameButton = new JButton("Search by Name");
        JButton SearchButton = new JButton("Search");
        
        buttonPanel.add(addButton);
        buttonPanel.add(DisplayButton);
        buttonPanel.add(SearchByNameButton);
        // add test erea
         JTextArea output = new JTextArea(10, 40);
         

        //output.setEditable(false);
         JScrollPane scrollPane = new JScrollPane(output);
         output.setFont(new Font("Serif", Font.PLAIN, 34));
         output.setText("Welcome to library management system\nmade by mahmoud hamad \n");
         
        //adding panels to fram
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.NORTH);
        //labels 
        JLabel nameLabel =new JLabel("Book Name : ");
        
        
        // event handeling 
        DisplayButton.addActionListener(l->{
        output.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.remove (nameLabel);
        panel.remove(nameField);
        panel.remove(insertButton);
        panel.remove(SearchButton);
        panel.revalidate();
        panel.repaint();
            output.setText("Books:\n");
        try(Scanner scanner = new Scanner(new File("Books.txt"))){
            int x =1;
            while (scanner.hasNextLine()){
            output.append(x++ +"- "+ scanner.nextLine() + "\n");
        }
        }
        catch(Exception e){
        output.setText("Error 404 : failed to open the bookshelf ");
        }
        });
        
        SearchByNameButton.addActionListener(e->{
            output.setFont(new Font("Arial", Font.PLAIN, 18));
            panel.remove (nameLabel);
        panel.remove(nameField);
        panel.remove(insertButton);
        panel.remove(SearchButton);
        panel.revalidate();
        panel.repaint();
    output.setText("Searching for book in the bookshelf");
        panel.add (nameLabel);
        panel.add(nameField);
        panel.add(SearchButton);
        panel.revalidate();
        panel.repaint();
    });
        SearchButton.addActionListener(l->{
        // search logic 
        try(Scanner scanner = new Scanner(new File("Books.txt"))){
            boolean isThere=false;
            while (scanner.hasNextLine()){
            if (scanner.nextLine().equals(nameField.getText())){isThere=true; break;}
        }
            if (isThere) output.setText(nameField.getText() + " was found in the bookshelf");
            else output.setText(nameField.getText() + " was not found in the bookshelf");
        }
        catch(Exception e){
        output.setText("Error 404 : failed to open the bookshelf ");
        }
        
        nameField.setText("");
        panel.remove (nameLabel);
        panel.remove(nameField);
        panel.remove(SearchButton);
        panel.revalidate();
        panel.repaint();
        });
        
        addButton.addActionListener(e->{
            output.setFont(new Font("Arial", Font.PLAIN, 18));
            panel.remove (nameLabel);
        panel.remove(nameField);
        panel.remove(insertButton);
        panel.remove(SearchButton);
        panel.revalidate();
        panel.repaint();
        output.setText("adding book to bookshelf");
        panel.add (nameLabel);
        panel.add(nameField);
        panel.add(insertButton);
        panel.revalidate();
        panel.repaint();
    });
        insertButton.addActionListener(l->{
        // insertion logic 
        try(FileWriter writer  = new FileWriter("Books.txt",true)){
        Scanner scanner = new Scanner(new File("Books.txt"));
        boolean isThere=false;
        while (scanner.hasNextLine()){
        String Line = scanner.nextLine();
        if (Line.equals(nameField.getText())){isThere=true;break;}
        }
        if (isThere)
            output.setText("the Book is already in the shelf");
        else{
            writer.write(nameField.getText() + '\n');
            output.setText("insertion done successfully");}
        }catch(Exception e){
        output.setText("Error 404 : failed to save the book ");
        }
        
        nameField.setText("");
        
        
        panel.remove (nameLabel);
        panel.remove(nameField);
        panel.remove(insertButton);
        
        panel.revalidate();
        panel.repaint();
        
        });
        
        
        frame.setVisible(true);
        
    }
}
