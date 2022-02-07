package condo.dora.controllers;

import condo.dora.models.*;
import condo.dora.services.ImportData;
import condo.dora.services.ImportFile;
import condo.dora.services.RoomData;
import condo.dora.services.RoomFile;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AddImport {
    @FXML public TextField name;
    @FXML public TextField building;
    @FXML public TextField roomNum;
    @FXML public TextField sender;
    @FXML public TextField size;
    @FXML public TextField level;
    @FXML public TextField trackingNum;
    @FXML public TextField company;
    @FXML public TextField width;

    @FXML Button addBtn;
    @FXML Button saveBtn;
    @FXML ImageView imageView;
    String imageF;

    private ImportStorage itemsImport;
    private ImportData addLetter;
    private ImportData addDocument;
    private ImportData addBox;
    private String staffAdd;
    private String staff;
    private RoomArraylist rooms;
    private RoomData roomData;

    @FXML
    public void initialize() {
        rooms = new RoomArraylist();
        roomData = new RoomFile("data","Room.csv");
        rooms = roomData.getRoomData();
        itemsImport = new ImportStorage();
        addLetter = new ImportFile("data", "Letter.csv");
        addDocument = new ImportFile("data", "Document.csv");
        addBox = new ImportFile("data", "Box.csv");
        addImage();

    }

    public void addImage() {
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
                        System.out.println(target.toUri().toString());
                        System.out.println(imageView);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void setStaff(String currentAccount) {
        this.staff = currentAccount;
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

    @FXML
    public void handleEnterBtnLetterOnAction(javafx.event.ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ADD LETTER");
        alert.setContentText("ถ้าต้องการยืนยันการเพิ่มจดหมายให้พูดว่า...\n"+"'ADD LETTER!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (rooms.checkRoomHaveResident(roomNum.getText(),name.getText())) {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                itemsImport = addLetter.getImportDataLetter();

                Import itemNew = new Import(name.getText(), roomNum.getText(), sender.getText(), size.getText(), imageF,staff,"-- WAITING --");
                itemNew.setTimeNow();
                itemsImport.addItem(itemNew);
                addLetter.setImportDataLetter(itemsImport);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/importHome.fxml"));
                stage.setScene(new Scene(loader.load(), 750, 600));

                ImportHome importHome = loader.getController();
                importHome.setStaff(staff);

                stage.show();
            }
            else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("ไม่มีผู้พักในห้องพักหมายเลขนี้!\n"+"(Try Again)");
                alertError.show();
            }

        }

    }

    @FXML
    public void handleEnterBtnDocumentOnAction(javafx.event.ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ADD DOCUMENT");
        alert.setContentText("ถ้าต้องการยืนยันการเพิ่มเอกสารให้พูดว่า...\n"+"'ADD DOCUMENT!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (rooms.checkRoomHaveResident(roomNum.getText(),name.getText())) {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                itemsImport = addDocument.getImportDataDocument();

                Import itemNew = new Import(name.getText(), roomNum.getText(), sender.getText(), size.getText(), level.getText(), imageF,staff,"-- WAITING --");
                itemNew.setTimeNow();
                itemsImport.addItem(itemNew);
                addDocument.setImportDataDocument(itemsImport);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/importHome.fxml"));
                stage.setScene(new Scene(loader.load(), 750, 600));

                ImportHome importHome = loader.getController();
                importHome.setStaff(staff);

                stage.show();
            }
            else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("ไม่มีผู้พักในห้องพักหมายเลขนี้!\n"+"(Try Again)");
                alertError.show();
            }
        }


    }

    @FXML
    public void handleEnterBtnBoxOnAction(javafx.event.ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ADD PARCEL POST");
        alert.setContentText("ถ้าต้องการยืนยันการเพิ่มพัสดุให้พูดว่า...\n"+"'ADD PARCEL POST!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (rooms.checkRoomHaveResident(roomNum.getText(),name.getText())) {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                itemsImport = addBox.getImportDataBox();

                Import itemNew = new Import(name.getText(), roomNum.getText(), sender.getText(), company.getText(), trackingNum.getText(), width.getText(), size.getText(), imageF,staff,"-- WAITING --");
                itemNew.setTimeNow();
                itemsImport.addItem(itemNew);
                addBox.setImportDataBox(itemsImport);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/importHome.fxml"));
                stage.setScene(new Scene(loader.load(), 750, 600));

                ImportHome importHome = loader.getController();
                importHome.setStaff(staff);

                stage.show();
            }
            else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("ไม่มีผู้พักในห้องพักหมายเลขนี้!\n"+"(Try Again)");
                alertError.show();
            }
        }


    }

    @FXML public void handleBackBtnStaffOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        System.out.println(staffAdd);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/staffhome.fxml"));
        stage.setScene(new Scene(loader.load(), 1000,800));

        StaffHome staffHome = loader.getController();
        staffHome.setStaff(staff);

        stage.show();
    }

    @FXML public void handleBackImportHomeBtnOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/importHome.fxml"));
        stage.setScene(new Scene(loader.load(), 750,600));

        ImportHome importHome = loader.getController();
        importHome.setStaff(staff);

        stage.show();
    }
}
