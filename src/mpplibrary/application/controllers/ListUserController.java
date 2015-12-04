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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import mpplibrary.base.Member;
import mpplibrary.base.roles.User;

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
    Label fxTxtEmail, fxTxtAddress, fxTxtPhone, fxLastLoggedIn, fxUserNameTitle;

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

    @FXML
    public void initialize() {

        usersList = FXCollections.observableArrayList();
        filteredUsersList = FXCollections.observableArrayList();
        userModel = new UserModel();

        usersList.addAll(userModel.getUsers());

        onTableRowClicked();

        tblColumnUserId.setCellValueFactory(new PropertyValueFactory<User, Object>("ID"));
        tblColumnUserId.setCellFactory(cellFactory);

        tblColumnFullName.setCellValueFactory(new PropertyValueFactory<User, Object>("fullname"));
        tblColumnFullName.setCellFactory(cellFactory);

        tblColumnStatus.setCellValueFactory(new PropertyValueFactory<User, Object>("status"));
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

    private void updateFilteredData() {
        filteredUsersList.clear();

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
                ListUserController.MyStringTableCell cell = new ListUserController.MyStringTableCell();
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new ListUserController.MyEventHandler());

                return cell;
            }
        };
    }

    void refreshListData() {
        usersList.clear();
        usersList.addAll(userModel.getUsers());
        tblViewUsers.setItems(usersList);
        txtSearchQuery.setText("");
    }

    class MyStringTableCell extends TableCell<Member, Object> {

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
            try {
                TableCell c = (TableCell) t.getSource();
                int index = c.getIndex();
                anchorPaneUserPreview.setVisible(true);

                User user = filteredUsersList.get(index);
                fxUserNameTitle.setText(user.getFirstname() + " " + user.getLastname());
                fxTxtEmail.setText(user.getEmail());
                fxTxtPhone.setText(user.getPhone());
                fxTxtAddress.setText(user.getFullAddress());
//                System.out.println(user.getEmail());
            } catch (Exception e) {
            }
        }
    }
}
