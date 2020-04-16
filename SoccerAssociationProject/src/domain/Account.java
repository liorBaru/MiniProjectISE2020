package domain;

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

    public User getUser() {
        return user;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
}
