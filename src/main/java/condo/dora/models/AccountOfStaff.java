package condo.dora.models;

import condo.dora.services.CheckAccount;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class AccountOfStaff implements CheckAccount { //FOR STAFF
    private ArrayList<StaffAccount> accounts;

    public AccountOfStaff() {
        accounts = new ArrayList<>();
    }

    public void addAccount(StaffAccount acc) { accounts.add(acc);}

    public void deleteStaff(StaffAccount acc) { accounts.remove(acc);}

    @Override
    public boolean checkAccount(String username, String password) {

        for (int i = 0; i < accounts.size(); i++){
            if (accounts.get(i).getAccountStaff().equals(username) && accounts.get(i).getPassword().equals(password)) {
                if (accounts.get(i).getStatus().equals("YES")) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println(accounts.get(i).getAccountStaff());
                    accounts.get(i).setTime(dtf.format(now));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void changePassword(String username, String password) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountStaff().equals(username)) {
                accounts.get(i).setPassword(password);
            }
        }
    }

    public boolean checkNameStaff(String username) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountStaff().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<StaffAccount> toList() {
        return accounts;
    }

}
