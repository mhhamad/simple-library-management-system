
package com.mycompany.lib.manage.gui;
import java.util.Objects;
/**
 *
 * @author mahmoud
 */
public class Book {
    private String name;
   

    public Book(String name) {
        this.name = name.trim();
        
    }

    public void setName(String name) {
        this.name = name;
    }

   

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name + " book";
    }
}
