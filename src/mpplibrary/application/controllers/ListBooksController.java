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

    private ObservableList<Book> booksList;

    private ObservableList<Book> filteredBooksList;

    private BookModel bookModel;

    @FXML
    public void initialize() {
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

    /**
     * Updates the filteredData to contain all data from the masterData that
     * matches the current filter.
     */
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

    class MyStringTableCell extends TableCell<Book, String> {

        @Override
        public void updateItem(String item, boolean empty) {
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

        }
    }
}
