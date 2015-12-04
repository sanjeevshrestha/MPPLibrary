/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mpplibrary.application.models.MemberModel;
import mpplibrary.rulesets.RuleException;
import mpplibrary.rulesets.RuleSet;
import mpplibrary.rulesets.RuleSetFactory;

/**
 *
 * @author 984947
 */
public class AddBookController {

    @FXML
    private TextField fxTxtIsbn, fxTxtTitle;

    @FXML
    private Label fxLabelError;
    @FXML
    private TextArea fxTxtDescription;

    private String isbn, title, description;

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
            TextField errorTextField = (TextField) e.getErrorObject();
            getFxLabelError().setText(e.getMessage());
            getFxLabelError().setStyle("-fx-text-fill:red; -fx-font-size: 14pt; -fx-opacity: 0.6; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
            errorTextField.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
        }
    }

    @FXML
    protected void onBtnCancelClicked(ActionEvent event) {

    }

    /**
     * @return the isbn
     */
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

}
