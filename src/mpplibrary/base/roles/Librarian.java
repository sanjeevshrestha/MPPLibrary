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
public class Librarian extends User implements Role {
    
     public Librarian()
    {
        super();
    }
    
    public Librarian(String username,String password)
    {
        super(username,password);
    }

    @Override
    public boolean canLogin() {
return true;     }

    @Override
    public boolean canAddBook() {
return false;    }

    @Override
    public boolean canEditBook() {
return false;    }

    @Override
    public boolean canDeleteBook() {
return false;    }

    @Override
    public boolean canCheckoutBook() {
return true;     }

    @Override
    public boolean canCheckinBook() {
return true;     }

    @Override
    public boolean canAddMember() {
return false;    }

    @Override
    public boolean canEditMember() {
return false;    }

    @Override
    public boolean canDeleteMember() {
return false;    }

    @Override
    public boolean canAddAuthor() {
return false;    }

    @Override
    public boolean canDeleteAuthor() {
return false;    }

    @Override
    public boolean canEditAuthor() {
return false;    }
    
}
