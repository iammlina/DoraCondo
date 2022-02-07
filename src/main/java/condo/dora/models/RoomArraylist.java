package condo.dora.models;

import java.util.ArrayList;

public class RoomArraylist {
    private ArrayList<Room> rooms;

    public RoomArraylist() { rooms = new ArrayList<>();}

    public void addRoom(Room room) { rooms.add(room);}


    public boolean checkRoomNumber(String newNum) {
        for (int i = 0; i < rooms.size(); i++){
            if (rooms.get(i).getRoomNumber().equals(newNum)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkRoomFree (String roomNum, String status) {
        for (int i = 0; i < rooms.size(); i++){
            if (rooms.get(i).getRoomNumber().equals(roomNum) && rooms.get(i).getStatus().toLowerCase().equals(status.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public void updateRoom ( String roomNum, String name,String email, String tel,String total,String status) {
        for (int i = 0; i < rooms.size(); i++){
            if (rooms.get(i).getRoomNumber().equals(roomNum) ) {
                rooms.get(i).setResident(name);
                rooms.get(i).setEmail(email);
                rooms.get(i).setTel(tel);
                rooms.get(i).setTotal(total);
                rooms.get(i).setStatus(status);
            }
        }
    }

    public boolean checkRoomHaveResident(String number, String name) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber().equals(number) && rooms.get(i).getResident().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public  boolean checkDeleterResident(String status) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getStatus().equals(status)) {
                if (status.equals("-- BOOK --")){
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Room> toList() { return rooms;}
}
