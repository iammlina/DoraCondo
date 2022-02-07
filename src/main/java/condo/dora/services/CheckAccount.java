package condo.dora.services;

public interface CheckAccount {
    boolean checkAccount(String username, String password);
    void changePassword(String username, String newPassword);

}
