package condo.dora.controllers;

import condo.dora.models.AccountOfStaff;
import condo.dora.models.StaffAccount;
import condo.dora.services.AccountData;
import condo.dora.services.StaffFile;
import condo.dora.services.StringConfiguration;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

public class AllStaff {

    private AccountOfStaff acc;
    private AccountData accSf;
    private StaffAccount selectedStaff;
    private ObservableList<StaffAccount> staffAccountObservableList;
    private String admin;

    @FXML private TableView<StaffAccount> staffTable;
    @FXML private Label usernameLb;
    @FXML private Label nameLb;
    @FXML private Label timeLb;
    @FXML AnchorPane pane;
    @FXML ImageView imageView;
    @FXML private TextField searchText;
    @FXML private Button deleteBt;
    @FXML private Button onBt;
    @FXML private Button offBt;

    @FXML public void initialize() {
        acc = new AccountOfStaff();
        accSf = new StaffFile("data","StaffAccount.csv");
        acc = accSf.getAccountData();

        showStaffData();
        Search();

        staffTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedStaff(newValue);
            }
        });
    }

    public void setStaff(String currentAccount) {
        this.admin = currentAccount;
    }

    private void showStaffData(){
        staffAccountObservableList = FXCollections.observableArrayList(acc.toList());
        staffTable.setItems(staffAccountObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Username","field:accountStaff"));
        configs.add(new StringConfiguration("title:Password","field:password"));
        configs.add(new StringConfiguration("title:Status","field:status"));
        configs.add(new StringConfiguration("title:Last Time","field:time"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            staffTable.getColumns().add(col);
        }

    }

    private void showSelectedStaff(StaffAccount staff) {
        selectedStaff = staff;
        deleteBt.setDisable(false);
        offBt.setDisable(false);
        onBt.setDisable(false);
        usernameLb.setText(staff.getAccountStaff());
        nameLb.setText(staff.getName());
        timeLb.setText(staff.getTime());
        Image image = new Image(staff.getImageF());
        imageView.setImage(image);
        imageView.setX(15);
        imageView.setY(50);
        imageView.setFitWidth(249);
        imageView.setFitHeight(287);
    }

    private void Search() {
        FilteredList<StaffAccount> filteredListL = new FilteredList<>(staffAccountObservableList, e -> true);
        searchText.setOnKeyReleased(event -> {
            searchText.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredListL.setPredicate((Predicate<? super StaffAccount>) staff->{
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseSearch = newValue.toLowerCase();
                    if (staff.getAccountStaff().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    } else if (staff.getStatus().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<StaffAccount> sortedList = new SortedList<>(filteredListL);
            sortedList.comparatorProperty().bind(staffTable.comparatorProperty());
            staffTable.setItems(sortedList);
        });

    }

    private void clearSelectedAll() {
        selectedStaff = null;
        deleteBt.setDisable(true);
        offBt.setDisable(true);
        onBt.setDisable(true);
    }

    @FXML public void handleTurnOnButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONTINUED");
        alert.setContentText("ถ้าต้องการเปิดระบบเจ้าหน้าที่ส่วนกลางให้พูดว่า...\n"+"'CONTINUED!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            selectedStaff.turnOn();
            clearSelectedAll();
            staffTable.getSelectionModel().getSelectedItems();
            staffTable.refresh();
            accSf.setAccountData(acc);
        }
    }

    @FXML public void handleTurnOffButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("SUSPENDED");
        alert.setContentText("ถ้าต้องการปิดระบบเจ้าหน้าที่ส่วนกลางให้พูดว่า...\n"+"'SUSPENDED!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            selectedStaff.turnOff();
            clearSelectedAll();
            staffTable.getSelectionModel().getSelectedItems();
            staffTable.refresh();
            accSf.setAccountData(acc);
        }
    }

    @FXML public void handleDeleteStaffBtnOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("DELETE STAFF");
        alert.setContentText("ถ้าต้องการยืนยันการลบเจ้าหน้าที่ส่วนกลางให้พูดว่า...\n"+"'DELETE STAFF!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            acc.deleteStaff(selectedStaff);
            clearSelectedAll();
            staffTable.getItems().removeAll(
                    staffTable.getSelectionModel().getSelectedItems()
            );
            staffTable.refresh();
            accSf.setAccountData(acc);
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

