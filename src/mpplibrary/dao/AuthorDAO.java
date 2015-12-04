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
import mpplibrary.base.Member;
import mpplibrary.base.roles.User;
import mpplibrary.database.Database;
import mpplibrary.database.DatabaseFactory;
import mpplibrary.database.Query;
import mpplibrary.database.QueryException;

/**
 *
 * @author sanjeev
 */
public class AuthorDAO {
    
    
    private Member member;
    private Author author;

    private ArrayList<Author> authors;

    public AuthorDAO() {
        this.authors = new ArrayList<>();
    }

    public AuthorDAO(Author m) {
        this.author = m;
        this.authors = new ArrayList<>();

    }

    public ArrayList<Author> getAuthors() {
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("*").from("authors");
            rs = db.getResultSet();
            while (rs.next()) {
                Author m;

                m = new Author(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("active"), rs.getDouble("amount_due"),
                        rs.getString("credentials"),
                        rs.getString("bio"),
                        rs.getString("note"),
                        rs.getString("address"),
                        rs.getString("city"), rs.getString("state"), rs.getString("zip"));
                this.authors.add(m);
            }

            rs.close();

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.authors;

    }

    public ArrayList<Author> searchAuthors(String key, String value) {
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("*").from("authors");

            String[] valueParts = value.split(" ");
            for (String str : valueParts) {
                q.where(key + " LIKE \"%" + str + "%\"", "OR");
            }

            rs = db.getResultSet();
            while (rs.next()) {
                Author m;

                m = new Author(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("active"), rs.getDouble("amount_due"),
                        rs.getString("credentials"),
                        rs.getString("bio"),
                        rs.getString("note"),
                        rs.getString("address"),
                        rs.getString("city"), rs.getString("state"), rs.getString("zip"));
                this.authors.add(m);
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.authors;

    }

    public boolean loadAuthor(Author m) {
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);
            q.select("*").from("authors").where("id=" + m.getID());

            rs = db.getResultSet();

            while (rs.next()) {

                m.setID(rs.getInt("id"));
                m.setFirstname(rs.getString("firstname"));
                m.setLastname(rs.getString("lastname"));
                m.setActive(rs.getBoolean("active"));
                m.setCredentials(rs.getString("credentials"));
                m.setBio(rs.getString("bio"));
                m.setNote(rs.getString("note"));
                m.setAddress(rs.getString("address"));
                m.setCity(rs.getString("city"));
                m.setState(rs.getString("state"));
                m.setZip(rs.getString("zip"));
                
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public boolean saveAuthor(Author m) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);

            User u = MPPLibraryFactory.getLoggedInUser();

            q.insert("authors");
            q.column("firstname").value(m.getFirstname());
            q.column("lastname").value(m.getLastname());
            q.column("created_by").value(String.valueOf(u.getID()));
            q.column("active").value(String.valueOf(m.isActive()));
            q.column("note").value(String.valueOf(m.getNote()));
            q.column("bio").value(m.getBio());
            q.column("credentials").value(m.getCredentials());
            q.column("address").value(m.getAddress());
            q.column("city").value(m.getCity());
            q.column("state").value(m.getState());
            q.column("zip").value(m.getZip());

            long id=db.execute();
            m.setID(id);
            
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;

    }

    public boolean deleteAuthor(Author m) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);

            User u = MPPLibraryFactory.getLoggedInUser();

            q.delete("authors");
            q.where("id="+q.quote(String.valueOf(m.getID())));

            db.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;

    }
    
}
