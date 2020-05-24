package domain.manageUsers;

public class AccountMangerStub extends AccountManager {
    public  Account account=new Account("MatanG","Ga123456");

    public AccountMangerStub() {
    }

    @Override
    public Account getAccount(String userName) {
        return account;
    }

    @Override
    public User login(String userName, String password) throws Exception {
        return super.login(userName, password);
    }

}
