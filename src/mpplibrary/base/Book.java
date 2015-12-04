/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base;

import java.util.ArrayList;
import mpplibrary.dao.BookDAO;

/**
 *
 * @author 984970
 */
public class Book extends Publication {

    private boolean isAvailable;
    private String ISBN;

    private BookDAO dataAccess;

    public Book() {

        this.dataAccess = new BookDAO();
    }

    public Book(long ID) {
        super(ID);
        this.dataAccess = new BookDAO();

    }

    public Book(long ID, String title) {
        super(ID, title);
        this.dataAccess = new BookDAO();

    }

    public Book(long ID, String title, String isbn, String description, String type, ArrayList<Author> authors, ArrayList<String> lencopies) {
        super(ID, title, description, type);
        this.ISBN = isbn;

        this.setAuthors(authors);
        for (String s : lencopies) {
            this.addLendableCopies(Integer.parseInt(s));
        }
        this.dataAccess = new BookDAO();

    }

    public Book(long ID, String title, String ISBN, boolean isAvailable) {
        super(ID, title);
        this.ISBN = ISBN;
        this.isAvailable = isAvailable;
        this.dataAccess = new BookDAO();

    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean save() {
        return this.dataAccess.save(this);
    }

    public boolean delete() {
        return this.dataAccess.delete(this);
    }
}
