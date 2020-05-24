package domain.manageUsers;

import DataAccess.UsersDaoSql;
import domain.Asset.*;
import domain.Asset.*;
import domain.Asset.Refree.Refree;
import domain.manageLeagues.IFA;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;

public class AccountManager
{
    private static UsersDaoSql usersDaoSql =UsersDaoSql.getInstance();

    public Account getAccount(String userName)
    {
        String[] key = {userName};
        List<String[]> user =usersDaoSql.get(key);
        if(user!=null)
        {
            String [] userString=user.get(0);
            Account account = new Account(userString[0],userString[1]);
            return account;
        }
        return null;
    }

    public String getUserType(String userName) throws Exception {
        String [] key ={userName};
        List<String[]> users =usersDaoSql.get(key);
        if(users!=null && users.isEmpty()==false)
        {
            String[] user=users.get(0);
            return user[2];
        }
        throw new Exception("wrong input");
    }

    public List<String[]> getUserNames()
    {
        List<String[]> userNames =usersDaoSql.getAll();
        return userNames;
    }


    /**
     * gal
     * create new account
     * @param userName
     * @param password
     * @return new Account
     * @throws Exception
     */
    public Account createAccount(String userName, String password,String type) throws Exception
    {
        if(userName!=null && password!=null)
        {
            if(userName.length()>=6 )
            {
                if(checkPassword(password))
                {
                    String salt="1";
                    String newPasswod = BCrypt.hashpw(password, BCrypt.gensalt());
                    System.out.println(newPasswod);
                    String[] params={userName,newPasswod,salt,type};
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
                    String newPasswordEncrypt = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                    String[] update = {user.getAccount().getUserName(),newPasswordEncrypt,user.getName(),"1",user.getKind()};
                    usersDaoSql.update(update);
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
                boolean encryptSuccess= BCrypt.checkpw(password, userDetails[1]);
                if(encryptSuccess)
                {
                    User user=getUser(userDetails[0],userDetails[3]);
                    return user;
                }
            }
        }
        return null;
    }

    public User getUser(String userName, String type) throws Exception {
        String[] key = {userName};
        User user=null;
        if(type.equals("Fan"))
        {
            user = Fan.createFanFromDB(key);
            return user;
        }
        else if(type.equals("Coach"))
        {
            user= Coach.getCoachFromDB(key);
        }
        else if(type.equals("Refree"))
        {
            user= Refree.getRefreeFromDB(key);
        }
        else if(type.equals("Player"))
        {
            user= Player.getPlayerFromDB(key);
        }
        else if(type.equals("IFA"))
        {
            user=IFA.getIFADromDB(key);
        }
        else if(type.equals("Owner"))
        {
            user= Owner.getOwnerFromDB(key);
        }
        else if(type.equals("TeamManager"))
        {
            user= TeamManager.createTeamManagerFromDB(key);
        }
        else if(type.equals("SystemManager"))
        {
            user= SystemManager.createSystemManagerFromDB(key);
        }
        else
        {
            throw new Exception("wrong arguments");
        }
        return user;
    }

    /**
     * gal,
     * remove account from the system by system manager
     * @param account
     * @return
     */
    public boolean removeAccount(Account account) throws SQLException {
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
