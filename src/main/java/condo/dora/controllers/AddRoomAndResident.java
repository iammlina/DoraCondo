package condo.dora.controllers;

import condo.dora.models.Room;
import condo.dora.models.RoomArraylist;
import condo.dora.services.RoomData;
import condo.dora.services.RoomFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AddRoomAndResident {
    @FXML TextField roomNum;
    @FXML TextField floor;
    @FXML TextField type;
    @FXML TextField nameTf;
    @FXML TextField emailTf;
    @FXML TextField telTf;
    @FXML TextField totalTf;

    private RoomArraylist rooms;
    private RoomData roomData;
    private String free = "FREE";
    private String staff;

    @FXML
    public void initialize() {
        rooms = new RoomArraylist();
        roomData = new RoomFile("data","Room.csv");
        rooms = roomData.getRoomData();


    }

    public void setStaff(String currentAccount) {
        this.staff = currentAccount;
    }

    @FXML
    public void handleEnterBtnRoomOnAction(javafx.event.ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ADD ROOM");
        alert.setContentText("ถ้าต้องการยืนยันการเพิ่มห้องพักให้พูดว่า...\n"+"'ADD ROOM!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (rooms.checkRoomNumber(roomNum.getText())) {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();


                Room roomNew = new Room(roomNum.getText(),floor.getText(),type.getText(),"-","-","-","-",free);
                rooms.addRoom(roomNew);
                roomData.setRoomData(rooms);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/searchHome.fxml"));
                stage.setScene(new Scene(loader.load(), 1000, 800));

                SearchHome searchHome = loader.getController();
                searchHome.setStaff(staff);

                stage.show();
            } else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("มีห้องพักหมายเลขนี้แล้ว!\n"+"(Try Again)");
                alertError.show();
            }
        }

    }

    @FXML
    public void handleEnterBtnResidentOnAction(javafx.event.ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ADD RESIDENT");
        alert.setContentText("ถ้าต้องการยืนยันการเพิ่มผู้พักให้พูดว่า...\n"+"'ADD RESIDENT!' (OK)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (rooms.checkRoomFree(roomNum.getText(),free)) {
                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                rooms.updateRoom(roomNum.getText(),nameTf.getText(),emailTf.getText(),telTf.getText(),totalTf.getText(),"-- BOOK --");
                roomData.setRoomData(rooms);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/searchHome.fxml"));
                stage.setScene(new Scene(loader.load(), 1000, 800));

                SearchHome searchHome = loader.getController();
                searchHome.setStaff(staff);

                stage.show();
            } else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("ห้องพักนี้มีผู้พักคนอื่นแล้ว!\nหรือไม่มีห้องหมายเลขนี้ในคอนโด\n"+"(Try Again)");
                alertError.show();
            }
        }

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b= (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/searchHome.fxml"));
        stage.setScene(new Scene(loader.load(), 1000,800));

        SearchHome searchHome = loader.getController();
        searchHome.setStaff(staff);

        stage.show();
    }
}
