/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author 984970
 */
public class CheckoutRecord {

    private long ID;

    private Member checkedOutBy;
    private LocalDate checkoutDate;

    public CheckoutRecord() {
    }

    public CheckoutRecord(long ID, Member member) {
        this.ID = ID;
        this.checkedOutBy = member;

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Member getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(Member checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    public List<CheckoutRecordEntry> getCheckoutItems() {
        return checkoutItems;
    }

    public void setCheckoutItems(List<CheckoutRecordEntry> checkoutItems) {
        this.checkoutItems = checkoutItems;
    }

    public List<Fine> getFines() {
        return fines;
    }

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    private List<CheckoutRecordEntry> checkoutItems;
    private List<Fine> fines;

}
