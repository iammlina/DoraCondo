package condo.dora.controllers;

import condo.dora.models.Import;
import condo.dora.models.Room;
import condo.dora.models.RoomArraylist;
import condo.dora.services.RoomData;
import condo.dora.services.RoomFile;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

public class SearchHome {
//    @FXML TextField roomNum;
//    @FXML TextField type;
//    @FXML TextField nameTf;
//    @FXML TextField emailTf;
//    @FXML TextField telTf;
//    @FXML TextField totalTf;
    @FXML Label firstLb;
    @FXML Label emailLb;
    @FXML Label phoneLb;
    @FXML Button deleteBt;
    @FXML private TableView<Room> tableResident;
    @FXML private TextField searchText;

    private RoomArraylist rooms;
    private RoomData roomData;
    private Room selectedResident;
    private ObservableList<Room> roomObservableList;
    private String staff;

    @FXML public void initialize() {
        rooms = new RoomArraylist();
        roomData = new RoomFile("data","Room.csv");
        rooms = roomData.getRoomData();
        showRoomData();
        Search();

        tableResident.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedResident(newValue);
            }
        });

    }

    public void setStaff(String currentAccount) {
        this.staff = currentAccount;
    }

    private void showRoomData() {
        roomObservableList = FXCollections.observableArrayList(rooms.toList());
        tableResident.setItems(roomObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Room Number","field:roomNumber"));
        configs.add(new StringConfiguration("title:Floor","field:floor"));
        configs.add(new StringConfiguration("title:Type Room","field:typeRoom"));
        configs.add(new StringConfiguration("title:Resident","field:resident"));
        configs.add(new StringConfiguration("title:Number of Resident","field:total"));
        configs.add(new StringConfiguration("title:Status","field:status"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn((conf.get("title")));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            tableResident.getColumns().add(col);
        }
    }

    private void showSelectedResident(Room room) {
        selectedResident = room;
        deleteBt.setDisable(false);
        firstLb.setText(selectedResident.getResident());
        emailLb.setText(selectedResident.getEmail());
        phoneLb.setText(selectedResident.getTel());
    }

    private void clearSelectedAll() {
        selectedResident = null;
        deleteBt.setDisable(true);
    }

    private void Search() {
        FilteredList<Room> filteredListL = new FilteredList<>(roomObservableList, e -> true);
        searchText.setOnKeyReleased(event -> {
            searchText.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredListL.setPredicate((Predicate<? super Room>) room->{
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseSearch = newValue.toLowerCase();
                    if (room.getRoomNumber().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    } else if (room.getResident().toLowerCase().contains(lowerCaseSearch)){
                        return true;
                    } else if (room.getStatus().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    } else if (room.getTypeRoom().toLowerCase().contains(lowerCaseSearch)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<Room> sortedList = new SortedList<>(filteredListL);
            sortedList.comparatorProperty().bind(tableResident.comparatorProperty());
            tableResident.setItems(sortedList);
        });
    }

    @FXML public void handleDeleteResidentBtnOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("DELETE RESIDENT");
        alert.setContentText("ถ้าต้องการนำผู้พักออกจากห้องให้พูดว่า...\n"+"'DELETE RESIDENT!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (rooms.checkDeleterResident(selectedResident.getStatus())) {
                selectedResident.updateToDelete(selectedResident.getResident(),selectedResident.getEmail(),selectedResident.getTel(),selectedResident.getTotal(),selectedResident.getStatus());
                clearSelectedAll();
                tableResident.refresh();
                tableResident.getSelectionModel().clearSelection();
                roomData.setRoomData(rooms);
                this.staff = staff;
            }
            else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("ห้องพักนี้ไม่มีผู้พักอยู่แล้ว!\n"+"(Try Again)");
                alertError.show();
            }
        }

    }

    @FXML
    public void handleAddRoomBtnOnAction(javafx.event.ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addRoom.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 500));

        AddRoomAndResident addRoomAndResident = loader.getController();
        addRoomAndResident.setStaff(staff);

        stage.show();
    }

    @FXML
    public void handleAddResidentBtnOnAction(javafx.event.ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addResident.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 500));

        AddRoomAndResident addRoomAndResident = loader.getController();
        addRoomAndResident.setStaff(staff);

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
