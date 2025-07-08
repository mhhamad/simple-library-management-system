
package com.mycompany.lib.manage.gui;
import java.util.Objects;
/**
 *
 * @author mahmoud
 */
public class Book {
    private String name;
   

    public Book(String name) {
        this.name = name;
        
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Book other = (Book) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
