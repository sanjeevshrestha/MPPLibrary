/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 *
 * @author user
 */
public class AddMemberController {

    @FXML
    TextField txtMemberId, txtFirstName, txtLastName, txtAddress, txtCity, txtState, txtZip, txtPhone, txtEmail;
    String memberId, firstName, lastName, address, city, state, zip, phone,email;
    
    public void initialize() {
        Tooltip toolTip = new Tooltip("Member id must be numeric");
        txtMemberId.setTooltip(toolTip);
        
        Tooltip nameToolTip = new Tooltip("Name must not be empty");
        txtFirstName.setTooltip(nameToolTip);
    }
    
    @FXML
    protected void onAddMemberClicked(){
        memberId = txtMemberId.getText();
        firstName = txtFirstName.getText();
        lastName = txtLastName.getText();
        address = txtAddress.getText();
        city = txtCity.getText();
        state = txtState.getText();
        zip = txtZip.getText();
        phone = txtPhone.getText();
        email = txtEmail.getText();
    }
    
}
