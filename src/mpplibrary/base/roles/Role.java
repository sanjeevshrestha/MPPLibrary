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
public interface Role {


    boolean canManageBook();
    boolean canManageLibrary();
    boolean canManageMember();
    boolean canManageAdmin();


}
