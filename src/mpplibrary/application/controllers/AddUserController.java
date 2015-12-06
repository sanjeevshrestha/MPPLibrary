/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mpplibrary.MPPLibraryFactory;
import mpplibrary.application.models.UserModel;
import mpplibrary.base.roles.User;

/**
 *
 * @author Anish
 */
public class AddUserController {

    @FXML
    TextField usernameTxt, firstNameTxt, lastNameTxt, phoneTxt, emailTxt, addressTxt, cityTxt, stateTxt, zipTxt;

    @FXML
    PasswordField passwordTxt;

    @FXML
    ComboBox accountTypeComboBox;

    @FXML
    Button saveBttn, cancelBttn;

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String role;

    private ListUserController listUserController;
    private Stage dialogStage;

    @FXML
    public void initialize() {

        accountTypeComboBox.getItems().addAll("admin", "librarian", "both");
        listUserController = new ListUserController();

    }

    @FXML
    protected void onSaveBttnClicked(ActionEvent event) {
        this.username = usernameTxt.getText();
        this.password = passwordTxt.getText();
        this.firstname = firstNameTxt.getText();
        this.lastname = lastNameTxt.getText();
        this.phone = phoneTxt.getText();
        this.email = emailTxt.getText();
        this.address = addressTxt.getText();
        this.city = cityTxt.getText();
        this.state = stateTxt.getText();
        this.zip = zipTxt.getText();
        this.role = accountTypeComboBox.getValue().toString();

        try {
            User u = MPPLibraryFactory.getLoggedInUser();
            if (UserModel.getInstance().save(0, firstname, lastname, email, username, password, role, new Date(), u.getID(), new Date(), u.getID(), true, phone, address, city, state, zip)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Saved");
                alert.setContentText("Successfully added user");
                alert.showAndWait();
                listUserController.refreshListData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCancelBttnClicked(ActionEvent event) {

    }

    public void setUserlistController(ListUserController userListController, Stage dialogStage) {
        this.listUserController = userListController;
        this.dialogStage = dialogStage;
    }

}
