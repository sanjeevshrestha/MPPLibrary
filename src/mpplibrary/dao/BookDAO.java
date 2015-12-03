/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mpplibrary.base.Book;
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

    public ArrayList<Book> getBooksResult() {
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

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.books;

    }

}
