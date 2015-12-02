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

/**
 *
 * @author 984970
 */
public class BookController {

    @FXML
    private TextField txtisbn;
    private TextField txtNoOfCopies;
    private TextField txtBookTitle;
    private TextField txtFirstName;
    private TextField txtLastName;
    private TextField txtStreet;
    private TextField txtCity;
    private TextField txtState;
    private TextField txtZip;
    private TextField txtPhone;
    private TextField txtCredentials;
    private TextField txtBio;
    private Label errorLabel;

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

    }

}
