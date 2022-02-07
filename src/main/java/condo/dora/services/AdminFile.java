package condo.dora.services;

import condo.dora.models.AccountOfAdmin;
import condo.dora.models.AccountOfStaff;
import condo.dora.models.AdminAccount;

import java.io.*;

public class AdminFile implements AccountData{
    private String fileDirectoryName;
    private String fileName;
    private AccountOfAdmin admin;


    public AdminFile(String fileDirectoryName, String fileName){
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
            AdminAccount account = new AdminAccount(data[0].trim(), data[1].trim());
            admin.addAccount(account);
        }
        reader.close();
    }

    @Override
    public AccountOfAdmin getData() {
        try {
            admin = new AccountOfAdmin();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return admin;
    }

    @Override
    public void setData(AccountOfAdmin account) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (AdminAccount acc: account.toList()) {
                String line = acc.getAccountAdmin() + ","
                        + acc.getPassword();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

    @Override
    public AccountOfStaff getAccountData() { return null; }

    @Override
    public void setAccountData(AccountOfStaff accounts) { }
}
