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
public class Address {

    private long ID;
    private String address;
    private String city;
    private String zip;
    private String state;

    public Address(String address, String city, String zip, String state) {
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.state = state;
    }

    public Address(long ID, String address, String city, String zip, String state) {
        this.ID = ID;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.state = state;
    }
    
    

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
