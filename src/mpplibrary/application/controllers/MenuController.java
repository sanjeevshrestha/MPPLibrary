/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mpplibrary.MPPLibrary;
import mpplibrary.MPPLibraryFactory;
import mpplibrary.base.roles.User;
import mpplibrary.helper.DataHelper;
import mpplibrary.helper.LoadWindowFrame;

/**
 *
 * @author 984947
 */
public class MenuController {

    @FXML
    MenuItem menuAddNewMember, menuListMembers, menuMemberReport, menuSearchMember;

    @FXML
    MenuBar mainMenuBar;

    @FXML
    VBox vBoxMainWindow;

    @FXML
    AnchorPane mainFramePane;

    @FXML
    Menu mnuMainLibrary, mnuMainMember, mnuMainBook, mnuMainAdmin;

    protected Stage primaryStage;

//    protected MemberController memberController;
    protected LoadWindowFrame windowFrame;

    @FXML
    protected void onMenuAddMemberSelected(ActionEvent event) {
        windowFrame.setSceneAddMember();
    }

    @FXML
    protected void onMenuListMembersSelected(ActionEvent event) {
        windowFrame.setSceneListMembers();
    }

    @FXML
    protected void onMenuMemberReportSelected(ActionEvent event) {
        System.out.println("OnMenuMemberReportSelected");
    }

    @FXML
    protected void onMenuSearchMemberSelected(ActionEvent event) {
        System.out.println("OnSearchMemeberSelected");
    }

    @FXML
    protected void onMenuAddBookSelected(ActionEvent event) {
        windowFrame.setSceneAddBook();
    }

    @FXML
    protected void onMenuSearchBookSelected(ActionEvent event) {
        System.out.println("OnSearchBookSelected");
    }

    @FXML
    protected void onMenuBookReportSelected(ActionEvent event) {
        System.out.println("OnBookReportSelected");
    }

    @FXML
    protected void onMenuListBooks(ActionEvent event) {
       // System.out.println("OnListBooksSelected");
//        new BookController().listBooks();
        windowFrame.setSceneListBooks();
    }

    @FXML
    protected void onMenuLogOutSelected(ActionEvent event) {
        ((Node) mainFramePane.getParent()).getScene().getWindow().hide();
        lib.loadLoginWindow();
    }

    @FXML
    protected void onMenuExitSelected(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    protected void onCheckInMenuItemClicked(ActionEvent event) {

    }

    @FXML
    protected void onCheckOutMenuItemClicked(ActionEvent event) {
        windowFrame.setSceneCheckoutList();
    }

    @FXML
    protected void onSearchCheckedOutMenuItemClicked(ActionEvent event) {

    }

    MPPLibrary lib;

    public void initialize(Stage primaryStage, MPPLibrary lib) {
//       vBoxMainWindow.getChildren().add(1, mainMenuBar);
//        memberController = new MemberController(mainFramePane);
        this.lib = lib;
        User u = MPPLibraryFactory.getLoggedInUser();
        mnuMainAdmin.setDisable(true);
        mnuMainBook.setDisable(true);
        mnuMainLibrary.setDisable(true);
        mnuMainMember.setDisable(true);

        if (u.canManageAdmin()) {
            mnuMainAdmin.setDisable(false);
        }

        if (u.canManageBook()) {
            mnuMainBook.setDisable(false);

        }

        if (u.canManageMember()) {
            mnuMainMember.setDisable(false);
        }

        if (u.canManageLibrary()) {
            mnuMainLibrary.setDisable(false);
        }

        windowFrame = LoadWindowFrame.getInstance(mainFramePane);
        this.primaryStage = primaryStage;
    }

    @FXML
    protected void onAddNewUserMenuItemSelected(ActionEvent event) {
        windowFrame.setSceneAddUser();
    }

    @FXML
    protected void onListUserMenuItemClicked(ActionEvent event) {
        windowFrame.setSceneListUsers();
    }
}
