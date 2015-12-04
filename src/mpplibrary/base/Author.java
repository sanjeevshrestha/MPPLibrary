/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base;

import mpplibrary.dao.AuthorDAO;

/**
 *
 * @author 984970
 */
public class Author extends Person {

    private String credentials;
    private String bio;
    
    private AuthorDAO dataAccess;
    

    public Author(String firstName, String lastName, String email, String credentials, String bio) {
        super(0, firstName, lastName, true, "", email, "");
        this.credentials = credentials;
        this.bio = bio;
        this.dataAccess = new AuthorDAO();

    }
    
    
     public Author(long ID, String firstname, String lastname, boolean active,  String credentials, String bio, String note,String email, String address, String city, String state, String zip) {
        super(ID, firstname, lastname, active);
        this.setCredentials(credentials);
        this.setBio(bio);
        this.setAddress(address);
        this.setCity(city);
        this.setState(state);
        this.setZip(zip);
        this.setEmail(email);
        
        this.dataAccess = new AuthorDAO();

    }
    public String getAuthorFullName(){
        return getFirstname() + " " + getLastname();
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    
    public boolean save()
    {
       return  this.dataAccess.saveAuthor(this);
    }

    @Override
    public String toString() {
    return getAuthorFullName();
    }
    
    
}
