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
import javafx.stage.Stage;

/**
 *
 * @author 984947
 */
public class MenuController {

    @FXML
    MenuItem menuAddNewMember, menuListMembers, menuMemberReport, menuSearchMember;

    @FXML
    MenuBar mainMenuBar;

    protected Stage primaryStage;
    
    protected MemberController memberController;

    @FXML
    protected void onMenuAddMemberSelected(ActionEvent event) {

        memberController.setSceneAddMember();

    }

    @FXML
    protected void onMenuListMembersSelected(ActionEvent event) {
        System.out.println("OnListMemeberSelected");
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
        System.out.println("OnMenuAddBookSelected");
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
        new BookController().listBooks();
    }

    @FXML
    protected void onMenuLogOutSelected(ActionEvent event) {
        System.out.println("OnLogoutSelected");
    }

    @FXML
    protected void onMenuExitSelected(ActionEvent event) {
        Platform.exit();
    }

    public void initialize(Stage primaryStage) {
       
        memberController = new MemberController(primaryStage);
    }

}
