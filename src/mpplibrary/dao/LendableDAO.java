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
    
    
    public ArrayList<LendableCopy> getLendableCopies(String isbn)
    {
        ArrayList<LendableCopy> copies=new ArrayList<>();
        
         ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("*").from("lendablecopies");
            q.where("isbn="+q.quote(isbn));
            System.out.println(q.getQuery());
            rs = db.getResultSet();
            while (rs.next()) {
                LendableCopy b;
                b = new LendableCopy(rs.getString("uniqueid"));
                copies.add(b);
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return copies;
    }
    
}
