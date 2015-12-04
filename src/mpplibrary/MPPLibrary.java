/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mpplibrary.application.controllers.LoginController;
import mpplibrary.application.controllers.MenuController;
import mpplibrary.helper.DataHelper;
import mpplibrary.helper.LoadWindowFrame;
import mpplibrary.interfaces.OnLoginSuccessListener;

/**
 *
 * @author 984970
 */
public class MPPLibrary extends Application implements OnLoginSuccessListener {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/Login.fxml"));
            Parent root = loader.load();
            ((LoginController) loader.getController()).initialize(this);
            Scene scene = new Scene(root, DataHelper.WINDOW_WIDTH, DataHelper.WINDOW_HEIGHT);

            
            primaryStage.setResizable(false);

            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
            this.primaryStage = primaryStage;
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void onLoginSuccess() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mpplibrary/views/MainWindow.fxml"));
            Parent root = loader.load();
            ((MenuController) loader.getController()).initialize(primaryStage);
            Scene scene = new Scene(root, DataHelper.MAIN_WINDOW_WIDTH, DataHelper.MAIN_WINDOW_HEIGHT);
            primaryStage.setTitle("MPP Library Management");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MPPLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
