package domain;

import java.util.TreeMap;

public class AccountManager
{
    private TreeMap <String, Account> userNames;

    public AccountManager ()
    {
        userNames=new TreeMap<>();
    }

    public boolean containsUserName(String userName)
    {
        return this.userNames.containsKey(userName);
    }

    public User getUser(String userName, String password)
    {
        if(userNames.containsKey(userName))
        {
            Account account =userNames.get(userName);
            if(account.accountVerification(password))
            {
                return account.getUser();
            }
        }
        // throw nor good password or user name
        return null;
    }


    public boolean addAcount(String userName, Account account)
    {
        if(account!=null && userName!=null)
        {
            userNames.put(userName,account);
            return true;
        }
        return false;
    }
}
