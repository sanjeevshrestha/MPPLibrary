/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mpplibrary.base.CheckoutRecord;
import mpplibrary.base.CheckoutRecordEntry;
import mpplibrary.base.LendableCopy;
import mpplibrary.base.Member;
import mpplibrary.database.Database;
import mpplibrary.database.DatabaseFactory;
import mpplibrary.database.Query;
import mpplibrary.database.QueryException;

/**
 *
 * @author sanjeev
 */
public class CheckoutRecordDAO {
    
    
   private CheckoutRecord record;

    private ArrayList<CheckoutRecord> records;

    public CheckoutRecordDAO() {
        this.records = new ArrayList<>();
    }

    public CheckoutRecordDAO(CheckoutRecord r) {
        this.record = r;
        this.records = new ArrayList<>();

    }

    public ArrayList<CheckoutRecord> getCheckoutRecords() {
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("*").from("checkoutrecords");
            rs = db.getResultSet();
            while (rs.next()) {
                CheckoutRecord r;
                Member m=new Member(rs.getInt("checkout_by"));
                m.loadMember();
                r = new CheckoutRecord(rs.getLong("id"), m);
                this.records.add(r);
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.records;

    }

    public ArrayList<CheckoutRecord> searchMembers(String key, String value) {
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("*").from("checkoutrecords");

            String[] valueParts = value.split(" ");
            for (String str : valueParts) {
                q.where(key + " LIKE \"%" + str + "%\"", "OR");
            }

            rs = db.getResultSet();
            while (rs.next()) {
               CheckoutRecord r;
                Member m=new Member(rs.getInt("checkout_by"));
                m.loadMember();
                r = new CheckoutRecord(rs.getLong("id"), m);
                this.records.add(r);
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.records;

    }
    
    public ArrayList<CheckoutRecordEntry> getCheckoutEntries(long cid)
    {
             ResultSet rs = null;
             ArrayList<CheckoutRecordEntry> entries=new ArrayList<>();
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("*").from("recordentries");
            q.where("checkout_record_id="+cid);

            rs = db.getResultSet();
            while (rs.next()) {
               CheckoutRecordEntry r;
               LendableCopy lc=new LendableCopy();
                r = new CheckoutRecordEntry(rs.getLong("id"));
                entries.add(r);
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return entries;
        
    }
    
}
