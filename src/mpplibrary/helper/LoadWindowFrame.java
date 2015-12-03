/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.helper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mpplibrary.application.controllers.AddBookController;
import mpplibrary.application.controllers.AddMemberController;
import mpplibrary.application.controllers.ListBooksController;
import mpplibrary.application.controllers.ListMembersController;

/**
 *
 * @author user
 */
public class LoadWindowFrame {

    private final AnchorPane mainFramePane;

    private static LoadWindowFrame instance;

    public LoadWindowFrame(AnchorPane mainFramePane) {

        this.mainFramePane = mainFramePane;
    }

    public static LoadWindowFrame getInstance(AnchorPane mainFramePane) {

        instance = new LoadWindowFrame(mainFramePane);

        return instance;
    }

    public static LoadWindowFrame getInstance() {

        return instance;
    }

    public void setSceneAddMember() {
        ListMembersController memberListController = setSceneListMembers();
        popUpAddMemberScene(memberListController);
    }

    public void setSceneAddBook() {
        ListBooksController listBooksController = setSceneListBooks();
        popUpAddBookScene(listBooksController);

    }

    private void popUpAddBookScene(ListBooksController listBooksController) {
        AnchorPane pane;
        try {
//            mainFramePane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/AddBook2.fxml"));
            pane = loader.load();
//            mainFramePane.getChildren().add(pane);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner();
            dialogStage.initOwner(mainFramePane.getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            ((AddBookController) loader.getController()).setListBooksController(listBooksController, dialogStage);
            // Set the add book window into the controller.
//            ((BookController) loader.getController()).initialize();
            // Show the dialog and wait until the user closes it
            dialogStage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoadWindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void popUpAddMemberScene(ListMembersController memberListController) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoadWindowFrame.class.getResource("/mpplibrary/views/AddMember2.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Member");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner();
            dialogStage.initOwner(mainFramePane.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            ((AddMemberController) loader.getController()).setMemberController(memberListController, dialogStage);
            // Set the add book window into the controller.
//            ((BookController) loader.getController()).initialize();
            // Show the dialog and wait until the user closes it
            dialogStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListBooksController setSceneListBooks() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/ListBooks2.fxml"));
        try {

            AnchorPane pane = loader.load();
            mainFramePane.getChildren().clear();
            mainFramePane.getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(LoadWindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return loader.getController();
    }

    public ListMembersController setSceneListMembers() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/ListMembers2.fxml"));
        try {

            AnchorPane pane = loader.load();
            mainFramePane.getChildren().clear();
            mainFramePane.getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(LoadWindowFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loader.getController();
    }

    public void setSceneCheckoutList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/CheckOutBookList2.fxml"));
            AnchorPane pane = loader.load();
            mainFramePane.getChildren().clear();
            mainFramePane.getChildren().add(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
