/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary;

import mpplibrary.base.roles.User;

/**
 *
 * @author sanjeev
 */
public class MPPLibraryFactory {
    
    private static User loggedInUser;
    
    
   public static User getLoggedInUser()
   {
       return loggedInUser;
   }
   
   public static void setLoggedInUser(User loggedin)
   {
       loggedInUser=loggedin;
   }
    
}
