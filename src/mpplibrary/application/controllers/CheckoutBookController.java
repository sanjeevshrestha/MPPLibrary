/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import mpplibrary.base.LendableCopy;

/**
 *
 * @author user
 */
public class CheckoutBookController {

    @FXML
    TextField txtStudentID, txtBookUniqueID, txtLendableDays;

    @FXML
    DatePicker dtCheckoutDate;

    @FXML
    TableColumn tblColumnUniqueID, tblColumnBookTitle, tblColumnDueDate;

    @FXML
    Button btnCheckout, btnAddBook,btnCancel;

    private ObservableList<LendableCopy> bookList;

    private ListCheckoutsController listCheckoutController;

    private Stage dialogStage;

    Callback<TableColumn<LendableCopy, Object>, TableCell<LendableCopy, Object>> cellFactory;

    public void setCheckoutListController(ListCheckoutsController controller, Stage dialogStage) {
        this.listCheckoutController = controller;
        this.dialogStage = dialogStage;
    }

    @FXML
    public void initialize() {
        bookList = FXCollections.observableArrayList();
        tblColumnUniqueID.setCellValueFactory(new PropertyValueFactory<LendableCopy, Object>("uniqueID"));
        tblColumnUniqueID.setCellFactory(cellFactory);
        tblColumnBookTitle.setCellValueFactory(new PropertyValueFactory<LendableCopy, Object>("title"));
        tblColumnBookTitle.setCellFactory(cellFactory);
        tblColumnDueDate.setCellValueFactory(new PropertyValueFactory<LendableCopy, Object>("dueDate"));
        tblColumnDueDate.setCellFactory(cellFactory);

    }

    @FXML
    public void onBtnCheckoutClicked(ActionEvent e) {

        System.out.println("Clicked Checkout");
    }

    @FXML
    public void onBtnAddBookClicked(ActionEvent e) {

        try {
            String uniqueID = txtBookUniqueID.getText();

            String lendableDays = txtLendableDays.getText();

            LocalDate dtCheckout = dtCheckoutDate.getValue();;
            if (dtCheckout == null) {
                dtCheckout = LocalDate.now();
            }

            LendableCopy cp = new LendableCopy(Integer.parseInt(uniqueID), Integer.parseInt(lendableDays));
            if (cp.isValidCopy()) {
                cp.loadBookDetail();
                cp.calculateDueDate(dtCheckout);
                this.bookList.add(cp);
                System.out.println("isValid");
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Copy");
                alert.setContentText("The Copy you are tying to checkout is invalid. Please check the uniqueID and try again");
                alert.showAndWait();
            }
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
