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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import mpplibrary.application.models.BookModel;
import mpplibrary.base.Book;
import mpplibrary.base.Member;
import mpplibrary.helper.LoadWindowFrame;

/**
 *
 * @author user
 */
public class ListBooksController {

    @FXML
    TextField txtSearchQuery;

    @FXML
    TableView tblViewBooks;

    @FXML
    AnchorPane anchorPaneBookPreview;

    @FXML
    TableColumn<Book, String> tblColumnIsbn;

    @FXML
    TableColumn<Book, String> tblColumnTitle;

    @FXML
    TableColumn<Book, String> tblColumnAvailable;

    @FXML
    Button fxBtnAddNewBook, fxBtnDeleteBook;

    private ObservableList<Book> booksList;

    private ObservableList<Book> filteredBooksList;

    private BookModel bookModel;

    private int selectedBookPosition;

    @FXML
    public void initialize() {
        selectedBookPosition = -1;
        booksList = FXCollections.observableArrayList();
        filteredBooksList = FXCollections.observableArrayList();
        bookModel = new BookModel();

        booksList.addAll(bookModel.getBooks());
        System.out.println("Books:" + booksList.size());
        onTableRowClicked();

        tblColumnIsbn.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        tblColumnIsbn.setCellFactory(cellFactory);

        tblColumnTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tblColumnTitle.setCellFactory(cellFactory);

        tblColumnAvailable.setCellValueFactory(new PropertyValueFactory<Book, String>("available"));
        tblColumnAvailable.setCellFactory(cellFactory);

        filteredBooksList.addAll(booksList);
        tblViewBooks.setItems(filteredBooksList);
        txtSearchQuery.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFilteredData();
            }

        });

    }

    private void updateFilteredData() {
        filteredBooksList.clear();

        for (Book book : booksList) {
            if (matchesFilter(book)) {
                filteredBooksList.add(book);
            }
        }

        reapplyTableSortOrder();
    }

    private boolean matchesFilter(Book book) {
        String queryText = txtSearchQuery.getText();
        if (queryText == null || queryText.isEmpty()) {
            // No filter --> Add all.
            return true;
        }

        String lowerCaseFilterString = queryText.toLowerCase();

        if (book.getTitle().toLowerCase().contains(lowerCaseFilterString)) {
            return true;
        }

        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<Book, ?>> sortOrder = new ArrayList<>(tblViewBooks.getSortOrder());
        tblViewBooks.getSortOrder().clear();
        tblViewBooks.getSortOrder().addAll(sortOrder);
    }

    Callback<TableColumn<Book, String>, TableCell<Book, String>> cellFactory;

    private void onTableRowClicked() {
        cellFactory
                = new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
            @Override
            public TableCell call(TableColumn p) {
                MyStringTableCell cell = new MyStringTableCell();
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

                return cell;
            }
        };
    }

    void refreshListData() {
        booksList.clear();
        booksList.addAll(bookModel.getBooks());
        filteredBooksList.clear();
        filteredBooksList.addAll(booksList);
        tblViewBooks.setItems(filteredBooksList);
        txtSearchQuery.setText("");
    }

    class MyStringTableCell extends TableCell<Book, Object> {

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
            System.out.println("id = " + filteredBooksList.get(index).getISBN());
            System.out.println("name = " + filteredBooksList.get(index).getTitle());
            selectedBookPosition = index;

        }
    }

    @FXML
    private void onBtnDeleteBookCLicked() {
        if (selectedBookPosition >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Book");
            Book selectedBook = filteredBooksList.get(selectedBookPosition);
            alert.setHeaderText("Are you sure you want to remove " + selectedBook.getTitle());

            ButtonType buttonTypeConfirm = new ButtonType("Ok", ButtonBar.ButtonData.APPLY);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeConfirm, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeConfirm) {

                if (bookModel.delete(filteredBooksList.get(selectedBookPosition).getID())) {

                    refreshListData();
                } else {
                    System.out.println("Error in deleteing member");
                }
                alert.close();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Member");
            alert.setHeaderText("Select a member to delete");
            alert.getButtonTypes().clear();
            ButtonType buttonTypeCancel = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().addAll(buttonTypeCancel);
            alert.show();
        }
    }

    @FXML
    private void onBtnAddNewBookClicked() {
        LoadWindowFrame lf = LoadWindowFrame.getInstance();
        lf.setSceneAddBook();
    }
}
