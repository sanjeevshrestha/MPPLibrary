/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base.roles;

/**
 *
 * @author 984970
 */
public class Admin extends User implements Role {
    
    public Admin()
    {
        super();
    }
    
    public Admin(String username,String password)
    {
        super(username,password);
    }

    @Override
    public boolean canLogin() {
        return true;
    }

    @Override
    public boolean canAddBook() {
        return true;
    }

    @Override
    public boolean canEditBook() {
        return true;
    }

    @Override
    public boolean canDeleteBook() {
        return true;
    }

    @Override
    public boolean canCheckoutBook() {
        return false;
    }

    @Override
    public boolean canCheckinBook() {
        return false;
    }

    @Override
    public boolean canAddMember() {
        return true;
    }

    @Override
    public boolean canEditMember() {
        return true;
    }

    @Override
    public boolean canDeleteMember() {
        return true;
    }

    @Override
    public boolean canAddAuthor() {
        return true;
    }

    @Override
    public boolean canDeleteAuthor() {
        return true;
    }

    @Override
    public boolean canEditAuthor() {
        return true;
    }

    public static void main(String[] args) {
        Admin adm=new Admin("sanjeev","password");
        adm.login();
    }
 
   
}
