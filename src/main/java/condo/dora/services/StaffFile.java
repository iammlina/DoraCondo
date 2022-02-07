package condo.dora.services;

import condo.dora.models.AccountOfAdmin;
import condo.dora.models.StaffAccount;
import condo.dora.models.AccountOfStaff;

import java.io.*;

public class StaffFile implements AccountData {

    private String fileDirectoryName;
    private String fileName;
    private AccountOfStaff accountOfStaff;

    public StaffFile(String fileDirectoryName, String fileName){
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

    private void readData() throws IOException{
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null){
            String[] data = line.split(",");
            StaffAccount staff = new StaffAccount(data[0].trim(), data[1].trim(), data[2].trim(),data[3].trim(),data[4].trim());
            staff.setTime(data[5].trim());
            accountOfStaff.addAccount(staff);
        }
        reader.close();
    }


    @Override
    public AccountOfStaff getAccountData() {
        try {
            accountOfStaff = new AccountOfStaff();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return accountOfStaff;
    }

    @Override
    public void setAccountData(AccountOfStaff accounts) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (StaffAccount acc: accounts.toList()) {
                String line = acc.getName() + ","
                        + acc.getAccountStaff() + ","
                        + acc.getPassword() + ","
                        + acc.getStatus() + ","
                        + acc.getImageF() + ","
                        + acc.getTime();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

    @Override
    public AccountOfAdmin getData() { return null; }

    @Override
    public void setData(AccountOfAdmin account) { }

}
