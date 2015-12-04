/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.models;

import java.util.ArrayList;
import mpplibrary.base.Book;
import mpplibrary.base.LendableCopy;
import mpplibrary.dao.BookDAO;
import mpplibrary.dao.LendableDAO;

/**
 *
 * @author 984970
 */
public class BookModel {

    private static BookModel instance;

    static {
        instance = new BookModel();
    }

    public static BookModel getInstance() {
        return instance;

    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();

        try {
            BookDAO bd = new BookDAO();
            books = bd.getBooks();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return books;

    }

    public ArrayList<Book> searchBooks(String key, String value) {
        ArrayList<Book> books = new ArrayList<>();

        try {
            BookDAO bd = new BookDAO();
            books = bd.searchBooks(key, value);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return books;

    }

    public ArrayList<LendableCopy> getLendableCopies(String isbn) {

        ArrayList<LendableCopy> copies = new ArrayList<>();
        try {
            LendableDAO lcd = new LendableDAO();
            copies = lcd.getLendableCopies(isbn);

        } catch (Exception e) {
        }

        return copies;

    }

    public boolean save(long ID, String title, String ISBN, boolean available) {
        Book b = new Book(ID, title, ISBN, available);
        return b.save();

    }

    public boolean delete(long ID) {
        Book b = new Book(ID);
        return b.delete();
    }

}
