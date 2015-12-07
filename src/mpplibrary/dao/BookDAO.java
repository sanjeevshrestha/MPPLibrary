/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mpplibrary.MPPLibraryFactory;
import mpplibrary.base.Author;
import mpplibrary.base.Book;
import mpplibrary.base.LendableCopy;
import mpplibrary.base.roles.User;
import mpplibrary.database.Database;
import mpplibrary.database.DatabaseFactory;
import mpplibrary.database.Query;
import mpplibrary.database.QueryException;

/**
 *
 * @author 984970
 */
public class BookDAO {
    
    private Book book;
    
    private ArrayList<Book> books;
    
    public BookDAO() {
        
        this.books = new ArrayList<>();
    }
    
    public BookDAO(Book b) {
        this.book = b;
        this.books = new ArrayList<>();
        
    }
    
    public ArrayList<Book> getBooks() {
        ResultSet rs = null;
        try {
            
            Database db = DatabaseFactory.getInstance();
            
            Query q = db.getQuery(true);
            q.select("*").from("books");
            rs = db.getResultSet();
            while (rs.next()) {
                Book b;
                b = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("ISBN"), rs.getBoolean("available"));
                this.books.add(b);
            }
            rs.close();
            
        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return this.books;
        
    }
    
    public ArrayList<Book> searchBooks(String key, String value) {
        ResultSet rs = null;
        try {
            
            Database db = DatabaseFactory.getInstance();
            
            Query q = db.getQuery(true);
            q.select("*").from("books");
            
            String[] valueParts = value.split(" ");
            for (String str : valueParts) {
                q.where(key + " LIKE \"%" + str + "%\"", "OR");
            }
            
            rs = db.getResultSet();
            while (rs.next()) {
                Book b;
                b = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("ISBN"), rs.getBoolean("available"));
                this.books.add(b);
            }
            rs.close();
            
        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return this.books;
        
    }
    
    public boolean save(Book b) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);
            User u = MPPLibraryFactory.getLoggedInUser();
            q.insert("books");
            q.column("title").value(b.getTitle());
            q.column("ISBN").value(b.getISBN());
            q.column("created_by").value(String.valueOf(u.getID()));
            q.column("available").value(String.valueOf(b.isAvailable()));
            q.column("description").value(String.valueOf(b.getDescription()));
            q.column("type").value(b.getType());
            
            long insertid = db.execute();
            for (Author a : b.getAuthors()) {
                q = db.getQuery(true);
                q.insert("books_authors");
                q.column("book_id").value(String.valueOf(insertid));
                q.column("author_id").value(String.valueOf(a.getID()));
                db.execute();
                
            }
            
            for (LendableCopy l : b.getLendableCopies()) {
                q = db.getQuery(true);
                q.insert("lendablecopies");
                q.column("book_id").value(String.valueOf(insertid));
                q.column("uniqueid").value(String.valueOf(l.getUniqueID()));
                q.column("available").value("1");
                db.execute();
                
            }
            
            return true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    public boolean delete(Book b) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);
            
            User u = MPPLibraryFactory.getLoggedInUser();
            
            q.delete("books");
            q.where("id=" + q.quote(String.valueOf(b.getID())));
            
            db.execute();
            return true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
        
    }
    
    public boolean loadBook(Book bin) {
        ResultSet rs = null, rs1 = null;
        System.out.println("called");
        try {
            
            Database db = DatabaseFactory.getInstance();
            
            Query q = db.getQuery(true);
            q.select("*").from("books");
            
            q.where("id=" + q.quote(String.valueOf(bin.getID())));
            
            rs = db.getResultSet();
            while (rs.next()) {
                //b = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("ISBN"), rs.getBoolean("available"));
                bin.setTitle(rs.getString("title"));
                bin.setID(rs.getLong("id"));
                bin.setISBN(rs.getString("ISBN"));
                bin.setIsAvailable(rs.getBoolean("available"));
                bin.setDescription(rs.getString("description"));
                
                q = db.getQuery(true);
                q.select("*").from("lendablecopies").where("book_id=" + q.quote(String.valueOf(bin.getID())));
                
                rs1 = db.getResultSet();
                while (rs1.next()) {
                    LendableCopy lc = new LendableCopy(rs1.getLong("uniqueid"), rs1.getLong("book_id"));
                    System.out.println(rs1.getBoolean("available"));
                    
                    lc.setIsAvailable(rs1.getBoolean("available"));
                    
                    bin.addLendableCopies(lc);
                }
                
                q = db.getQuery(true);
                q.select("*").from("books_authors").where("book_id=" + q.quote(String.valueOf(bin.getID())));
                rs1 = db.getResultSet();
                
                while (rs1.next()) {
                    Author a = new Author(rs1.getLong("author_id"));
                    a.loadAuthor();
                    bin.addAuthor(a);
                    
                }
                
                return true;
            }
            rs.close();
            
        } catch (QueryException | SQLException e) {
            
            System.out.println(e.getMessage());
        }
        return false;
        
    }
    
}
