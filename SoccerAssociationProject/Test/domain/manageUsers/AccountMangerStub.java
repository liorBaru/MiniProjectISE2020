package domain.manageUsers;

import DB.System;
import domain.manageUsers.Account;
import domain.manageUsers.AccountManager;
import domain.manageUsers.User;

public class AccountMangerStub extends AccountManager {
    public  Account account=new Account("MatanG","Ga123456");

    public AccountMangerStub(System system) {
        super(system);
    }

    @Override
    public Account getAccount(String userName) {
        return account;
    }

    @Override
    public User login(String userName, String password) throws Exception {
        return super.login(userName, password);
    }

    @Override
    public Account createAccount(String userName, String password) throws Exception {
        return account;
    }
}
