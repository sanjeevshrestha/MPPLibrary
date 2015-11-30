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

    boolean canLogin();

    boolean canAddBook();

    boolean canEditBook();

    boolean canDeleteBook();

    boolean canCheckoutBook();

    boolean canCheckinBook();

    boolean canAddMember();

    boolean canEditMember();

    boolean canDeleteMember();

    boolean canAddAuthor();

    boolean canDeleteAuthor();

    boolean canEditAuthor();

}
