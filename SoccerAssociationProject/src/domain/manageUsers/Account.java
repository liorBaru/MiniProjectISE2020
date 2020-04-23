package domain.manageUsers;

public class Account
{
    private String userName;
    private String password;
    private User user;

    public Account(String userName, String password)
    {
        this.userName=userName;
        this.password=password;
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
