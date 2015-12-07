/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import java.util.ArrayList;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import mpplibrary.application.models.UserModel;
import mpplibrary.base.roles.User;
import mpplibrary.helper.LoadWindowFrame;

/**
 *
 * @author Anish
 */
public class ListUserController {

    @FXML
    TextField txtSearchQuery;

    @FXML
    TableView tblViewUsers;

    @FXML
    Label fxTxtEmail, fxTxtAddress, fxTxtPhone, fxLastLoggedIn, fxUserNameTitle, fxTxtUsername;

    @FXML
    AnchorPane anchorPaneUserPreview;

    @FXML
    TableColumn<User, Object> tblColumnUserId;

    @FXML
    TableColumn<User, Object> tblColumnFullName;

    @FXML
    TableColumn<User, Object> tblColumnStatus;

    private ObservableList<User> usersList;

    private ObservableList<User> filteredUsersList;

    private UserModel userModel;

    Callback<TableColumn<User, Object>, TableCell<User, Object>> cellFactory;

    private int selectedUserPosition;

    @FXML
    public void initialize() {

        selectedUserPosition = -1;
        usersList = FXCollections.observableArrayList();
        filteredUsersList = FXCollections.observableArrayList();
        userModel = new UserModel();

        usersList.addAll(userModel.getUsers());

        onTableRowClicked();

        tblColumnUserId.setCellValueFactory(new PropertyValueFactory<User, Object>("ID"));
        tblColumnUserId.setCellFactory(cellFactory);

        tblColumnFullName.setCellValueFactory(new PropertyValueFactory<User, Object>("fullname"));
        tblColumnFullName.setCellFactory(cellFactory);

        tblColumnStatus.setCellValueFactory(new PropertyValueFactory<User, Object>("active"));
        tblColumnStatus.setCellFactory(cellFactory);

        filteredUsersList.addAll(usersList);
        tblViewUsers.setItems(filteredUsersList);
        txtSearchQuery.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFilteredData();
            }

        });

    }

    @FXML
    public void onDeleteBttnClicked(ActionEvent event) {
        if (selectedUserPosition >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete User");
            User selectedUser = filteredUsersList.get(selectedUserPosition);
            alert.setHeaderText("Are you sure you want to remove " + selectedUser.getFullname() + "?");

            ButtonType buttonTypeConfirm = new ButtonType("Ok", ButtonBar.ButtonData.APPLY);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeConfirm, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeConfirm) {

                if (userModel.delete(filteredUsersList.get(selectedUserPosition).getID())) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("User Saved");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully saved user");

                    alert.showAndWait();
                    refreshListData();
                } else {
                    System.out.println("Error in deleting User");
                }
                alert.close();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete User");
            alert.setHeaderText("Select User to Delete");
            alert.getButtonTypes().clear();
            ButtonType buttonTypeCancel = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().addAll(buttonTypeCancel);
            alert.show();
        }
    }

    @FXML
    public void onAddNewUserBttnClicked(ActionEvent event) {
        LoadWindowFrame lf = LoadWindowFrame.getInstance();
        lf.setSceneAddUser();
    }

    private void updateFilteredData() {
        selectedUserPosition = -1;
        filteredUsersList.clear();
        anchorPaneUserPreview.setVisible(false);
        for (User user : usersList) {
            if (matchesFilter(user)) {
                filteredUsersList.add(user);
            }
        }

        reapplyTableSortOrder();
    }

    private boolean matchesFilter(User user) {
        String queryText = txtSearchQuery.getText();
        if (queryText == null || queryText.isEmpty()) {

            return true;
        }

        String lowerCaseFilterString = queryText.toLowerCase();

        if (user.getFirstname().toLowerCase().contains(lowerCaseFilterString) || user.getLastname().toLowerCase().contains(lowerCaseFilterString)) {
            return true;
        }

        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<User, ?>> sortOrder = new ArrayList<>(tblViewUsers.getSortOrder());
        tblViewUsers.getSortOrder().clear();
        tblViewUsers.getSortOrder().addAll(sortOrder);
    }

    private void onTableRowClicked() {
        cellFactory = new Callback<TableColumn<User, Object>, TableCell<User, Object>>() {
            @Override
            public TableCell call(TableColumn p) {
                MyStringTableCell cell = new MyStringTableCell();
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

                return cell;
            }
        };
    }

    void refreshListData() {
        usersList.clear();
        usersList.addAll(userModel.getUsers());
        filteredUsersList.clear();
        filteredUsersList.addAll(usersList);
        tblViewUsers.setItems(filteredUsersList);
        txtSearchQuery.setText("");
    }

    class MyStringTableCell extends TableCell<User, Object> {

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
            selectedUserPosition = index;
            System.out.println("Position:: " + selectedUserPosition);
            anchorPaneUserPreview.setVisible(true);

            User user = filteredUsersList.get(index);
            fxUserNameTitle.setText(user.getFirstname() + " " + user.getLastname());
            fxTxtEmail.setText(user.getEmail());
            fxTxtPhone.setText(user.getPhone());
            fxTxtAddress.setText(user.getFullAddress());

//            fxTxtUsername.setText(user.getUsername());
//                System.out.println(user.getEmail());
        }
    }

}
