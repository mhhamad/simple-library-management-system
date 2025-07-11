/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.lib.manage.gui;

import java.util.*;
/**
 *
 * @author mahmoud
 */
public interface IBook_handler {
    
    public void addBook(Book book) throws Exception;
    public boolean bookExists(String bookName) throws Exception;
    public List<Book> getAllBooks()throws Exception;
    public void removeBook(String bookName)throws Exception;
}
