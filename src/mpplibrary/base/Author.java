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
public class Author extends Person {

    private String credentials;
    private String bio;

    public Author(String firstName, String lastName, String email, String credentials, String bio) {
        super(0, firstName, lastName, true, "", email, "");
        this.credentials = credentials;
        this.bio = bio;
    }
    
    public String getAuthorFullName(){
        return getFirstname() + " " + getLastname();
    }

}
