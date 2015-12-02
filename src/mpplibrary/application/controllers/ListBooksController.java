/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import mpplibrary.application.models.BookModel;

/**
 *
 * @author user
 */
public class ListBooksController {

    @FXML
    TextField txtSearchQuery;

    @FXML
    ListView listViewBooks;

    @FXML
    AnchorPane anchorPaneBookPreview;

    private ObservableList<BookModel> booksList;

    public void initialize() {
        booksList = FXCollections.observableArrayList();
//        bookList = getBookListFromDB();
        txtSearchQuery.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterList(oldValue, newValue);
            }

        });
    }

    private void filterList(String oldValue, String newValue) {
        if (oldValue != null && (newValue.length() < oldValue.length())) {
            listViewBooks.setItems(booksList);
        }
        String value = newVal.toUpperCase();
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for (Object entry : list.getItems()) {
            boolean match = true;
            String entryText = (String) entry;
            if (!entryText.toUpperCase().contains(value)) {
                match = false;
                break;
            }
            if (match) {
                subentries.add(entryText);
            }
        }
        list.setItems(subentries);
    }

}
}
