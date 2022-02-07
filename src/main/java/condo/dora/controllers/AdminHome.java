package condo.dora.controllers;

import condo.dora.models.AccountOfAdmin;
import condo.dora.models.AccountOfStaff;
import condo.dora.models.AdminAccount;
import condo.dora.services.AccountData;
import condo.dora.services.AdminFile;
import condo.dora.services.StaffFile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Optional;

public class AdminHome {
    @FXML Button addBtn;
    @FXML Button saveBtn;
    @FXML AnchorPane pane;
    @FXML ImageView imageView;
    @FXML TextField username;
    @FXML PasswordField oldPassword;
    @FXML PasswordField newPassword;
    private AccountData accAdmin;
    private AccountOfAdmin accAm;
    private String admin;

    @FXML public  void initialize() {
        accAdmin = new AdminFile("data","AdminAccount.csv");
        accAm = accAdmin.getData();

    }

    public void setStaff(String currentAccount) {
        this.admin = currentAccount;
    }

    @FXML public void handleChangePasswordBtnOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changePassAdmin.fxml"));
        stage.setScene(new Scene(loader.load(), 500,600));

        stage.show();
    }

    @FXML public void handleEnterAdminBtnOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRM TO CHANGE PASSWORD");
        alert.setContentText("ถ้าต้องการยืนยันการเปลี่ยนรหัสผ่านใหม่ให้พูดว่า...\n"+"'CHANGE PASSWORD!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (accAm.checkAccount(username.getText(), oldPassword.getText())) {
                accAm.changePassword(username.getText(), newPassword.getText());
                accAdmin.setData(accAm);

                Button b= (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminhome.fxml"));
                stage.setScene(new Scene(loader.load(), 1000,800));

                stage.show();
            }
            else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("ไม่สามารถเปลี่ยนรหัสผ่านได้\n"+"ชื่อผู้ใช้งานหรือรหัสผ่านเก่าไม่ถูกต้อง (Try Again)");
                alertError.show();
            }
        }


    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminhome.fxml"));
        stage.setScene(new Scene(loader.load(), 1000,800));

        stage.show();
    }


    @FXML public void handleBackLogoutOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signin.fxml"));
        stage.setScene(new Scene(loader.load(), 1000,800));

        stage.show();
    }

    @FXML public void handleAllStaffOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allStaff.fxml"));
        stage.setScene(new Scene(loader.load(), 800,600));

        stage.show();
    }

    @FXML public void handleAddStaffBtnOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addStaff.fxml"));
        stage.setScene(new Scene(loader.load(), 500,600));

        stage.show();
    }



}
