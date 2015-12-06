/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base;

import java.time.LocalDate;
import mpplibrary.dao.CheckoutRecordEntryDAO;

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
    private int lendableDays;

    private CheckoutRecordEntryDAO dataAccess;

    public CheckoutRecordEntry(long ID, LendableCopy book, LocalDate checkoutDate, LocalDate dueDate) {
        this.ID = ID;
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dataAccess = new CheckoutRecordEntryDAO();
    }

    public CheckoutRecordEntry(long ID, LendableCopy book, LocalDate checkoutDate, int lendableDays) {
        this.ID = ID;
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.lendableDays = lendableDays;
        this.dataAccess = new CheckoutRecordEntryDAO();

    }

    public CheckoutRecordEntry(LendableCopy book, LocalDate checkoutDate, int lendableDays) {
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.lendableDays = lendableDays;
        this.dataAccess = new CheckoutRecordEntryDAO();

    }

    public CheckoutRecordEntry(LendableCopy book, LocalDate checkoutDate) {
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.dataAccess = new CheckoutRecordEntryDAO();

    }

    public CheckoutRecordEntry(long ID) {
        this.ID = ID;
        this.dataAccess = new CheckoutRecordEntryDAO();

    }

    public CheckoutRecordEntry() {

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public LendableCopy getBook() {
        return book;
    }

    public void setBook(LendableCopy book) {
        this.book = book;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDueDateInString() {
        return dueDate.toString();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return this.book.getTitle();
    }

    public long getUniqueID() {
        return this.book.getUniqueID();
    }

    public int getLendableDays() {
        return lendableDays;
    }

    public void setLendableDays(int lendableDays) {
        this.lendableDays = lendableDays;
    }

    public void calculateDueDate() {
        this.dueDate = this.checkoutDate.plusDays(this.lendableDays);
    }

    public LocalDate calculateDueDateWithCheckoutDate(LocalDate dt) {
        return dt.plusDays(this.lendableDays);
    }

    public String getFine() {
        return "$0.00";
    }

}
