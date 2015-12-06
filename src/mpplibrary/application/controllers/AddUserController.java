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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mpplibrary.MPPLibraryFactory;
import mpplibrary.application.models.UserModel;
import mpplibrary.base.roles.User;
import mpplibrary.rulesets.RuleException;
import mpplibrary.rulesets.RuleSet;
import mpplibrary.rulesets.RuleSetFactory;

/**
 *
 * @author Anish
 */
public class AddUserController {

    @FXML
    private TextField usernameTxt;
    @FXML
    private TextField firstNameTxt;
    @FXML
    private TextField lastNameTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private TextField cityTxt;
    @FXML
    private TextField stateTxt;
    @FXML
    private TextField zipTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Label errorLabel;

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
            RuleSet addressRules = RuleSetFactory.getRuleSet(AddUserController.this);
            addressRules.applyRules(AddUserController.this);
            User u = MPPLibraryFactory.getLoggedInUser();
            if (UserModel.getInstance().save(0, firstname, lastname, email, username, password, role, new Date(), u.getID(), new Date(), u.getID(), true, phone, address, city, state, zip)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Saved");
                alert.setContentText("Successfully added user");
                alert.showAndWait();
                listUserController.refreshListData();
            }
        } catch (RuleException e) {
            TextField errorTextField = (TextField) e.getErrorObject();
            errorLabel.setText(e.getMessage());
            errorLabel.setStyle("-fx-text-fill:red; -fx-font-size: 14pt; -fx-opacity: 0.6; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
            errorTextField.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
//            e.printStackTrace();
        }
    }

    @FXML
    protected void onCancelBttnClicked(ActionEvent event) {

    }

    public TextField getUsernameTxt() {
        return usernameTxt;
    }

    public void setUsernameTxt(TextField usernameTxt) {
        this.usernameTxt = usernameTxt;
    }

    public TextField getFirstNameTxt() {
        return firstNameTxt;
    }

    public void setFirstNameTxt(TextField firstNameTxt) {
        this.firstNameTxt = firstNameTxt;
    }

    public TextField getLastNameTxt() {
        return lastNameTxt;
    }

    public void setLastNameTxt(TextField lastNameTxt) {
        this.lastNameTxt = lastNameTxt;
    }

    public TextField getPhoneTxt() {
        return phoneTxt;
    }

    public void setPhoneTxt(TextField phoneTxt) {
        this.phoneTxt = phoneTxt;
    }

    public TextField getEmailTxt() {
        return emailTxt;
    }

    public void setEmailTxt(TextField emailTxt) {
        this.emailTxt = emailTxt;
    }

    public TextField getAddressTxt() {
        return addressTxt;
    }

    public void setAddressTxt(TextField addressTxt) {
        this.addressTxt = addressTxt;
    }

    public TextField getCityTxt() {
        return cityTxt;
    }

    public void setCityTxt(TextField cityTxt) {
        this.cityTxt = cityTxt;
    }

    public TextField getStateTxt() {
        return stateTxt;
    }

    public void setStateTxt(TextField stateTxt) {
        this.stateTxt = stateTxt;
    }

    public TextField getZipTxt() {
        return zipTxt;
    }

    public void setZipTxt(TextField zipTxt) {
        this.zipTxt = zipTxt;
    }

    public PasswordField getPasswordTxt() {
        return passwordTxt;
    }

    public void setPasswordTxt(PasswordField passwordTxt) {
        this.passwordTxt = passwordTxt;
    }

    public void setUserlistController(ListUserController userListController, Stage dialogStage) {
        this.listUserController = userListController;
        this.dialogStage = dialogStage;
    }

}
