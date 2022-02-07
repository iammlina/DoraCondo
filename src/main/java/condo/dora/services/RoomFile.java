package condo.dora.services;

import condo.dora.models.Import;
import condo.dora.models.ImportStorage;
import condo.dora.models.Room;
import condo.dora.models.RoomArraylist;

import java.io.*;

public class RoomFile implements RoomData{
    private String fileDirectoryName;
    private String fileName;
    private RoomArraylist rooms;

    public RoomFile(String fileDirectoryName, String fileName){
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName; //เอามาต่อกัน
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile(); // ไม่มีไฟล์ก็สร้าง
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    private void readDataRoom() throws IOException{
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null){
            String[] data = line.split(",");
            Room room = new Room(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim());
            rooms.addRoom(room);
        }
        reader.close();
    }




    @Override
    public RoomArraylist getRoomData() {
        try {
            rooms = new RoomArraylist();
            readDataRoom();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return rooms;
    }

    @Override
    public void setRoomData(RoomArraylist roomData) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Room room : roomData.toList()) {
                String line = room.getRoomNumber() + ","
                        + room.getFloor() + ","
                        + room.getTypeRoom() + ","
                        + room.getResident() + ","
                        + room.getEmail() + ","
                        + room.getTel() + ","
                        + room.getTotal() + ","
                        + room.getStatus();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }



}
