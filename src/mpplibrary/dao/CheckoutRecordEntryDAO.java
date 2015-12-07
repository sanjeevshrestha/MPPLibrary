/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.dao;

import java.time.LocalDate;
import mpplibrary.MPPLibraryFactory;
import mpplibrary.base.CheckoutRecord;
import mpplibrary.base.CheckoutRecordEntry;
import mpplibrary.base.roles.User;
import mpplibrary.database.Database;
import mpplibrary.database.DatabaseFactory;
import mpplibrary.database.Query;

/**
 *
 * @author sanjeev
 */
public class CheckoutRecordEntryDAO {

    public boolean checkin(CheckoutRecordEntry entry) {

        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);
            q.update("recordentries");
            q.set("checked_in", "1");
            q.where("id=" + q.quote(String.valueOf(entry.getID())));
     
            db.execute();
            
            q = db.getQuery(true);
            q.update("lendablecopies");
            q.set("available", "1");
            q.where("uniqueid=" + q.quote(String.valueOf(entry.getBook().getUniqueID())));
            System.out.println(q.getQuery());
            db.execute();
     
            return true;

        } catch (Exception e) {
            return false;

        }

    }

}
