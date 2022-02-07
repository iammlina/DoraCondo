package condo.dora.controllers;

import condo.dora.models.AccountOfStaff;
import condo.dora.models.StaffAccount;
import condo.dora.services.AccountData;
import condo.dora.services.StaffFile;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Optional;

public class AddStaff {
    @FXML public TextField nameText;
    @FXML public TextField usernameText;
    @FXML public PasswordField passwordText;
    @FXML Button addBtn;
    @FXML Button saveBtn;
    @FXML AnchorPane pane;
    @FXML ImageView imageView;
    String imageF;

    private AccountOfStaff acc;
    private AccountData addSf;
    private String admin;

    @FXML public  void initialize() {
        acc = new AccountOfStaff();
        addSf = new StaffFile("data","StaffAccount.csv");
        addImage();
    }

    public void setStaff(String currentAccount) {
        this.admin = currentAccount;
    }

    private void addImage() {
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser chooser = new FileChooser();
                // SET FILECHOOSER INITIAL DIRECTORY
                chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                // DEFINE ACCEPTABLE FILE EXTENSION
                chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg"));
                // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
                File file = chooser.showOpenDialog(addBtn.getScene().getWindow());
                if (file != null) {
                    try {
                        saveImage(file);
                        // CREATE FOLDER IF NOT EXIST
                        File destDir = new File("images");
                        destDir.mkdirs();
                        // RENAME FILE
                        String[] fileSplit = file.getName().split("\\.");
//                        String[] fileSplit = file.getName().split("\\.src.main.resources");
                        String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "." + fileSplit[fileSplit.length - 1];
                        Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + System.getProperty("file.separator") + filename);
                        // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                        Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                        imageView.setImage(new Image(target.toUri().toString()));
//                        System.out.println(target.toUri().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void saveImage(File file) {
        Image image = new Image(file.toURI().toString());
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Image");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg"));

                File file = fileChooser.showSaveDialog(saveBtn.getScene().getWindow());
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),
                            null), "png", file);
                    imageF = file.toURI().toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        imageView.setSmooth(true);
        imageView.setCache(true);
    }

    @FXML public void handleEnterAdminBtnOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ADD STAFF");
        alert.setContentText("ถ้าต้องการยืนยันการเพิ่มเจ้าหน้าที่ส่วนกลางให้พูดว่า...\n"+"'ADD STAFF!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (acc.checkNameStaff(nameText.getText())) {
                Button b= (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                acc = addSf.getAccountData();

                StaffAccount accNew = new StaffAccount(nameText.getText(),usernameText.getText(),passwordText.getText(),"YES",imageF);
                acc.addAccount(accNew);
                addSf.setAccountData(acc);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminhome.fxml"));
                stage.setScene(new Scene(loader.load(), 1000,800));

                stage.show();
            } else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("ไม่สามารถเพิ่มเจ้าหน้าที่ที่ชื่อผู้ใช้เหมือนกันได้\n"+"(Try Again)");
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
}
