/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.models;

import java.time.LocalDate;
import java.util.ArrayList;
import mpplibrary.base.CheckoutRecord;
import mpplibrary.base.CheckoutRecordEntry;
import mpplibrary.dao.CheckoutRecordDAO;

/**
 *
 * @author 984970
 */
public class CheckoutModel {

    private static CheckoutModel instance;

    static {
        instance = new CheckoutModel();
    }

    public static CheckoutModel getInstance() {
        return instance;

    }
    
    
    public ArrayList<CheckoutRecord> getCheckoutRecords()
    {
         ArrayList<CheckoutRecord> records = new ArrayList<>();

        try {
            CheckoutRecordDAO cd = new CheckoutRecordDAO();
             records = cd.getCheckoutRecords();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return records;

    }
    
    
    public boolean isBookValid(long uniqueID)
    {
        return true;
        
    }
    
    public boolean isBookAvailable(long uniqueID)
    {
        return true;
        
    }
    
    
    public boolean checkout(long memberID,LocalDate checkoutDate,ArrayList<CheckoutRecordEntry> list)
    {
        
        return true;
    }
    
    

}
