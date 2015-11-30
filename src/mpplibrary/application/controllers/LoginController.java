/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author 984947
 */
public class LoginController {

    @FXML
    TextField txtUsername;

    @FXML
    TextField txtPassword;

    @FXML
    protected void handleBtnLoginAction(ActionEvent event) {
        System.out.println("LoginClicked!!");
    }
}
