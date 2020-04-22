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

    public Account getAccount(String userName)
    {
        if(userNames.containsKey(userName))
        {
            return userNames.get(userName);
        }
        return null;
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
            if(userName.length()>=6 )
            {
                if(userNames.containsKey(userName)==false)
                {
                    if(checkPassword(password))
                    {
                        Account account = new Account(userName,password);
                        userNames.put(userName,account);
                        return account;
                    }
                }
                throw new Exception("Invalid username, userName already exists please try different username");
            }
            throw new Exception("Invalid username, username must be at least 6 characters");
        }
        throw new Exception("wrong arguments");
    }


    /**
     * gal
     * check if the account password is legal
     * @param password
     * @return
     */
    private boolean checkPassword(String password) throws Exception {
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
        throw new Exception("Invalid password");
    }

    /**
     * gal
     * change user password
     * @param oldPassword
     * @param newPassword
     * @param user
     * @throws Exception
     */


    public void changePassword(String oldPassword, String newPassword, User user) throws Exception {
        if(user!=null)
        {
            if(user.account.accountVerification(oldPassword))
            {
                if(checkPassword(newPassword))
                {
                    user.account.setPassword(newPassword);
                    return;
                }
            }
            throw new Exception("wrong password");
        }
    }


    /**
     * gal
     * gets account from the system by username
     * @param userName
     * @return
     */



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

    /**
     * gal,
     * remove account from the system by system manager
     * @param account
     * @return
     */
    public boolean removeAccount(Account account)
    {
        if(account!=null)
        {
            String username=account.getUserName();
            if(userNames.containsKey(username))
            {
                userNames.remove(username);
                return true;
            }
        }
        return false;
    }

}
