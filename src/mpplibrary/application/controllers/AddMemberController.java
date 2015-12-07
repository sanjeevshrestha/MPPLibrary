/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import mpplibrary.application.models.MemberModel;
import mpplibrary.rulesets.RuleException;
import mpplibrary.rulesets.RuleSet;
import mpplibrary.rulesets.RuleSetFactory;

/**
 *
 * @author user
 */
public class AddMemberController {

    @FXML
    TextField txtFirstName, txtLastName, txtAddress, txtCity, txtState, txtZip, txtPhone, txtEmail;

    @FXML
    Label errorLabel;

    private String memberId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;

    private ListMembersController listMembersController;

    private Stage dialogStage;

    public void initialize() {

        Tooltip nameToolTip = new Tooltip("Name must not be empty");
        txtFirstName.setTooltip(nameToolTip);
    }

    @FXML
    protected void onAddMemberClicked() {

        this.firstName = txtFirstName.getText();
        this.lastName = txtLastName.getText();
        this.address = txtAddress.getText();
        this.city = txtCity.getText();
        this.state = txtState.getText();
        this.zip = txtZip.getText();
        this.phone = txtPhone.getText();
        this.email = txtEmail.getText();

        try {
            RuleSet addressRules = RuleSetFactory.getRuleSet(AddMemberController.this);
            addressRules.applyRules(AddMemberController.this);
            if (MemberModel.getInstance().save(0, firstName, lastName, true, 0.00, email, "", phone, "", address, city, state, zip)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Member Saved");
                alert.setContentText("Successfully added member");
                alert.showAndWait();
                listMembersController.refreshListData();
                this.dialogStage.close();
            } else {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Member could not be saved");
                alert.setContentText("Could not add  member");
                alert.showAndWait();
            }

        } catch (RuleException e) {
            TextField errorTextField = (TextField) e.getErrorObject();
            errorLabel.setText(e.getMessage());
            errorLabel.setStyle("-fx-text-fill:red; -fx-font-size: 14pt; -fx-opacity: 0.6; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
            errorTextField.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
        }
    }

//    public TextField getMemberComponent() {
//        return txtMemberId;
//    }
    public TextField getFirstNameComponent() {
        return txtFirstName;
    }

    public TextField getLastNameComponent() {
        return txtLastName;
    }

    public TextField getAddressComponent() {
        return txtAddress;
    }

    public TextField getCityComponent() {
        return txtCity;
    }

    public TextField getStateComponent() {
        return txtState;
    }

    public TextField getZipComponent() {
        return txtZip;
    }

    public TextField getPhoneComponent() {
        return txtPhone;
    }

    public TextField getEmailComponent() {
        return txtEmail;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setMemberController(ListMembersController memberListController, Stage dialogStage) {
        this.listMembersController = memberListController;
        this.dialogStage = dialogStage;
    }
}
