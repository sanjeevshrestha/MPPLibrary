/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
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
import mpplibrary.application.models.MemberModel;
import mpplibrary.base.Book;
import mpplibrary.base.Member;
import mpplibrary.helper.LoadWindowFrame;

/**
 *
 * @author user
 */
public class ListMembersController {

    @FXML
    TextField txtSearchQuery;

    @FXML
    TableView tblViewMembers;

    @FXML
    AnchorPane anchorPaneMemberPreview;

    @FXML
    TableColumn<Member, Object> tblColumnMemberId;

    @FXML
    TableColumn<Member, Object> tblColumnFName;

    @FXML
    TableColumn<Member, Object> tblColumnLName;

    @FXML
    TableColumn<Member, Object> tblColumnAmountDue;

    @FXML
    Label fxTxtEmail, fxTxtPhone, fxTxtAddress, fxMemberNameTitle;

    @FXML
    Button fxBtnNewMember, fxBtnDeleteMember;

    private ObservableList<Member> membersList;

    private ObservableList<Member> filteredMembersList;

    private MemberModel memberModel;
    private LoadWindowFrame windowFrame;

    private int selectedMemberPosition;

    @FXML
    public void initialize() {
        selectedMemberPosition = -1;
        membersList = FXCollections.observableArrayList();
        filteredMembersList = FXCollections.observableArrayList();
        memberModel = new MemberModel();

        membersList.addAll(memberModel.getMembers());

        onTableRowClicked();
        tblColumnMemberId.setCellValueFactory(new PropertyValueFactory<Member, Object>("ID"));
        tblColumnMemberId.setCellFactory(cellFactory);

        tblColumnFName.setCellValueFactory(new PropertyValueFactory<Member, Object>("fullname"));
        tblColumnFName.setCellFactory(cellFactory);
//        tblColumnLName.setCellValueFactory(new PropertyValueFactory<Member, Object>("lastname"));
//        tblColumnLName.setCellFactory(cellFactory);
        tblColumnAmountDue.setCellValueFactory(new PropertyValueFactory<Member, Object>("amount_due"));
        tblColumnAmountDue.setCellFactory(cellFactory);

        filteredMembersList.addAll(membersList);
        tblViewMembers.setItems(filteredMembersList);
        txtSearchQuery.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFilteredData();
            }

        });

    }

    @FXML
    public void onNewMemberButtonClick(ActionEvent event) {
        LoadWindowFrame lf = LoadWindowFrame.getInstance();
        lf.setSceneAddMember();
    }

    @FXML
    public void onDeleteMemberButtonClicked(ActionEvent event) {
        if (selectedMemberPosition >= 0) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Member");
            Member selectedMember = filteredMembersList.get(selectedMemberPosition);
            alert.setHeaderText("Are you sure you want to remove " + selectedMember.getFullname() + "?");

            ButtonType buttonTypeConfirm = new ButtonType("Ok", ButtonData.APPLY);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeConfirm, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeConfirm) {

                if (memberModel.delete(filteredMembersList.get(selectedMemberPosition).getID())) {
                    refreshListData();
                } else {
                    System.out.println("Error in deleting member");
                }
                alert.close();
            }

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Member");
            alert.setHeaderText("Select a member to delete");
            alert.getButtonTypes().clear();
            ButtonType buttonTypeCancel = new ButtonType("Ok", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().addAll(buttonTypeCancel);
            alert.show();
        }
    }

    private void updateFilteredData() {
        selectedMemberPosition = -1;
        filteredMembersList.clear();
        anchorPaneMemberPreview.setVisible(false);
        for (Member member : membersList) {
            if (matchesFilter(member)) {
                filteredMembersList.add(member);
            }
        }

        reapplyTableSortOrder();
    }

    private boolean matchesFilter(Member member) {
        String queryText = txtSearchQuery.getText();
        if (queryText == null || queryText.isEmpty()) {

            return true;
        }

        String lowerCaseFilterString = queryText.toLowerCase();

        if (member.getFirstname().toLowerCase().contains(lowerCaseFilterString) || member.getLastname().toLowerCase().contains(lowerCaseFilterString)) {
            return true;
        }

        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<Book, ?>> sortOrder = new ArrayList<>(tblViewMembers.getSortOrder());
        tblViewMembers.getSortOrder().clear();
        tblViewMembers.getSortOrder().addAll(sortOrder);
    }

    Callback<TableColumn<Member, Object>, TableCell<Member, Object>> cellFactory;

    private void onTableRowClicked() {
        cellFactory
                = new Callback<TableColumn<Member, Object>, TableCell<Member, Object>>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        MyStringTableCell cell = new MyStringTableCell();
                        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

                        return cell;
                    }
                };
    }

    void refreshListData() {
        membersList.clear();
        membersList.addAll(memberModel.getMembers());
        filteredMembersList.clear();
        filteredMembersList.addAll(membersList);
        tblViewMembers.setItems(filteredMembersList);
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
            TableCell c = (TableCell) t.getSource();
            int index = c.getIndex();
            anchorPaneMemberPreview.setVisible(true);

            Member m = filteredMembersList.get(index);
            fxMemberNameTitle.setText(m.getFirstname() + " " + m.getLastname());
            fxTxtEmail.setText(m.getEmail());
            fxTxtPhone.setText(m.getPhone());
            fxTxtAddress.setText(m.getFullAddress());

            selectedMemberPosition = index;

        }
    }
}
