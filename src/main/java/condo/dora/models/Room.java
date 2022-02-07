package condo.dora.models;

public class Room {
    private String roomNumber;
    private String typeRoom;
    private String resident;
    private String status;
    private String email;
    private String tel;
    private String total;
    private String floor;

    public Room(String roomNumber,String floor,String typeRoom, String resident, String email, String tel, String total, String status) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.typeRoom = typeRoom;
        this.resident = resident;
        this.email = email;
        this.tel = tel;
        this.total = total;
        this.status = status;
    }


    public String getRoomNumber() { return roomNumber; }
    public String getTypeRoom() { return typeRoom; }
    public String getResident() { return resident; }
    public String getStatus() { return status; }
    public String getEmail() { return email; }
    public String getTel() { return tel; }
    public String getTotal() { return total; }
    public String getFloor() { return floor; }

    public void setTypeRoom(String typeRoom) { this.typeRoom = typeRoom; }
    public void setEmail(String email) { this.email = email; }
    public void setTel(String tel) { this.tel = tel; }
    public void setTotal(String total) { this.total = total; }
    public void setResident(String resident) { this.resident = resident; }
    public void setStatus(String status) { this.status = status; }
    public void setFloor(String floor) { this.floor = floor; }

    public void updateToDelete(String name, String email, String tel,String total,String status) {
        setResident("-");
        setEmail("-");
        setTel("-");
        setTotal("-");
        setStatus("FREE");
    }
}
