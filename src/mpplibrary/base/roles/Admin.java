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
public class Admin implements Role {

    @Override
    public boolean canManageBook() {
        return true;
    }

    @Override
    public boolean canManageLibrary() {
        return false;
    }

    @Override
    public boolean canManageMember() {
        return true;
    }

    @Override
    public boolean canManageAdmin() {
        return true;
    }

}
