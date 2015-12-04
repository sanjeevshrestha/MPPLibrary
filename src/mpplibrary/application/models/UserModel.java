/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.models;

import java.util.ArrayList;
import java.util.Date;
import mpplibrary.MPPLibraryFactory;
import mpplibrary.base.roles.Admin;
import mpplibrary.base.roles.Librarian;
import mpplibrary.base.roles.User;
import mpplibrary.dao.UserDAO;

/**
 *
 * @author 984970
 */
public class UserModel {

    private static UserModel instance;
    
    static {
        instance = new UserModel();
    }

    public static UserModel getInstance() {
        return instance;

    }

    public boolean login(String username, String password) {
        User u = new User(username, password);
        if (u.login()) {
            String role = u.getRole();
            User lUser = null;
            switch (role) {
                case "admin":
                    lUser = new Admin(username, password);
                    break;
                case "librarian":
                    lUser = new Librarian(username, password);

                    break;
            }
            MPPLibraryFactory.setLoggedInUser(lUser);
            return true;
        } else {
            return false;
        }
    }

    public boolean save(long ID, String firstname, String lastname, String email, String username, String password, String role, Date createdDate, long createdBy, Date modifiedDate, long modifiedBy, boolean active, String phone, String address, String city, String state, String zip) {
        User user = new User(0, firstname, lastname, email, username, password, role, createdDate, createdBy, modifiedDate, modifiedBy, true, phone, address, city, state, zip);
        return user.saveMember();
    }
    
    public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        
        try {
            UserDAO dao = new UserDAO();
            users = dao.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return users;
    }
    
    public boolean delete(long ID) {
        User m = new User(ID);
        return m.delete();
    }

}
