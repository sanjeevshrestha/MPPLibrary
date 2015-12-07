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
    private LendableDAO dataAccess;
    
    

    public LendableCopy() {

        this.dataAccess = new LendableDAO();
    }

    public LendableCopy(long uniqueID) {
        this.uniqueID = uniqueID;
        this.dataAccess = new LendableDAO();

    }

    public LendableCopy(long uniqueID, long ID, String title) {
        super(ID, title);
        this.uniqueID = uniqueID;
        this.dataAccess = new LendableDAO();

    }

    public LendableCopy(long uniqueID, long ID) {
        super(ID);
        this.uniqueID = uniqueID;
        this.dataAccess = new LendableDAO();

    }

    public LendableCopy(long uniqueID, int lendableDays) {
        this.uniqueID = uniqueID;
        this.dataAccess = new LendableDAO();

    }

    public LendableCopy(long uniqueID, long ID, String title, String ISBN, boolean isAvailable) {
        super(ID, title, ISBN, isAvailable);
        this.uniqueID = uniqueID;
        this.dataAccess = new LendableDAO();

    }

    public long getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(long uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void loadBookDetail() {
        this.dataAccess.loadBookDetail(this);
    }

    
    public boolean isValidCopy()
    {
       return this.dataAccess.isValidCopy(this);
    }
    
    public boolean isAvailable()
    {
        return this.dataAccess.isAvailable(this);
        
    }
    
    public void makeUnavailable()
    {
        this.dataAccess.makeUnvailable(this);
    }
    
    public String getStrUniqueId(){
        return (uniqueID+"");
    }
}
