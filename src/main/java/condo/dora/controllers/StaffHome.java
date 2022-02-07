package condo.dora.controllers;

import condo.dora.services.*;
import condo.dora.models.AccountOfStaff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;


public class StaffHome {

    private AccountData acc;
    private AccountOfStaff accSf;
    private String staff;

    @FXML private Label staffLb;
    @FXML TextField username;
    @FXML PasswordField oldPassword;
    @FXML PasswordField newPassword;


    @FXML
    public void initialize() {
        acc = new StaffFile("data","StaffAccount.csv");
        accSf = acc.getAccountData();
    }

    public void setStaff(String currentAccount) {
        this.staff = currentAccount;
        staffLb.setText(staff);
    }



    @FXML
    public void handleImportBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/importHome.fxml"));
        stage.setScene(new Scene(loader.load(),750, 600));

        ImportHome importHome = loader.getController();
        importHome.setStaff(staff);

        stage.show();

    }


    @FXML public void handleChangePasswordBtnOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changePassStaff.fxml"));
        stage.setScene(new Scene(loader.load(), 500,600));

        StaffHome staffHome = loader.getController();
        staffHome.setStaff(staff);

        stage.show();
    }

    @FXML public void handleEnterStaffBtnOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRM TO CHANGE PASSWORD");
        alert.setContentText("ถ้าต้องการยืนยันการเปลี่ยนรหัสผ่านใหม่ให้พูดว่า...\n"+"'CHANGE PASSWORD!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (accSf.checkAccount(username.getText(), oldPassword.getText())) {
                accSf.changePassword(username.getText(), newPassword.getText());
                acc.setAccountData(accSf);

                Button b= (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/staffhome.fxml"));
                stage.setScene(new Scene(loader.load(), 1000,800));

                StaffHome staffHome = loader.getController();
                staffHome.setStaff(staff);

                stage.show();
            }
            else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("ไม่สามารถเปลี่ยนรหัสผ่านได้\n"+"=ชชื่อผู้ใช้งานหรือรหัสผ่านเก่าไม่ถูกต้อง (Try Again)");
                alertError.show();
            }
        }

    }



    @FXML
    public void handleLogoutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/signin.fxml"));
        stage.setScene(new Scene(loader.load(), 1000, 800));

        stage.show();

    }

    @FXML
    public void handleExportBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/exportHome.fxml"));
        stage.setScene(new Scene(loader.load(), 1000, 800));

        ExportHome exportHome = loader.getController();
        exportHome.setStaff(staff);

        stage.show();

    }

    @FXML
    public void handleConcludeBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/conclude.fxml"));
        stage.setScene(new Scene(loader.load(), 1000, 800));

        ConcludeHome concludeHome = loader.getController();
        concludeHome.setStaff(staff);

        stage.show();

    }

    @FXML
    public void handleSearchBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/searchHome.fxml"));
        stage.setScene(new Scene(loader.load(), 1000, 800));

        SearchHome searchHome = loader.getController();
        searchHome.setStaff(staff);

        stage.show();

    }

    @FXML public void handleBackBtnStaffOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/staffhome.fxml"));
        stage.setScene(new Scene(loader.load(), 1000,800));

        StaffHome staffHome = loader.getController();
        staffHome.setStaff(staff);

        stage.show();
    }

}
