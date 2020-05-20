package main.domain.manageUsers;

import main.DB.UsersDaoSql;
import main.domain.Asset.Coach;
import main.domain.Asset.Fan;
import main.domain.Asset.Refree.Refree;

import java.util.List;

public class AccountManager
{
    private UsersDaoSql usersDaoSql =UsersDaoSql.getInstance();

    public Account getAccount(String userName)
    {
        String[] key = {userName};
        List<String[]> user =usersDaoSql.get(key);
        if(user.isEmpty()==false)
        {
            String [] userString=user.get(0);
            Account account = new Account(userString[0],userString[1]);
            return account;
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
    public Account createAccount(String userName, String password) throws Exception
    {
        if(userName!=null && password!=null)
        {
            if(userName.length()>=6 )
            {
                if(checkPassword(password))
                {
                    String[] params={userName,password};
                    usersDaoSql.save(params);
                    Account account = new Account(userName,password);
                    return account;
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


    public boolean changePassword(String oldPassword, String newPassword, User user) throws Exception {
        if(user!=null)
        {
            if(user.account.accountVerification(oldPassword))
            {
                if(checkPassword(newPassword))
                {
                    user.account.setPassword(newPassword);
                    return true;
                }
            }
            throw new Exception("wrong password");
        }
        throw new Exception("no user");

    }


    /**
     * gal
     * gets account from the system by username
     * @param userName
     * @return
     */



    /**
     * gal,
     * find the relevant user in toLoginPage and return the user
     * @param userName
     * @param password
     * @return user
     * @throws Exception
     */
    public User login(String userName, String password) throws Exception
    {
        if(userName!=null && password!=null)
        {
            String [] key={userName};
            List<String []> userData =usersDaoSql.get(key);
            if(userData.isEmpty())
            {
                throw new  Exception(" wrong userName or password, please try again");
            }
            else
            {
                String [] userDetails= userData.get(0);
                if(userDetails[1].equals(password))
                {
                    if(userDetails[3].equals("Fan"))
                    {
                        User user = Fan.createFan(userDetails);
                        return user;
                    }
                    if(userDetails[3].equals("Coach"))
                    {

                        //TODO:
                        // 1.complete the tables to add coach
                       // User coach = Coach.createCoach(userDetails);
                        //return user
                    }
                    if(userDetails[3].equals("Refree"))
                    {
                        Refree referee = Refree.createReferee(userDetails);
                    }
                    if(userDetails[3].equals("Player"))
                    {

                    }
                    if(userDetails[3].equals("IFA"))
                    {

                    }
                    if(userDetails[3].equals("Owner"))
                    {

                    }
                    if(userDetails[3].equals("TeamManager"))
                    {

                    }
                    if(userDetails[3].equals("SystemManager"))
                    {

                    }
                }
            }
        }
        return null;
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
            String [] key = {username};
            usersDaoSql.delete(key);
            return true;
        }
        return false;
    }


}
