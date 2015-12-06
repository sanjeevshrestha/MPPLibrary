/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import mpplibrary.base.Author;
import mpplibrary.base.Book;
import mpplibrary.base.LendableCopy;
import mpplibrary.dao.AuthorDAO;
import mpplibrary.dao.BookDAO;
import mpplibrary.dao.LendableDAO;
import mpplibrary.database.Database;
import mpplibrary.database.DatabaseFactory;
import mpplibrary.database.Query;

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
    
    public ArrayList<Author> getAllAuthors() {

        ArrayList<Author> authors = new ArrayList<>();
        try {
            AuthorDAO ad = new AuthorDAO();
            authors = ad.getAuthors();

        } catch (Exception e) {
        }

        return authors;

    }

    public boolean save(long ID, String title, String ISBN, boolean available) {
        Book b = new Book(ID, title, ISBN, available);
        return b.save();

    }

    public boolean delete(long ID) {
        Book b = new Book(ID);
        return b.delete();
    }
    
    
    public Book getBook(long ID)
    {
        Book b=new Book(ID);
        b.loadBook();
        return b;
                
    }
    
    
    public boolean isUniqueISBN(String isbn)
    {
        try {
            ResultSet rs = null;
            
             Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("count(*) as cnt").from("books");

            q.where("ISBN=" + q.quote(isbn));
             rs = db.getResultSet();
            while (rs.next()) {
                int rowcount=rs.getInt("cnt");
                return (rowcount==0);
                
            }
            rs.close();
            
        } catch (Exception e) {
        }
        
        return true;
    }

}
