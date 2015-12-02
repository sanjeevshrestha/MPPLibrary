/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.helper;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import mpplibrary.MPPLibrary;
import mpplibrary.application.controllers.AddMemberController;
import mpplibrary.application.controllers.BookController;

/**
 *
 * @author user
 */
public class LoadWindowFrame {

    private final AnchorPane mainFramePane;

    public LoadWindowFrame(AnchorPane mainFramePane) {
        this.mainFramePane = mainFramePane;
    }

    public void setSceneAddMember() {
        try {
            mainFramePane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/AddMember.fxml"));
            AnchorPane pane = loader.load();
            mainFramePane.getChildren().add(pane);
            ((AddMemberController) loader.getController()).initialize();
//              initValidators();

        } catch (IOException ex) {
            Logger.getLogger(MPPLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSceneAddBook() {

        AnchorPane pane;
        try {
            mainFramePane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/AddBook.fxml"));
            pane = loader.load();
            mainFramePane.getChildren().add(pane);
            ((BookController) loader.getController()).initialize();
        } catch (IOException ex) {
            Logger.getLogger(LoadWindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void setSceneListBooks(){
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/ListBooks.fxml"));
            AnchorPane pane = loader.load();
            mainFramePane.getChildren().clear();
            mainFramePane.getChildren().add(pane);
            
        } catch (IOException ex) {
            Logger.getLogger(LoadWindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
