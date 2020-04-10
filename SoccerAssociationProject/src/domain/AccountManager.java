package domain;

import java.util.TreeMap;

public class AccountManager
{
    TreeMap <String, Account> userNames;

    public User login(String userName, String password)
    {
        if(userName!=null && password!=null)
        {
            if(userNames.containsKey(userName))
            {
                Account account =userNames.get(userName);
                if(account.accountVerification(password))
                {
                    return account.getUser();
                }
            }
        }
        return null;
    }

    public User register(String userName, String password,String name)
    {
        if(userName!=null&& password!=null && name!=null)
        {
            if(userName.length()<6 || password.length()<6)
            {
                return null;
            }
            else
            {
                if(userNames.containsKey(userName))
                {
                    return null;
                }
                else
                {
                    Account account = new Account(userName,password);
                    userNames.put(userName,account);
                    User user = new Fan(name,account);
                    return user;
                }
            }
        }
        return null;
    }
}
