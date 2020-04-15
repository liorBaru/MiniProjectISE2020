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

    public boolean createAccount(String userName , String password)
    {
        if(userName!=null && password!=null)
        {
            if(userName.length()>6 && userNames.containsKey(userName)==false)
            {
                if(checkPassword(password))
                {
                    Account account = new Account(userName,password);
                    userNames.put(userName,account);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkPassword(String password)
    {
        if(password!=null && password.length()>=8)
        {
            boolean digit=false;
            boolean capitalLetter=false;
            boolean lowerLetter=false;
            char c;
            for (int i=0; i<password.length();i++)
            {
                c=password.charAt(i);
                if(Character.isDigit(c) && !digit)
                {
                    digit=true;
                }
                else if(Character.isUpperCase(c)&& !capitalLetter)
                {
                    capitalLetter=true;
                }
                else if(Character.isLowerCase(c)&& !lowerLetter)
                {
                    lowerLetter=true;
                }
                if(digit && capitalLetter && lowerLetter)
                    return true;
            }
            if(digit && capitalLetter && lowerLetter)
            {
                return true;
            }
        }
        return false;
    }

    public Account getAccount(String userName)
    {
        if(userNames.containsKey(userName))
        {
            return userNames.get(userName);
        }
        return null;
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
