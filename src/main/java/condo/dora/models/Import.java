package condo.dora.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Import {
    private String name;
    private String roomNumber;
    private String senderInfo;
    private String size;
    private String level;
    private String company;
    private String trackingNumber;
    private String width;
    private String time;
    private String imageF;
    private String staff;
    private String status;
    private String consignee;

    public Import(String name, String roomNumber, String senderInfo, String size, String imageF, String staff,String status) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.senderInfo = senderInfo;
        this.size = size;
        this.imageF = imageF;
        this.staff = staff;
        this.status = status;
    }

    public Import(String name, String roomNumber, String senderInfo, String size, String level, String imageF, String staff,String status) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.senderInfo = senderInfo;
        this.size = size;
        this.level = level;
        this.imageF = imageF;
        this.staff = staff;
        this.status = status;
    }

    public Import(String name, String roomNumber, String senderInfo, String company, String trackingNumber, String width, String size, String imageF, String staff,String status) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.senderInfo = senderInfo;
        this.company = company;
        this.trackingNumber = trackingNumber;
        this.width = width;
        this.size = size;
        this.imageF = imageF;
        this.staff = staff;
        this.status = status;
    }

    public boolean isRoomNumber(String roomNumber) {
        return this.roomNumber.contains(roomNumber);
    }

    public String getName() { return name; }
    public String getRoomNumber() { return roomNumber; }
    public String getSenderInfo() { return senderInfo; }
    public String getSize() { return size; }
    public String getLevel() { return level; }
    public String getCompany() { return company; }
    public String getTrackingNumber() { return trackingNumber; }
    public String getWidth() { return width; }
    public String getImageF() { return imageF; }
    public String getTime() { return time; }
    public String getStaff() { return staff; }
    public String getStatus() { return status; }
    public String getConsignee() { return consignee; }
    public void setConsignee(String consignee) { this.consignee = consignee; }

    public void setStaff(String staff) { this.staff = staff; }

    public void setStatus(String status) { this.status = status; }

    public void setTime(String time) { this.time = time; }

    public void setTimeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.time = dtf.format(now);
    }

    public void updateExport(String consigneeName, String staffExport) {
        this.consignee = consigneeName;
        this.staff = staffExport;
        this.status = "-- RECEIVED --";
    }
}
