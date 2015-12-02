/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.models;

import mpplibrary.MPPLibraryFactory;
import mpplibrary.base.roles.Admin;
import mpplibrary.base.roles.Librarian;
import mpplibrary.base.roles.User;

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
            User lUser=null;
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
        }
        else
        {
            return false;
        }

    }
    
  
}
