package condo.dora.services;

import condo.dora.models.AccountOfStaff;
import condo.dora.models.Import;
import condo.dora.models.ImportStorage;
import condo.dora.models.StaffAccount;

import java.io.*;

public class ImportFile implements ImportData{
    private String fileDirectoryName;
    private String fileName;
    private ImportStorage items;

    public ImportFile(String fileDirectoryName, String fileName){
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

    private void readDataLetter() throws IOException{
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null){
            String[] data = line.split(",");
            Import letter = new Import(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim());
            letter.setTime(data[7].trim());
            letter.setConsignee(data[8].trim());
            items.addItem(letter);
        }
        reader.close();
    }

    private void readDataDocument() throws IOException{
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null){
            String[] data = line.split(",");
            Import document = new Import(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim());
            document.setTime(data[8].trim());
            document.setConsignee(data[9].trim());
            items.addItem(document);
        }
        reader.close();
    }

    private void readDataBox() throws IOException{
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null){
            String[] data = line.split(",");
            Import box = new Import(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim(), data[9].trim());
            box.setTime(data[10].trim());
            box.setConsignee(data[11].trim());
            items.addItem(box);
        }
        reader.close();
    }

    @Override
    public ImportStorage getImportDataLetter() {
        try {
            items = new ImportStorage();
            readDataLetter();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return items;
    }

    @Override
    public void setImportDataLetter(ImportStorage items) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Import item : items.toList()) {
                String line = item.getName() + ","
                        + item.getRoomNumber() + ","
                        + item.getSenderInfo() + ","
                        + item.getSize() + ","
                        + item.getImageF() + ","
                        + item.getStaff() + ","
                        + item.getStatus() + ","
                        + item.getTime() + ","
                        + item.getConsignee();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

    @Override
    public ImportStorage getImportDataDocument() {
        try {
            items = new ImportStorage();
            readDataDocument();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return items;
    }

    @Override
    public void setImportDataDocument(ImportStorage items) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Import item : items.toList()) {
                String line = item.getName() + ","
                        + item.getRoomNumber() + ","
                        + item.getSenderInfo() + ","
                        + item.getSize() + ","
                        + item.getLevel() + ","
                        + item.getImageF() + ","
                        + item.getStaff() + ","
                        + item.getStatus() + ","
                        + item.getTime() + ","
                        + item.getConsignee();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

    @Override
    public ImportStorage getImportDataBox() {
        try {
            items = new ImportStorage();
            readDataBox();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return items;
    }

    @Override
    public void setImportDataBox(ImportStorage items) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Import item : items.toList()) {
                String line = item.getName() + ","
                        + item.getRoomNumber() + ","
                        + item.getSenderInfo() + ","
                        + item.getCompany() + ","
                        + item.getTrackingNumber() + ","
                        + item.getWidth() + ","
                        + item.getSize() + ","
                        + item.getImageF() + ","
                        + item.getStaff() + ","
                        + item.getStatus() + ","
                        + item.getTime() + ","
                        + item.getConsignee();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
