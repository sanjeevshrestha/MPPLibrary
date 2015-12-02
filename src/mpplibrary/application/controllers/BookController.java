/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mpplibrary.rulesets.RuleException;
import mpplibrary.rulesets.RuleSet;
import mpplibrary.rulesets.RuleSetFactory;

/**
 *
 * @author 984970
 */
public class BookController {

    private String isbn;
    private String noOfCopies;
    private String bookTitle;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String credentials;
    private String bio;

    @FXML
    TextField txtIsbn;
    @FXML
    TextField txtNoOfCopies;
    @FXML
    TextField txtBookTitle;
    @FXML
    TextField txtFirstName;
    @FXML
    TextField txtLastName;
    @FXML
    TextField txtStreet;
    @FXML
    TextField txtCity;
    @FXML
    TextField txtState;
    @FXML
    TextField txtZip;
    @FXML
    TextField txtPhone;
    @FXML
    TextField txtCredentials;
    @FXML
    TextField txtBio;
    @FXML
    Label errorLabel;

    @FXML
    private Button btnAddMoreBookAuthor;

    @FXML
    private VBox mBoxPaneAuthor;

    public void listBooks() {

    }

    @FXML
    protected void onAddMoreAuthorClicked(ActionEvent event) {
        loadAuthor();

    }

    private void loadAuthor() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/Author.fxml"));
        Pane pane;
        int childSize = mBoxPaneAuthor.getChildren().size();
        if (childSize > 0) {
            AnchorPane childPane = (AnchorPane) (mBoxPaneAuthor.getChildren().get(childSize - 1));
            TextField txtFirstName = (TextField) childPane.lookup("#txtFirstName");
            TextField txtLastName = (TextField) childPane.lookup("#txtLastName");
            Alert alert = new Alert(Alert.AlertType.NONE);
            if (txtFirstName != null && txtFirstName.getText().trim().length() == 0) {
                alert.setContentText("Add author first name");
//                alert.showAndWait();
                return;
            } else if (txtLastName != null && txtLastName.getText().trim().length() == 0) {
                alert.setContentText("Add author last name");
//                alert.showAndWait();
                return;
            }
        }
        try {
            pane = loader.load();
            mBoxPaneAuthor.getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initialize() {
        loadAuthor();
    }

    @FXML
    protected void onBtnAddBookClicked(ActionEvent event) {
        this.isbn = txtIsbn.getText();
        this.noOfCopies = txtNoOfCopies.getText();
        this.bookTitle = txtBookTitle.getText();
        this.firstName = txtFirstName.getText();
        this.lastName = txtLastName.getText();
        this.street = txtStreet.getText();
        this.city = txtCity.getText();
        this.state = txtState.getText();
        this.zip = txtZip.getText();
        this.phone = txtPhone.getText();
        this.credentials = txtCredentials.getText();
        this.bio = txtBio.getText();

        try {
            RuleSet addressRules = RuleSetFactory.getRuleSet(BookController.this);
            addressRules.applyRules(BookController.this);
        } catch (RuleException e) {
            TextField errorTextField = (TextField) e.getErrorObject();
            errorLabel.setText(e.getMessage());
            errorLabel.setStyle("-fx-text-fill:red; -fx-font-size: 14pt; -fx-opacity: 0.6; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
            errorTextField.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
        }
    }

    public TextField getTxtIsbn() {
        return txtIsbn;
    }

    public TextField getTxtNoOfCopies() {
        return txtNoOfCopies;
    }

    public TextField getTxtBookTitle() {
        return txtBookTitle;
    }

    public TextField getTxtFirstName() {
        return txtFirstName;
    }

    public TextField getTxtLastName() {
        return txtLastName;
    }

    public TextField getTxtStreet() {
        return txtStreet;
    }

    public TextField getTxtCity() {
        return txtCity;
    }

    public TextField getTxtState() {
        return txtState;
    }

    public TextField getTxtZip() {
        return txtZip;
    }

    public TextField getTxtPhone() {
        return txtPhone;
    }

    public TextField getTxtCredentials() {
        return txtCredentials;
    }

    public TextField getTxtBio() {
        return txtBio;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
