/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base;

import mpplibrary.dao.LendableDAO;

/**
 *
 * @author 984970
 */
public class LendableCopy extends Book {
    
    private LendableDAO dataAccess;

    public LendableCopy() {
        
        this.dataAccess=new LendableDAO();
    }

    public LendableCopy(String uniqueID) {
        this.uniqueID = uniqueID;
        this.dataAccess=new LendableDAO();
    }
    
    
    
    private String uniqueID;

 

    public LendableCopy(String uniqueID, long ID, String title) {
        super(ID, title);
        this.uniqueID = uniqueID;
    }

    public LendableCopy(String uniqueID, long ID, String title, String ISBN, boolean isAvailable) {
        super(ID, title, ISBN, isAvailable);
        this.uniqueID = uniqueID;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }
    
    
    
}
