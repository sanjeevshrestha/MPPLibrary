/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base;

import java.time.LocalDate;
import mpplibrary.dao.LendableDAO;

/**
 *
 * @author 984970
 */
public class LendableCopy extends Book {

    private long uniqueID;
    private int lendableDays;
    private LocalDate dueDate;
    private LendableDAO dataAccess;

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LendableCopy() {

        this.dataAccess = new LendableDAO();
        this.lendableDays = 0;
    }

    public LendableCopy(long uniqueID) {
        this.uniqueID = uniqueID;
        this.dataAccess = new LendableDAO();
        this.lendableDays = 0;

    }

    public LendableCopy(long uniqueID, long ID, String title) {
        super(ID, title);
        this.uniqueID = uniqueID;
        this.lendableDays = 0;
        this.dataAccess = new LendableDAO();

    }

    public LendableCopy(long uniqueID, long ID) {
        super(ID);
        this.uniqueID = uniqueID;
        this.lendableDays = 0;
        this.dataAccess = new LendableDAO();

    }

    public LendableCopy(long uniqueID, int lendableDays) {
        this.uniqueID = uniqueID;
        this.lendableDays = lendableDays;
        this.dataAccess = new LendableDAO();

    }

    public LendableCopy(long uniqueID, long ID, String title, String ISBN, boolean isAvailable) {
        super(ID, title, ISBN, isAvailable);
        this.uniqueID = uniqueID;
        this.lendableDays = 0;
        this.dataAccess = new LendableDAO();

    }

    public long getUniqueID() {
        return uniqueID;
    }

    public int getLendableDays() {
        return lendableDays;
    }

    public void setLendableDays(int lendableDays) {
        this.lendableDays = lendableDays;
    }

    public void setUniqueID(long uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void loadBookDetail() {
        this.dataAccess.loadBookDetail(this);
    }

    public void calculateDueDate(LocalDate r) {
        this.dueDate = r.plusDays(this.getLendableDays());
    }

    
    public boolean isValidCopy()
    {
       return this.dataAccess.isValidCopy(this);
    }
}
