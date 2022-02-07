package condo.dora.models;

import condo.dora.services.CheckAccount;

import java.util.ArrayList;

public class AccountOfAdmin implements CheckAccount {
    private ArrayList<AdminAccount> accounts;
    private String currentAccount ;

    public AccountOfAdmin() { accounts = new ArrayList<>(); }

    public void addAccount(AdminAccount acc) { accounts.add(acc);}

    @Override
    public boolean checkAccount(String accountAdminInput, String passwordInput) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountAdmin().equals(accountAdminInput) && accounts.get(i).getPassword().equals(passwordInput)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void changePassword(String username, String password) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountAdmin().equals(username)) {
                accounts.get(i).setPassword(password);
            }
        }
    }


    public  ArrayList<AdminAccount> toList(){
        return accounts;
    }
}
