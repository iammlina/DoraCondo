package condo.dora.controllers;

import condo.dora.models.AccountOfStaff;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/home.fxml"));
        primaryStage.setTitle("DORA CONDO");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}
