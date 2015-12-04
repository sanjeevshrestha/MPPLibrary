/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
        System.out.println("OnListBooksSelected");
//        new BookController().listBooks();
        windowFrame.setSceneListBooks();
    }

    @FXML
    protected void onMenuLogOutSelected(ActionEvent event) {
        System.out.println("OnLogoutSelected");
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

    public void initialize(Stage primaryStage) {
//       vBoxMainWindow.getChildren().add(1, mainMenuBar);
//        memberController = new MemberController(mainFramePane);
        windowFrame = new LoadWindowFrame(mainFramePane);
    }
    
    @FXML
    protected void onAddNewUserMenuItemSelected(ActionEvent event){
        windowFrame.setSceneAddUser();
    }
    
    @FXML
    protected void onListUserMenuItemClicked(ActionEvent event){
        windowFrame.setSceneListUsers();
    }
}
