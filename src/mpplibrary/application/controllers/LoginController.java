/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import mpplibrary.MPPLibraryFactory;
import mpplibrary.application.models.UserModel;
import mpplibrary.base.roles.User;

import mpplibrary.interfaces.OnLoginSuccessListener;

/**
 *
 * @author 984947
 */
public class LoginController {

    @FXML
    TextField txtUsername;

    @FXML
    TextField txtPassword;

    String userName, password;

    @FXML
    Label txtLoginInfo;

    private OnLoginSuccessListener onLoginSuccessListener;

    @FXML
    protected void handleBtnLoginAction(ActionEvent event) {
        userName = txtUsername.getText();
        password = txtPassword.getText();
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            txtLoginInfo.setText("Username or password cannot be empty");
            txtLoginInfo.setVisible(true);
            return;
        }

        UserModel um = UserModel.getInstance();
        if (um.login(userName, password)) {
            loadHomeScreen();
            User loggedUser = MPPLibraryFactory.getLoggedInUser();

        } else {

            txtLoginInfo.setText("Invalid Username or Password");
            txtLoginInfo.setVisible(true);
        }

    }

    @FXML
    protected void handleInputMethodActive(KeyEvent event) {
        txtLoginInfo.setVisible(false);
    }

    private void loadHomeScreen() {
        onLoginSuccessListener.onLoginSuccess();

    }

    @FXML
    public void initialize(OnLoginSuccessListener onLoginSuccessListener) {
        this.onLoginSuccessListener = onLoginSuccessListener;
    }

}
