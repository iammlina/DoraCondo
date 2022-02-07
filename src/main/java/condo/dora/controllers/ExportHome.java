package condo.dora.controllers;

import condo.dora.models.Import;
import condo.dora.models.ImportStorage;
import condo.dora.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class ExportHome {
    private ImportStorage itemsLetter;
    private ImportStorage itemsDocument;
    private ImportStorage itemsBox;
    private ImportData addLetter;
    private ImportData addDocument;
    private ImportData addBox;
    private String staff;
    private Import selectedImport;

    private ObservableList<Import> itemsObservableList;
    private ObservableList<Import> itemsObservableListD;
    private ObservableList<Import> itemsObservableListB;


    @FXML private TableView<Import> tableExL;
    @FXML private TableView<Import> tableExD;
    @FXML private TableView<Import> tableExB;
    @FXML private ImageView imageView;
    @FXML private TextField consigneeTf;
    @FXML private Button updateBt;
    @FXML private TextField searchTextL;
    @FXML private TextField searchTextB;
    @FXML private TextField searchTextD;

    @FXML public void initialize() {
        itemsLetter = new ImportStorage();
        itemsDocument = new ImportStorage();
        itemsBox = new ImportStorage();
        addLetter = new ImportFile("data", "Letter.csv");
        addDocument = new ImportFile("data", "Document.csv");
        addBox = new ImportFile("data", "Box.csv");
        itemsLetter = addLetter.getImportDataLetter();
        itemsDocument = addDocument.getImportDataDocument();
        itemsBox = addBox.getImportDataBox();
//        updateBt.setDisable(true);
        showLetterData();
        showDocumentData();
        showBoxData();

        tableExL.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedLetter(newValue);
            }
        });

        tableExD.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedDocument(newValue);
            }
        });

        tableExB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedBox(newValue);
            }
        });

        Search();
    }

    public void setStaff(String currentAccount) {
        this.staff = currentAccount;
    }

    private void showLetterData() {
        itemsObservableList = FXCollections.observableArrayList(itemsLetter.toList());
        tableExL.setItems(itemsObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room Number","field:roomNumber"));
        configs.add(new StringConfiguration("title:Consignee Name","field:consignee"));
        configs.add(new StringConfiguration("title:Date/Time","field:time"));
        configs.add(new StringConfiguration("title:Status","field:status"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            tableExL.getColumns().add(col);
        }
    }

    private void showSelectedLetter(Import item) {
        selectedImport = item;
//        itemsLetter.addConsigneeName(consigneeTf.getText());
        updateBt.setDisable(false);
        Image image = new Image(item.getImageF());
        imageView.setImage(image);
        imageView.setLayoutX(680);
        imageView.setLayoutY(245);
        imageView.setFitWidth(281);
        imageView.setFitHeight(261);
    }

    private void showDocumentData() {
        itemsObservableListD = FXCollections.observableArrayList(itemsDocument.toList());
        tableExD.setItems(itemsObservableListD);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room Number","field:roomNumber"));
        configs.add(new StringConfiguration("title:Consignee Name","field:consignee"));
        configs.add(new StringConfiguration("title:Date/Time","field:time"));
        configs.add(new StringConfiguration("title:Status","field:status"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            tableExD.getColumns().add(col);
        }
    }

    private void showSelectedDocument(Import item) {
        selectedImport = item;
        updateBt.setDisable(false);
        Image image = new Image(item.getImageF());
        imageView.setImage(image);
        imageView.setLayoutX(680);
        imageView.setLayoutY(245);
        imageView.setFitWidth(281);
        imageView.setFitHeight(261);

    }

    private void showBoxData() {
        itemsObservableListB = FXCollections.observableArrayList(itemsBox.toList());
        tableExB.setItems(itemsObservableListB);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room Number","field:roomNumber"));
        configs.add(new StringConfiguration("title:Consignee Name","field:consignee"));
        configs.add(new StringConfiguration("title:Tracking Number","field:trackingNumber"));
        configs.add(new StringConfiguration("title:Date/Time","field:time"));
        configs.add(new StringConfiguration("title:Status","field:status"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            tableExB.getColumns().add(col);
        }
    }

    private void showSelectedBox(Import item) {
        selectedImport = item;
        updateBt.setDisable(false);
        Image image = new Image(item.getImageF());
        imageView.setImage(image);
        imageView.setLayoutX(680);
        imageView.setLayoutY(245);
        imageView.setFitWidth(281);
        imageView.setFitHeight(261);

    }

    private void clearSelectedAll() {
        selectedImport = null;
        updateBt.setDisable(true);
    }

    @FXML
    public void handleUpdateButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXPORT THIS");
        alert.setContentText("ถ้าต้องการส่งออกแก่ผู้พักให้พูดว่า...\n"+"'EXPORT!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (itemsLetter.checkExport(selectedImport.getStatus()) || itemsDocument.checkExport(selectedImport.getStatus()) || itemsBox.checkExport(selectedImport.getStatus())) {
//                String consigneeName = consigneeTf.getText();
                selectedImport.updateExport(consigneeTf.getText(), staff);
                selectedImport.setTimeNow();
                clearSelectedAll();
                tableExB.refresh();
                tableExD.refresh();
                tableExL.refresh();
                tableExL.getSelectionModel().clearSelection();
                tableExB.getSelectionModel().clearSelection();
                tableExD.getSelectionModel().clearSelection();

                addLetter.setImportDataLetter(itemsLetter);
                addDocument.setImportDataDocument(itemsDocument);
                addBox.setImportDataBox(itemsBox);
            } else if (itemsLetter.checkExport(selectedImport.getStatus()) == false && itemsDocument.checkExport(selectedImport.getStatus()) == false && itemsBox.checkExport(selectedImport.getStatus()) == false) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("ได้ส่งออกไปแล้ว!\n" + "(Try Again)");
                alertError.show();
            }
        }

    }




    private void Search() {
        FilteredList<Import> filteredListL = new FilteredList<>(itemsObservableList, e -> true);
        FilteredList<Import> filteredListD = new FilteredList<>(itemsObservableListD, e -> true);
        FilteredList<Import> filteredListB = new FilteredList<>(itemsObservableListB, e -> true);
        searchTextL.setOnKeyReleased(e ->{
            searchTextL.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredListL.setPredicate((Predicate<? super Import>) item->{
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseSearch = newValue.toLowerCase();
                    if (item.getRoomNumber().toLowerCase().contains(lowerCaseSearch)){
                        return true;
                    } else if (item.getStatus().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<Import> sortedList = new SortedList<>(filteredListL);
            sortedList.comparatorProperty().bind(tableExL.comparatorProperty());
            tableExL.setItems(sortedList);
        });

        searchTextD.setOnKeyReleased(e ->{
            searchTextD.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredListD.setPredicate((Predicate<? super Import>) item->{
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseSearch = newValue.toLowerCase();
                    if (item.getRoomNumber().toLowerCase().contains(lowerCaseSearch)){
                        return true;
                    } else if (item.getStatus().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<Import> sortedList = new SortedList<>(filteredListD);
            sortedList.comparatorProperty().bind(tableExD.comparatorProperty());
            tableExD.setItems(sortedList);
        });

        searchTextB.setOnKeyReleased(e ->{
            searchTextB.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredListB.setPredicate((Predicate<? super Import>) item->{
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseSearch = newValue.toLowerCase();
                    if (item.getRoomNumber().toLowerCase().contains(lowerCaseSearch)){
                        return true;
                    } else if (item.getStatus().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<Import> sortedList = new SortedList<>(filteredListB);
            sortedList.comparatorProperty().bind(tableExB.comparatorProperty());
            tableExB.setItems(sortedList);
        });
    }

    @FXML
    public void handleBackBtnStaffOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/staffhome.fxml"));
        stage.setScene(new Scene(loader.load(), 1000,800));

        StaffHome staffHome = loader.getController();
        staffHome.setStaff(staff);

        stage.show();
    }
}
