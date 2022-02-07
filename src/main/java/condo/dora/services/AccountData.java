package condo.dora.services;

import condo.dora.models.AccountOfAdmin;
import condo.dora.models.AccountOfStaff;

import java.io.File;

public interface AccountData {
    AccountOfStaff getAccountData();
    void setAccountData(AccountOfStaff accounts);
    AccountOfAdmin getData();
    void setData(AccountOfAdmin account);
}
