package condo.dora.controllers;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Home {
    @FXML Button readBt;

    @FXML public void initialize() {
    }

    @FXML public void handleSignInBtnOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signin.fxml"));
        stage.setScene(new Scene(loader.load(), 1000,800));

        stage.show();
    }

    @FXML public void handleAboutBtnOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/about.fxml"));
        stage.setScene(new Scene(loader.load(), 1000,800));

        stage.show();
    }

    @FXML public void handleReadGuideBtnOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("READ ABOUT GUIDE");
        alert.setContentText("ถ้าต้องการอ่านเอกสารไกด์ให้พูดว่า...\n"+"'GUIDE!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                Desktop.getDesktop().open(new File("D:\\java\\DoraCondo\\6210451411.pdf"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load(), 1000,800));

        stage.show();
    }
}
