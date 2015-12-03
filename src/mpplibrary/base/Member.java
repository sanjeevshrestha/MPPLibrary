/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base;

import mpplibrary.dao.MemberDAO;

/**
 *
 * @author 984970
 */
public class Member extends Person {

    private double amount_due;

    private MemberDAO dataAccess;

    public Member() {

        this.dataAccess = new MemberDAO();

    }

    public Member(long ID) {
        super(ID);
        this.dataAccess = new MemberDAO();

    }

    public Member(long ID, String firstname, String lastname, boolean active) {
        super(ID, firstname, lastname, active);
        this.dataAccess = new MemberDAO();

    }

    public Member(long ID, String firstname, String lastname, boolean active, double amt) {
        super(ID, firstname, lastname, active);
        this.amount_due = amt;
        this.dataAccess = new MemberDAO();

    }
    
    public Member(long ID, String firstname, String lastname, boolean active, double amt,String email, String phone, String mobile,String note,String address,String city, String state, String zip) {
        super(ID, firstname, lastname, active,email,phone,mobile,note,address,city,state,zip);
        this.amount_due = amt;
        this.dataAccess = new MemberDAO();

    }

    public boolean loadMember() {
        
        this.dataAccess.loadMember(this);

        return true;

    }
    
    public boolean saveMember()
    {
        return this.dataAccess.saveMember(this);
    }

    public double getAmountDue() {
        return amount_due;
    }

    public void setAmountDue(double amount_due) {
        this.amount_due = amount_due;
    }

}
