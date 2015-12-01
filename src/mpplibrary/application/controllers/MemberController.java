/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mpplibrary.MPPLibrary;
import mpplibrary.helper.DataHelper;

/**
 *
 * @author 984970
 */
public class MemberController {
    
    private Stage primaryStage;

    MemberController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    void setSceneAddMember() {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/AddMember.fxml"));
            Parent root = loader.load();
//            ((MenuController) loader.getController()).initialize(primaryStage);
            Scene scene = new Scene(root, DataHelper.MAIN_WINDOW_WIDTH, DataHelper.MAIN_WINDOW_HEIGHT);
//            primaryStage.setTitle("MPP LIBRARY");
            
//            primaryStage.getScene().;
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MPPLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
