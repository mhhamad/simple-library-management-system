/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lib.manage.gui;

import java.io.*;
import java.util.*;

/**
 *
 * @author mahmoud
 */
public class Book_handler implements IBook_handler{
private final File file ;

    public Book_handler(String path){
    file = new File(path);
    }

    @Override
    public void addBook(Book book) throws Exception {
        if(bookExists(book.getName()))throw new Exception("the book is already in the shelf\n");
        try (FileWriter writer = new FileWriter(file,true)){
        if(getAllBooks().size() == 0)writer.write('\n');
            writer.write(book.getName()+'\n');
            
        }
    }
    
       @Override
    public void removeBook(String bookName) throws Exception {
    List<Book> allbooks = getAllBooks();
    // delete the file content
    try (FileWriter writer = new FileWriter(file)){}
 
    try (FileWriter writer = new FileWriter(file,true)){
 
        for(Book ebook : allbooks){
    if(!ebook.getName().equals(bookName))writer.write(ebook.getName()+'\n');
    }}
    }
    @Override
    public List<Book> getAllBooks()throws Exception{
    List<Book> books = new ArrayList<Book>();
    try(Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()){
        books.add(new Book(scanner.nextLine()));
        }
    }
    return books;
    }
    
    
    
    @Override
    public boolean bookExists(String bookName) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().equalsIgnoreCase(bookName)) {
                    return true;
                }
            }
        }
        return false;
    }

 
}
