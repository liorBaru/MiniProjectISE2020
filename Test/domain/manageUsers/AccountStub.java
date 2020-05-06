package domain.manageUsers;

import main.domain.manageUsers.Account;
import main.domain.manageUsers.User;

public class AccountStub extends Account
{
    private String userName;
    private String password;
    private User user;

    public AccountStub()
    {
        super("account","passAccount123");
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * gal
     * check if the password is correct
     * @param password
     * @return
     */
    public boolean accountVerification(String password)
    {
        if(password!=null && password==this.password )
        {
            return true;
        }
        return false;
    }

    /**
     * gal
     * gets the user by account
     * @return
     */


}
