package com.rayandrew.spamassassin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by rufus on 6/7/2017.
 */
public class MainClass extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainClass.fxml"));

        Parent root = loader.load();
        InterfaceClass interfaceClass = loader.getController();
        interfaceClass.setHostServices(getHostServices());
        Scene scene = new Scene(root);
        primaryStage = stage;
        scene.getStylesheets().add(getClass().getResource("/modena_dark.css").toExternalForm());
        primaryStage.setTitle("Spam Assassin");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}