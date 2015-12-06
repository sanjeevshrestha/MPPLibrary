/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import mpplibrary.base.CheckoutRecord;
import mpplibrary.base.CheckoutRecordEntry;
import mpplibrary.base.LendableCopy;
import mpplibrary.base.Member;

/**
 *
 * @author user
 */
public class CheckoutBookController {

    @FXML
    TextField txtMemberID, txtBookUniqueID, txtLendableDays;

    @FXML
    DatePicker dtCheckoutDate;

    @FXML
    TableColumn tblColumnUniqueID, tblColumnBookTitle, tblColumnDueDate;

    @FXML
    Button btnCheckout, btnAddBook, btnCancel;

    @FXML
    TableView tblBooksList;

    private ObservableList<CheckoutRecordEntry> bookList;

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
        onTableRowClicked();
        tblColumnUniqueID.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, Object>("uniqueID"));
        tblColumnUniqueID.setCellFactory(cellFactory);
        tblColumnBookTitle.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, Object>("title"));
        tblColumnBookTitle.setCellFactory(cellFactory);
        tblColumnDueDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, Object>("dueDate"));
        tblColumnDueDate.setCellFactory(cellFactory);

        tblBooksList.setItems(bookList);

    }

    @FXML
    public void onBtnCheckoutClicked(ActionEvent e) {

        try {
            String memberID = txtMemberID.getText();
            Member m = new Member(Integer.parseInt(memberID));
            if (m.isValid()) {
                if (this.bookList.size() > 0) {
                    LocalDate dtCheckout = dtCheckoutDate.getValue();
                    List<CheckoutRecordEntry> entries = new ArrayList<>();
                    entries.addAll(this.bookList);
                    CheckoutRecord r = new CheckoutRecord(m, dtCheckout, entries);
                    r.save();
                    this.dialogStage.close();
                    this.listCheckoutController.refreshListData();
                    
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("No Items to Checkout");
                    alert.setContentText("Please add items to checkout. The checkout list is empty");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Member");
                alert.setContentText("This member does not exist. Please check member id");
                alert.showAndWait();
            }

        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Checkout");
            alert.setContentText("There are error with this checkout request. Please fill in all the fields");
            alert.showAndWait();
        }

    }

    @FXML
    public void onBtnAddBookClicked(ActionEvent e) {

        try {
            String uniqueID = txtBookUniqueID.getText();

            boolean alreadyExists = false;
            for (CheckoutRecordEntry l : this.bookList) {
                if (String.valueOf(l.getBook().getUniqueID()).equals(uniqueID)) {
                    alreadyExists = true;

                }
            }

            if (!alreadyExists) {
                String lendableDays = txtLendableDays.getText();

                LocalDate dtCheckout = dtCheckoutDate.getValue();
                if (dtCheckout == null) {
                    dtCheckout = LocalDate.now();
                }

                LendableCopy l = new LendableCopy(Integer.parseInt(uniqueID));
                l.loadBookDetail();

                CheckoutRecordEntry cp = new CheckoutRecordEntry(l, dtCheckout, Integer.parseInt(lendableDays));
                if (cp.getBook().isValidCopy()) {
                    if (cp.getBook().isAvailable()) {
                        cp.getBook().loadBookDetail();
                        cp.setLendableDays(Integer.parseInt(lendableDays));
                        cp.calculateDueDate();
                        txtBookUniqueID.setText("");
                        txtLendableDays.setText("");

                        this.bookList.add(cp);
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Not Available");
                        alert.setContentText("The Copy you are tying to checkout is not available right now.");
                        alert.showAndWait();
                    }

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Invalid Copy");
                    alert.setContentText("The Copy you are tying to checkout is invalid. Please check the uniqueID and try again");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Duplicate Copy");
                alert.setContentText("The Copy you are tying to checkout already exists in the list. Please try again");
                alert.showAndWait();
            }

        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Checkout Entry");
            alert.setContentText("There are error with this checkout request. Please fill in all the fields");
            alert.showAndWait();
        }

    }

    private void onTableRowClicked() {
        cellFactory
                = new Callback<TableColumn<LendableCopy, Object>, TableCell<LendableCopy, Object>>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        CheckoutBookController.MyStringTableCell cell = new CheckoutBookController.MyStringTableCell();
                        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new CheckoutBookController.MyEventHandler());

                        return cell;
                    }
                };
    }

    class MyStringTableCell extends TableCell<LendableCopy, Object> {

        @Override
        public void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? null : getString());
            setGraphic(null);
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

    class MyEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
            TableCell c = (TableCell) t.getSource();
            int index = c.getIndex();

        }
    }

}
