package condo.dora.controllers;

import condo.dora.models.Import;
import condo.dora.models.ImportStorage;
import condo.dora.services.ImportData;
import condo.dora.services.ImportFile;
import condo.dora.services.StringConfiguration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ImportHome {
    private ImportStorage itemsLetter;
    private ImportStorage itemsDocument;
    private ImportStorage itemsBox;
    private ImportData addLetter;
    private ImportData addDocument;
    private ImportData addBox;
    private Import selectedImport;
    private ObservableList<Import> itemsObservableList;
    private ObservableList<Import> itemsObservableListD;
    private ObservableList<Import> itemsObservableListB;
    private String staff;
    @FXML private ImageView imageViewImL;
    @FXML private ImageView imageViewImD;
    @FXML private ImageView imageViewImB;
    @FXML private TableView<Import> tableLetter;
    @FXML private TableView<Import> tableDocument;
    @FXML private TableView<Import> tableBox;
    @FXML private Label roomNumL;
    @FXML private Label roomNumD;
    @FXML private Label roomNumB;
    @FXML private Label nameB;
    @FXML private Label nameD;
    @FXML private Label nameL;
    @FXML private Label sendL;
    @FXML private Label sendB;
    @FXML private Label sendD;
    @FXML private Label levelLb;
    @FXML private Label sizeL;
    @FXML private Label sizeB;
    @FXML private Label sizeD;
    @FXML private Label widthLb;
    @FXML private Label numLb;
    @FXML private Label companyLb;



    @FXML
    public void initialize() {
        itemsLetter = new ImportStorage();
        itemsDocument = new ImportStorage();
        itemsBox = new ImportStorage();
        addLetter = new ImportFile("data", "Letter.csv");
        addDocument = new ImportFile("data", "Document.csv");
        addBox = new ImportFile("data", "Box.csv");
        itemsLetter = addLetter.getImportDataLetter();
        itemsDocument = addDocument.getImportDataDocument();
        itemsBox = addBox.getImportDataBox();
        showLetterData();
        showDocumentData();
        showBoxData();

        tableLetter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedLetter(newValue);
            }
        });

        tableDocument.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedDocument(newValue);
            }
        });

        tableBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedBox(newValue);
            }
        });

    }

    public void setStaff(String currentAccount) {
        this.staff = currentAccount;
    }

    private void showLetterData() {
        itemsObservableList = FXCollections.observableArrayList(itemsLetter.toList());
        tableLetter.setItems(itemsObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room Number","field:roomNumber"));
        configs.add(new StringConfiguration("title:Staff Import","field:staff"));
        configs.add(new StringConfiguration("title:Date/Time","field:time"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            tableLetter.getColumns().add(col);
        }
    }

    private void showSelectedLetter(Import item) {
        selectedImport = item;
        roomNumL.setText(item.getRoomNumber());
        nameL.setText(item.getName());
        sendL.setText(item.getSenderInfo());
        sizeL.setText(item.getSize());
        Image image = new Image(item.getImageF());
        imageViewImL.setImage(image);
        imageViewImL.setLayoutX(465);
        imageViewImL.setLayoutY(177);
//        imageViewImL.setX(15);
//        imageViewImL.setY(50);
        imageViewImL.setFitWidth(249);
        imageViewImL.setFitHeight(287);
    }

    private void showDocumentData() {
        itemsObservableListD = FXCollections.observableArrayList(itemsDocument.toList());
        tableDocument.setItems(itemsObservableListD);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room Number","field:roomNumber"));
        configs.add(new StringConfiguration("title:Level","field:level"));
        configs.add(new StringConfiguration("title:Staff Import","field:staff"));
        configs.add(new StringConfiguration("title:Date/Time","field:time"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            tableDocument.getColumns().add(col);
        }
    }

    private void showSelectedDocument(Import item) {
        selectedImport = item;
        roomNumD.setText(item.getRoomNumber());
        nameD.setText(item.getName());
        sendD.setText(item.getSenderInfo());
        sizeD.setText(item.getSize());
        levelLb.setText(item.getLevel());
        Image image = new Image(item.getImageF());
        imageViewImD.setImage(image);
        imageViewImD.setLayoutX(465);
        imageViewImD.setLayoutY(177);
        imageViewImD.setFitWidth(249);
        imageViewImD.setFitHeight(287);
    }

    private void showBoxData() {
        itemsObservableListB = FXCollections.observableArrayList(itemsBox.toList());
        tableBox.setItems(itemsObservableListB);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room Number","field:roomNumber"));
        configs.add(new StringConfiguration("title:Staff Import","field:staff"));
        configs.add(new StringConfiguration("title:Tracking Number","field:trackingNumber"));
        configs.add(new StringConfiguration("title:Date/Time","field:time"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            tableBox.getColumns().add(col);
        }
    }

    private void showSelectedBox(Import item) {
        selectedImport = item;
        roomNumB.setText(item.getRoomNumber());
        nameB.setText(item.getName());
        sendB.setText(item.getSenderInfo());
        sizeB.setText(item.getSize());
//        widthLb.setText(item.getWidth());
        companyLb.setText(item.getCompany());
        numLb.setText(item.getTrackingNumber());
        Image image = new Image(item.getImageF());
        imageViewImB.setImage(image);
        imageViewImB.setLayoutX(465);
        imageViewImB.setLayoutY(177);
        imageViewImB.setFitWidth(249);
        imageViewImB.setFitHeight(287);
    }

    @FXML
    public void handleLetterBtnOnAction(javafx.event.ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addLetter.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 500));

        AddImport addImport = loader.getController();
        addImport.setStaff(staff);

        stage.show();
    }

    @FXML
    public void handleDocumentBtnOnAction(javafx.event.ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addDocument.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 500));

        AddImport addImport = loader.getController();
        addImport.setStaff(staff);

        stage.show();
    }

    @FXML
    public void handleBoxBtnOnAction(javafx.event.ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addBox.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 500));

        AddImport addImport = loader.getController();
        addImport.setStaff(staff);

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
