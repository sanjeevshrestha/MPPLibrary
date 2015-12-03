/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base;

import java.time.LocalDate;

/**
 *
 * @author 984970
 */
public class CheckoutRecordEntry {
    
    private long ID;
    private LendableCopy book;
    private String note;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    public CheckoutRecordEntry(long ID, LendableCopy book, String note, LocalDate checkoutDate, LocalDate dueDate) {
        this.ID = ID;
        this.book = book;
        this.note = note;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public CheckoutRecordEntry(long ID) {
        this.ID = ID;
    }

    public CheckoutRecordEntry() {
        
    }
    
    
    
    
   
}
