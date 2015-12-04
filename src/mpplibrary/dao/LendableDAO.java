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
            q.join("LEFT", "books as b", " b.id=l.bookid");

            q.where("b.isbn=" + q.quote(String.valueOf(isbn)));
            System.out.println(q.getQuery());
            rs = db.getResultSet();
            while (rs.next()) {
                LendableCopy b;
                b = new LendableCopy(rs.getLong("uniqueid"), rs.getLong("bookid"), rs.getString("title"), rs.getString("isbn"), rs.getBoolean("available"));
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
            q.join("LEFT", "books as b", " b.id=l.bookid");

            q.where("uniqueid=" + q.quote(String.valueOf(l.getUniqueID())));
            System.out.println(q.getQuery());
            rs = db.getResultSet();
            while (rs.next()) {

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
            System.out.println(q.getQuery());
            rs = db.getResultSet();
            
            while (rs.next()) {
                
                int rowcount=rs.getInt("cnt");
                isValid=rowcount>0;
                System.out.println("Count"+rowcount);

            }

            rs.close();
        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return isValid;
    }

}
