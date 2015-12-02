/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.models;

import java.util.ArrayList;
import mpplibrary.base.Book;
import mpplibrary.dao.BookDAO;

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
            books = bd.getBooksResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return books;

    }

}
