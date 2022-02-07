package condo.dora.models;


public class AdminAccount  {
    private String accountAdmin;
    private String password;

    public AdminAccount(String accountAdmin, String password){
        this.accountAdmin = accountAdmin;
        this.password = password;

    }

    public String getAccountAdmin() { return accountAdmin; }

    public String getPassword() { return password; }

    public void setAccountAdmin(String accountAdmin) { this.accountAdmin = accountAdmin; }

    public void setPassword(String password) { this.password = password; }

}

