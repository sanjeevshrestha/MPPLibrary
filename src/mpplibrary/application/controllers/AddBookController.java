/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import mpplibrary.base.Author;
import mpplibrary.base.Book;
import mpplibrary.rulesets.RuleException;
import mpplibrary.rulesets.RuleSet;
import mpplibrary.rulesets.RuleSetFactory;

/**
 *
 * @author 984947
 */
public class AddBookController {
    
    @FXML
    private TextField fxTxtIsbn, fxTxtTitle, fxTxtLendableCopyId;
    
    @FXML
    private Label fxLabelError;
    @FXML
    private TextArea fxTxtDescription;
    
    private String isbn, title, description;
    
    private ObservableList<String> lendableCopiesList;
    
    @FXML
    private TableView fxTblLendableIds;
    
    @FXML
    TableColumn<String, String> fxTblColumnLendableId;
    
    @FXML
    private ComboBox fxComboPublicationType;
    
    public static final String PUBLICATION_TYPE_BOOK = "book";
    
    @FXML
    private Label fxLblIsbn;

    // For adding author
    @FXML
    private TextField fxTxtAuthorFirstName, fxTxtAuthorLastName, fxTxtAuthorEmail, fxTxtAuthorCredentials;
    
    @FXML
    private TextArea fxTxtAuthorBio;
    
    @FXML
    private Button fxBtnAddAuthor;
    
    private String authorFName, authorLName, authorEmail, authorCredentials, authorBio;
    
    private ObservableList<Author> addedAuthorsList;
    
    @FXML
    private TableView fxTblAuthors;
            
    @FXML
    TableColumn<Author, Object> tblColumnAuthorName;
    
    public void setListBooksController(ListBooksController listBooksController, Stage dialogStage) {
        
    }
    
    @FXML
    protected void onBtnSaveClicked(ActionEvent event) {
        isbn = getFxTxtIsbn().getText();
        
        title = getFxTxtTitle().getText();
        description = getFxTxtDescription().getText();
        
        try {
            RuleSet addressRules = RuleSetFactory.getRuleSet(AddBookController.this);
            addressRules.applyRules(AddBookController.this);
//            if(MemberModel.getInstance().save(0, firstName, lastName, true, 0.00, email, "", phone, "", address, city, state, zip)){
//                listMembersController.refreshListData();
//                this.dialogStage.close();
//            }else{
//                System.out.println("Error in saving member");
//            }

        } catch (RuleException e) {
            if (e.getErrorObject() != null) {
                TextField errorTextField = (TextField) e.getErrorObject();
                errorTextField.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
            }
            getFxLabelError().setText(e.getMessage());
            getFxLabelError().setStyle("-fx-text-fill:red; -fx-font-size: 14pt; -fx-opacity: 0.6; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
            
        }
    }
    
    @FXML
    protected void onBtnCancelClicked(ActionEvent event) {
        
    }
    
    @FXML
    public void initialize() {
        lendableCopiesList = FXCollections.observableArrayList();
        addedAuthorsList = FXCollections.observableArrayList();
        
        fxTblColumnLendableId.setCellValueFactory(new Callback<CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<String, String> param) {
                return new SimpleStringProperty(param.getValue());
            }
        });
        
        tblColumnAuthorName.setCellValueFactory(new PropertyValueFactory<Author, Object>("authorFullName"));
        
        getFxComboPublicationType().valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (t1.equalsIgnoreCase(PUBLICATION_TYPE_BOOK)) {
                    fxLblIsbn.setText("ISBN*");
                } else {
                    fxLblIsbn.setText("ISBN");
                }
                System.out.println(t1);
                
            }
        });
        
    }
    
    public String getIsbn() {
        return isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the fxTxtIsbn
     */
    public TextField getFxTxtIsbn() {
        return fxTxtIsbn;
    }

    /**
     * @return the fxTxtTitle
     */
    public TextField getFxTxtTitle() {
        return fxTxtTitle;
    }

    /**
     * @return the fxLabelError
     */
    public Label getFxLabelError() {
        return fxLabelError;
    }

    /**
     * @return the fxTxtDescription
     */
    public TextArea getFxTxtDescription() {
        return fxTxtDescription;
    }

    /**
     * @return the lendableCopiesList
     */
    public ObservableList<String> getLendableCopiesList() {
        return lendableCopiesList;
    }
    
    @FXML
    public void onBtnAddLendableClicked() {
        String lendableCopyId = fxTxtLendableCopyId.getText().trim();
        if (!lendableCopyId.isEmpty()) {
            boolean alreadyExists = false;
            for (String copyId : lendableCopiesList) {
                if (copyId.equalsIgnoreCase(lendableCopyId)) {
                    fxTxtLendableCopyId.clear();
                    alreadyExists = true;
                    break;
                }
            }
            
            if (!alreadyExists) {
                lendableCopiesList.add(lendableCopyId);
                fxTblLendableIds.setItems(lendableCopiesList);
                fxTxtLendableCopyId.clear();
            }
        }
        
    }
    
    @FXML
    public void onBtnAddAuthorClicked() {
        
        authorFName = fxTxtAuthorFirstName.getText();
        authorLName = fxTxtAuthorLastName.getText();
        authorEmail = fxTxtAuthorEmail.getText();
        authorCredentials = fxTxtAuthorCredentials.getText();
        authorBio = fxTxtAuthorBio.getText();
        
        if (authorFName.trim().length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Author firstname is required");
            
            alert.showAndWait();
        } else if (authorLName.trim().length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Author lastname is required");
            
            alert.showAndWait();
        } else {
            Author author = new Author(authorFName, authorLName, authorEmail, authorCredentials, authorBio);
            addedAuthorsList.add(author);
            fxTblAuthors.setItems(addedAuthorsList);
//            tblAddedAuthors.
        }
        
    }

    /**
     * @return the fxComboPublicationType
     */
    public ComboBox getFxComboPublicationType() {
        return fxComboPublicationType;
    }
    
}
