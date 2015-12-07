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
    private String firstname = "";
    private String lastname = "";
    private String note = "";
    private String phone = "";
    private String email = "";
    private String mobile = "";
    private boolean active = true;
    private String address = "";
    private String city = "";
    private String state = "";
    private String zip = "";

    public Person(long ID) {
        this.ID = ID;
    }

    public Person() {

    }

    public Person(long ID, String firstname, String lastname, boolean active) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.active = active;
    }

    public Person(long ID, String firstname, String lastname, boolean active, String phone, String email, String mobile) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.active = active;
        this.phone = phone;
        this.email = email;
        this.mobile = mobile;

    }

    public Person(long ID, String firstname, String lastname, boolean active, String email, String phone, String mobile, String note, String address, String city, String state, String zip) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.note = note;
        this.phone = phone;
        this.email = email;
        this.mobile = mobile;
        this.active = active;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;

    }

    public Person(long id, String firstname, String lastname, String email, boolean active, String phone, String address, String city, String state, String zip) {
        this.ID = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.active = active;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (this.address != null && this.address.length() != 0) {
            sb.append(this.address);
            sb.append(", ");
        }

        if (this.city != null && this.city.length() != 0) {
            sb.append("\n");
            sb.append(this.city);
            sb.append(", ");
        }
        if (this.state != null && this.state.length() != 0) {
            sb.append(this.state);
            sb.append(", ");
        }

        if (this.zip != null && this.zip.length() != 0) {
            sb.append(this.zip);
            sb.append(", ");
        }

        return sb.toString();
    }

    public String getFullname() {
        return this.firstname + " " + this.lastname;
    }

}
