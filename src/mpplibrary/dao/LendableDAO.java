/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mpplibrary.base.LendableCopy;
import mpplibrary.database.Database;
import mpplibrary.database.DatabaseFactory;
import mpplibrary.database.Query;
import mpplibrary.database.QueryException;

/**
 *
 * @author sanjeev
 */
public class LendableDAO {

    private LendableCopy lendableCopy;

    public LendableDAO(LendableCopy lendableCopy) {
        this.lendableCopy = lendableCopy;
    }

    public LendableDAO() {
    }

    public ArrayList<LendableCopy> getLendableCopies(String isbn) {
        ArrayList<LendableCopy> copies = new ArrayList<>();

        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("l.uniqueid").from("lendablecopies as l");
            q.join("LEFT", "books as b", " b.id=l.book_id");

            q.where("b.isbn=" + q.quote(String.valueOf(isbn)));
            rs = db.getResultSet();
            while (rs.next()) {
                LendableCopy b;
                b = new LendableCopy(rs.getLong("uniqueid"), rs.getLong("book_id"), rs.getString("title"), rs.getString("isbn"), rs.getBoolean("available"));
                copies.add(b);
            }

            rs.close();
        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return copies;
    }

    public void loadBookDetail(LendableCopy l) {

        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("l.uniqueid,b.*").from("lendablecopies as l");
            q.join("LEFT", "books as b", " b.id=l.book_id");

            q.where("uniqueid=" + q.quote(String.valueOf(l.getUniqueID())));
            rs = db.getResultSet();
            while (rs.next()) {

                l.setUniqueID(rs.getLong("uniqueid"));
                l.setTitle(rs.getString("title"));
                l.setISBN(rs.getString("isbn"));

            }

            rs.close();
        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean isValidCopy(LendableCopy l) {

        boolean isValid = false;
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("count(*) as cnt").from("lendablecopies as l");

            q.where("uniqueid=" + q.quote(String.valueOf(l.getUniqueID())));
            rs = db.getResultSet();

            while (rs.next()) {

                int rowcount = rs.getInt("cnt");
                isValid = rowcount > 0;

            }

            rs.close();
        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return isValid;
    }

    public boolean isAvailable(LendableCopy l) {

        boolean isValid = false;
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("count(*) as cnt").from("lendablecopies as l");

            q.where("uniqueid=" + q.quote(String.valueOf(l.getUniqueID())));
            q.where("available=" + q.quote("1"));
            rs = db.getResultSet();

            while (rs.next()) {

                int rowcount = rs.getInt("cnt");
                isValid = rowcount > 0;

            }

            rs.close();
        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return isValid;
    }

    public void makeUnvailable(LendableCopy l) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);

            q.update("lendablecopies");
            q.set("available", "0");
            q.where("uniqueid=" + q.quote(String.valueOf(l.getUniqueID())));
            
            db.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
