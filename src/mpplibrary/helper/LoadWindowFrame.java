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
import javafx.scene.layout.AnchorPane;
import mpplibrary.MPPLibrary;
import mpplibrary.application.controllers.AddMemberController;

/**
 *
 * @author user
 */
public class LoadWindowFrame {
           
    private final AnchorPane mainFramePane;

           
    
    public LoadWindowFrame(AnchorPane mainFramePane) {
        this.mainFramePane= mainFramePane;
    }
    
    public void setSceneAddMember() {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/AddMember.fxml"));
              AnchorPane pane = loader.load();
              mainFramePane.getChildren().add(pane);
             ((AddMemberController)loader.getController()).initialize();
//              initValidators();

        } catch (IOException ex) {
            Logger.getLogger(MPPLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
