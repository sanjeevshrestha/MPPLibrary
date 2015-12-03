/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.models;

import java.util.ArrayList;
import mpplibrary.base.CheckoutRecord;
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

}
