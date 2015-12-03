/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpplibrary.base.Book;
import mpplibrary.helper.LoadWindowFrame;

/**
 * FXML Controller class
 *
 * @author Anish
 */
public class CheckoutBookController {

    @FXML
    TextField filterStudentTxt, studentIdTxt, bookUniqueIdTxt, landableDaysTxt;

    @FXML
    DatePicker datePicker, dueDatePicker;

    @FXML
    TableView checkedoutBookTable, checkedOutBookDetailsTable, bookAddedTable;
    
    @FXML
    TableColumn studentIDColumn, checkOutDateColumn;
    
    @FXML
    TableColumn bookIdColumn, dueDateColumn, fineColumn;
    
    @FXML
    TableColumn bookUniqueIdColumn, bookTitleColumn, checkOutDueDateColumn;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
    }

    @FXML
    protected void onNewCheckoutBttnClicked(ActionEvent event) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoadWindowFrame.class.getResource("/mpplibrary/views/CheckoutBooks.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Checkout Books");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the add book window into the controller.
//            ((BookController) loader.getController()).initialize();
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAddBookBttnClicked(ActionEvent event) {

    }

    @FXML
    protected void onCheckoutBttnClicked(ActionEvent event) {

    }

}
