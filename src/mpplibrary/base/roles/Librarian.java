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
public class Librarian implements Role {

    @Override
    public boolean canManageBook() {
        return false;
    }

    @Override
    public boolean canManageLibrary() {
        return true;
    }

    @Override
    public boolean canManageMember() {
        return false;
    }

    @Override
    public boolean canManageAdmin() {
        return false;
    }

   

}
