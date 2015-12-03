/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base;

/**
 *
 * @author 984970
 */
abstract public class Person {
     private long ID;

    private String firstname;
    private String lastname;
    private Address address;
    private String note;
    private String phone;
    private String email;
    private String mobile;
    private boolean active;

    public Person(long ID) {
        this.ID = ID;
    }
    
    
    
    public Person()
    {
        
    }
    
    public Person(long ID,String firstname, String lastname,boolean active)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.active=active;
    }
    
    
     public Person(long ID, String firstname, String lastname,boolean active, String phone, String email, String mobile)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.active=active;
        this.phone=phone;
        this.email=email;
        this.mobile=mobile;
    }

    public Person(long ID, String firstname, String lastname,  boolean active,  String email, String phone, String mobile, String note) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.note = note;
        this.phone = phone;
        this.email = email;
        this.mobile = mobile;
        this.active = active;
    }
     
     

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
     
     
   
}
