package main.DB;
import main.domain.Asset.*;
import main.domain.managePages.Page;
import main.domain.manageTeams.FinancialAction;
import main.domain.manageUsers.Account;
import main.domain.manageUsers.AccountManager;
import main.domain.Asset.Refree.LineRefree;
import main.domain.Asset.Refree.MainRefree;
import main.domain.Asset.Refree.Refree;
import main.domain.Asset.Refree.VarRefree;
import main.domain.manageUsers.User;
import main.domain.manageEvents.Complaint;
import main.domain.manageEvents.Notification;
import main.domain.manageLeagues.IFA;
import main.domain.manageLeagues.League;
import main.domain.manageLeagues.Season;
import main.domain.manageTeams.Team;

import java.sql.SQLException;
import java.util.*;


public class System
{

    private static System system;
    private static AccountManager accountManager;
    private NotificationsDaoSql notificationsDaoSql;
    private System ()
    {
        accountManager = new AccountManager();
    }

    public static System getInstance()
    {
        if(system!=null)
        {
            return system;
        }
        else
        {
            system=new System();
            try {
                system.initSystem("adminSystem1", "admin123A", "admin");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return system;
        }
    }

    public static void initSystem(String userName, String password, String name) throws Exception {
        try
        {
           // system=getInstance();
           // system.connectToDB();
            Account account = system.accountManager.createAccount(userName,password,"SystemManager");
            SystemManager systemManager = new SystemManager(name,account);
           // system.connectToIFA();
          // system.connectToTaxLaW();
        }
        catch (Exception e)
        {
            system=null;
            throw e;
        }
    }

    public Fan createNewFanUser(String name, String userName, String password)throws Exception
    {
         Account account = accountManager.createAccount(userName,password,"Fan");
         Fan newUser = new Fan(name,account);
         return newUser;
    }


    public User login (String username, String password) throws Exception
    {
        return accountManager.login(username,password);
    }


    public void changePassword(String oldPassword,String newPassword,User user) throws Exception {
        accountManager.changePassword(oldPassword,newPassword,user);
    }

    public AccountManager getAccountManager()
    {
        return this.accountManager;
    }

    public void sendNotification(String fanUserName,Notification notification) throws SQLException {
         String [] key ={fanUserName,notification.getDetails(),notification.getDate()};
         notificationsDaoSql.save(key);
    }


    private void connectToIFA() throws Exception {
        // throw new Exception("cant connect to IFA systems");
    }

    private void connectToTaxLaW() throws Exception
    {

        //throw new Exception("cant connect to tax law system");

    }



    public void sendFinancialAction(BoardMember boardMember, FinancialAction financialAction)
    {

    }
}
