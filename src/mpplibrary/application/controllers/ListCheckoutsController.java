/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import mpplibrary.application.models.CheckoutModel;
import mpplibrary.base.CheckoutRecord;
import mpplibrary.helper.LoadWindowFrame;

/**
 * FXML Controller class
 *
 * @author Anish
 */
public class ListCheckoutsController {

    @FXML
    TextField txtSearchQuery;

    @FXML
    TableView tblCheckedoutList;

    @FXML
    TableColumn tblColumnID, tblColumnName, tblColumnCheckoutDate, tblColumnBook, tblColumnFine, tblColumnDueDate;

    private ObservableList<CheckoutRecord> checkoutList;

    private ObservableList<CheckoutRecord> filteredCheckoutList;

    private CheckoutModel checkoutModel;

    private int selectedCheckoutsPosition;

    Callback<TableColumn<CheckoutRecord, Object>, TableCell<CheckoutRecord, Object>> cellFactory;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {

        selectedCheckoutsPosition = -1;

        checkoutList = FXCollections.observableArrayList();
        filteredCheckoutList = FXCollections.observableArrayList();
        checkoutModel = new CheckoutModel();

        checkoutList.addAll(checkoutModel.getCheckoutRecords());
        onTableRowClicked();

        tblColumnID.setCellValueFactory(new PropertyValueFactory<CheckoutRecord, Object>("ID"));
        tblColumnID.setCellFactory(cellFactory);

        tblColumnName.setCellValueFactory(new PropertyValueFactory<CheckoutRecord, Object>("membername"));
        tblColumnName.setCellFactory(cellFactory);
        tblColumnCheckoutDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecord, Object>("stringCheckoutdate"));
        tblColumnCheckoutDate.setCellFactory(cellFactory);
        filteredCheckoutList.addAll(checkoutList);
        tblCheckedoutList.setItems(filteredCheckoutList);
        txtSearchQuery.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFilteredData();
            }

        });
    }

    @FXML
    protected void onNewCheckoutBttnClicked(ActionEvent event) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoadWindowFrame.class.getResource("/mpplibrary/views/CheckOutBooks.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Checkouts");
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

    private void onTableRowClicked() {
        cellFactory
                = new Callback<TableColumn<CheckoutRecord, Object>, TableCell<CheckoutRecord, Object>>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        MyStringTableCell cell = new MyStringTableCell();
                        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

                        return cell;
                    }
                };
    }

    private void updateFilteredData() {
        selectedCheckoutsPosition = -1;
        filteredCheckoutList.clear();
        //anchorPaneMemberPreview.setVisible(false);
        for (CheckoutRecord cr : checkoutList) {
            if (matchesFilter(cr)) {
                filteredCheckoutList.add(cr);
            }
        }

        reapplyTableSortOrder();
    }

    private boolean matchesFilter(CheckoutRecord cr) {
        String queryText = txtSearchQuery.getText();
        if (queryText == null || queryText.isEmpty()) {

            return true;
        }

        String lowerCaseFilterString = queryText.toLowerCase();

        if (cr.getCheckedOutBy().getFirstname().toLowerCase().contains(lowerCaseFilterString) || cr.getCheckedOutBy().getLastname().toLowerCase().contains(lowerCaseFilterString)) {
            return true;
        }

        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<CheckoutRecord, ?>> sortOrder = new ArrayList<>(tblCheckedoutList.getSortOrder());
        tblCheckedoutList.getSortOrder().clear();
        tblCheckedoutList.getSortOrder().addAll(sortOrder);
    }

    class MyStringTableCell extends TableCell<CheckoutRecord, Object> {

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
            //  anchorPaneMemberPreview.setVisible(true);

            CheckoutRecord r = filteredCheckoutList.get(index);
//            fxMemberNameTitle.setText(m.getFirstname() + " " + m.getLastname());
//            fxTxtEmail.setText(m.getEmail());
//            fxTxtPhone.setText(m.getPhone());
//            fxTxtAddress.setText(m.getFullAddress());

            selectedCheckoutsPosition = index;

        }
    }

}
