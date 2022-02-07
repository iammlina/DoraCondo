package condo.dora.models;

public class StaffAccount {
    private String accountStaff;
    private String name;
    private String password;
    private String time;
    private String imageF;
    private String status;

    public StaffAccount(String name, String accountStaff, String password, String status, String imageF){
        this.name = name;
        this.accountStaff = accountStaff;
        this.password = password;
        this.status = status;
        this.imageF = imageF;
    }

    public String getAccountStaff() { return accountStaff; }

    public String getPassword() { return password; }

    public String getName() { return name; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public void setPassword(String password) { this.password = password; }

    public String getImageF() { return imageF; }

    public void setImageF(String imageF) { this.imageF = imageF; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public void turnOn() { this.status = "YES"; }

    public void turnOff() { this.status = "NO"; }
}

