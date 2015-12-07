/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import mpplibrary.MPPLibraryFactory;
import mpplibrary.base.CheckoutRecord;
import mpplibrary.base.CheckoutRecordEntry;
import mpplibrary.base.LendableCopy;
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
                Member m = new Member(rs.getInt("checkout_by"));
                m.loadMember();

                LocalDate ld = LocalDate.parse(rs.getString("checkout_date"));
                r = new CheckoutRecord(rs.getLong("id"), ld, m);
                this.records.add(r);
            }

            rs.close();

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.records;

    }

    public ArrayList<CheckoutRecord> searchCheckoutRecords(String key, String value) {
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
                Member m = new Member(rs.getInt("checkout_by"));
                m.loadMember();
                LocalDate ld = LocalDate.parse(rs.getString("checkout_date"));
                r = new CheckoutRecord(rs.getLong("id"), ld, m);
                this.records.add(r);
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.records;

    }

    public ArrayList<CheckoutRecordEntry> getCheckoutEntries(long cid) {
        ResultSet rs = null;
        ArrayList<CheckoutRecordEntry> entries = new ArrayList<>();
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("*").from("recordentries");
            q.where("checkout_record_id=" + cid);

            rs = db.getResultSet();
            while (rs.next()) {
                CheckoutRecordEntry r;
                LendableCopy lc = new LendableCopy();
                r = new CheckoutRecordEntry(rs.getLong("id"));
                entries.add(r);
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return entries;

    }

    public void save(CheckoutRecord record) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);

            User u = MPPLibraryFactory.getLoggedInUser();

            q.insert("checkoutrecords");
            q.column("checkout_by").value(String.valueOf(record.getCheckedOutBy().getID()));
            q.column("checkout_date").value(record.getCheckoutDate().toString());
            q.column("created_by").value(String.valueOf(u.getID()));
            q.column("created").value(LocalDate.now().toString());

            long insertid = db.execute();

            for (CheckoutRecordEntry r : record.getCheckoutItems()) {
                q = db.getQuery(true);

                q.insert("recordentries");
                q.column("checkout_record_id").value(String.valueOf(insertid));
                q.column("lendable_id").value(String.valueOf(r.getBook().getUniqueID()));
                q.column("checkout_date").value(record.getCheckoutDate().toString());

                q.column("due_date").value(r.calculateDueDateWithCheckoutDate(record.getCheckoutDate()).toString());
                q.column("checked_in").value("false");
                db.execute();
                r.getBook().makeUnavailable();

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void load(CheckoutRecord r) {

        List<CheckoutRecordEntry> entries=new ArrayList<>();
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);
            q.select("*").from("recordentries");
            q.where("checkout_record_id="+q.quote(String.valueOf(r.getID())));
            ResultSet rs=db.getResultSet();
            while(rs.next())
            {
                LendableCopy l=new LendableCopy(rs.getLong("lendable_id"));
                l.loadBookDetail();
                CheckoutRecordEntry e=new CheckoutRecordEntry(l,LocalDate.parse(rs.getString("checkout_date")));
                e.setDueDate(LocalDate.parse(rs.getString("due_date")));
                e.setChecked_in(rs.getBoolean("checked_in"));
              
                entries.add(e);
               // r.addRecordEntry(e);
            }
            r.setCheckoutItems(entries);
            
            

        } catch (Exception e) {
        }

        
    }

}
