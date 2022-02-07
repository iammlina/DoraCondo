package condo.dora.controllers;

import condo.dora.models.AccountOfAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import condo.dora.models.AccountOfStaff;
import condo.dora.models.AdminAccount;
import condo.dora.services.AccountData;
import condo.dora.services.StaffFile;
import condo.dora.services.AdminFile;

import java.io.IOException;

public class Signin {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    private AccountData acc;
    private AccountData accAdmin;
    private AccountOfStaff accSf;
    private AccountOfAdmin accAm;
    private String currentAccount ;

    @FXML public  void initialize() {
        acc = new StaffFile("data","StaffAccount.csv");
        accSf = acc.getAccountData();
        accAdmin = new AdminFile("data","AdminAccount.csv");
        accAm = accAdmin.getData();
    }

    @FXML public void handleLoginBtnOnAction(ActionEvent event) throws IOException {

        if(accAm.checkAccount(usernameField.getText() ,passwordField.getText()))
        {
            accAdmin.setData(accAm);
            currentAccount = usernameField.getText();
            Button b= (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminhome.fxml"));
            stage.setScene(new Scene(loader.load(), 1000,800));

//            AdminHome adminHome = loader.getController();
//            adminHome.setStaff(currentAccount);

            stage.show();
        }

        else if(accSf.checkAccount(usernameField.getText() ,passwordField.getText())) {
                acc.setAccountData(accSf);
                currentAccount = usernameField.getText();
                Button b= (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/staffhome.fxml"));
                stage.setScene(new Scene(loader.load(), 1000,800));

                StaffHome staffHome = loader.getController();
                staffHome.setStaff(currentAccount);

                stage.show();
            }

            else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("ชื่อผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง\n"+"ดังขึ้นอีก! (Try Again)");
                alertError.show();
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
