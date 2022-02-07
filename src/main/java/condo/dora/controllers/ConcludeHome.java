package condo.dora.controllers;

import condo.dora.models.Import;
import condo.dora.models.ImportStorage;
import condo.dora.services.ImportData;
import condo.dora.services.ImportFile;
import condo.dora.services.StringConfiguration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ConcludeHome {
    private ImportStorage itemsLetter;
    private ImportStorage itemsDocument;
    private ImportStorage itemsBox;
    private ImportData addLetter;
    private ImportData addDocument;
    private ImportData addBox;
    private String staff;

    private ObservableList<Import> itemsObservableList;
    private ObservableList<Import> itemsObservableListD;
    private ObservableList<Import> itemsObservableListB;

    @FXML private TableView<Import> tableConL;
    @FXML private TableView<Import> tableConD;
    @FXML private TableView<Import> tableConB;
    @FXML private TextField searchTextL;
    @FXML private TextField searchTextB;
    @FXML private TextField searchTextD;

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

        showLetterDataInConclude();
        showDocumentDataInConclude();
        showBoxDataInConclude();
        Search();

    }

    public void setStaff(String currentAccount) {
        this.staff = currentAccount;
    }

    private void showLetterDataInConclude() {
        itemsObservableList = FXCollections.observableArrayList(itemsLetter.toList());
        tableConL.setItems(itemsObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room Number","field:roomNumber"));
        configs.add(new StringConfiguration("title:Name","field:name"));
        configs.add(new StringConfiguration("title:Sender Info","field:senderInfo"));
        configs.add(new StringConfiguration("title:Size","field:size"));
        configs.add(new StringConfiguration("title:Consignee Name","field:consignee"));
        configs.add(new StringConfiguration("title:Staff Import Or Export","field:staff"));
        configs.add(new StringConfiguration("title:Date/Time","field:time"));
        configs.add(new StringConfiguration("title:Status","field:status"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            tableConL.getColumns().add(col);
        }
    }

    private void showDocumentDataInConclude() {
        itemsObservableListD = FXCollections.observableArrayList(itemsDocument.toList());
        tableConD.setItems(itemsObservableListD);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room Number","field:roomNumber"));
        configs.add(new StringConfiguration("title:Name","field:name"));
        configs.add(new StringConfiguration("title:Sender Info","field:senderInfo"));
        configs.add(new StringConfiguration("title:Size","field:size"));
        configs.add(new StringConfiguration("title:Level","field:level"));
        configs.add(new StringConfiguration("title:Consignee Name","field:consignee"));
        configs.add(new StringConfiguration("title:Staff Import Or Export","field:staff"));
        configs.add(new StringConfiguration("title:Date/Time","field:time"));
        configs.add(new StringConfiguration("title:Status","field:status"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            tableConD.getColumns().add(col);
        }
    }

    private void showBoxDataInConclude() {
        itemsObservableListB = FXCollections.observableArrayList(itemsBox.toList());
        tableConB.setItems(itemsObservableListB);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room Number","field:roomNumber"));
        configs.add(new StringConfiguration("title:Name","field:name"));
        configs.add(new StringConfiguration("title:Sender Info","field:senderInfo"));
        configs.add(new StringConfiguration("title:Company","field:company"));
        configs.add(new StringConfiguration("title:Tracking Number","field:trackingNumber"));
        configs.add(new StringConfiguration("title:Size","field:size"));
        configs.add(new StringConfiguration("title:Width","field:width"));
        configs.add(new StringConfiguration("title:Consignee Name","field:consignee"));
        configs.add(new StringConfiguration("title:Staff Import Or Export","field:staff"));
        configs.add(new StringConfiguration("title:Date/Time","field:time"));
        configs.add(new StringConfiguration("title:Status","field:status"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            tableConB.getColumns().add(col);
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
                    } else if (item.getName().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    } else if (item.getStaff().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<Import> sortedList = new SortedList<>(filteredListL);
            sortedList.comparatorProperty().bind(tableConL.comparatorProperty());
            tableConL.setItems(sortedList);
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
                    } else if (item.getName().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    } else if (item.getStaff().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    } else if (item.getLevel().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<Import> sortedList = new SortedList<>(filteredListD);
            sortedList.comparatorProperty().bind(tableConD.comparatorProperty());
            tableConD.setItems(sortedList);
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
                    } else if (item.getName().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    } else if (item.getStaff().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    } else if (item.getTrackingNumber().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<Import> sortedList = new SortedList<>(filteredListB);
            sortedList.comparatorProperty().bind(tableConB.comparatorProperty());
            tableConB.setItems(sortedList);
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
