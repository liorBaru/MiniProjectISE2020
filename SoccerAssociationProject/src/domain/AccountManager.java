package domain;

import java.util.TreeMap;

public class AccountManager
{
    private TreeMap <String, Account> userNames;
    private System system;


    public AccountManager (System system)
    {
        this.system=system;
        userNames=new TreeMap<>();
    }

    /**
     * gal
     * create new account
     * @param userName
     * @param password
     * @return new Account
     * @throws Exception
     */
    public Account createAccount(String userName, String password) throws Exception {
        if(userName!=null && password!=null)
        {
            if(userName.length()>6 && userNames.containsKey(userName)==false)
            {
                if(checkPassword(password))
                {
                    Account account = new Account(userName,password);
                    userNames.put(userName,account);
                    return account;
                }
                throw new Exception("Invalid password");
            }
            throw new Exception("Invalid username");
        }
        return null;
    }


    /**
     * gal
     * check if the account password is legal
     * @param password
     * @return
     */
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

    /**
     * gal,
     * find the relevant user in login and return the user
     * @param userName
     * @param password
     * @return user
     * @throws Exception
     */
    public User login(String userName, String password) throws Exception
    {
        if(userNames.containsKey(userName))
        {
            Account account =userNames.get(userName);
            if(account.accountVerification(password))
            {
                return account.getUser();
            }
        }
        throw new  Exception(" wrong userName or password, please try again");
    }

}
