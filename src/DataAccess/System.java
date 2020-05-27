package DataAccess;
import domain.Asset.BoardMember;
import domain.Asset.Fan;
import domain.Asset.SystemManager;
import ExternalSystems.PaymentProxy;
import ExternalSystems.PaymentSystem;
import ExternalSystems.TaxProxy;
import ExternalSystems.TaxSystem;
import domain.manageTeams.FinancialAction;
import domain.manageUsers.Account;
import domain.manageUsers.AccountManager;
import domain.manageUsers.User;
import domain.manageEvents.Notification;
import java.sql.SQLException;


public class System
{

    private static System system = new System();
    private static AccountManager accountManager;
    private NotificationsDaoSql notificationsDaoSql;
    static PaymentSystem externalPaymentsSystem;
    static TaxSystem externalTaxSystem;
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
            PaymentSystem externalPaymentsSystem = new PaymentProxy();
            TaxSystem externalTaxSystem = new TaxProxy();
           // system.connectToIFA();
          // system.connectToTaxLaW();
        }
        catch (Exception e)
        {
            system=null;
            throw e;
        }
    }

    public boolean createNewFanUser(String name, String userName, String password)throws Exception
    {
         Account account = accountManager.createAccount(userName,password,"Fan");
         Fan newUser = new Fan(name,account);
         return true;
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

    public void sendNotification(String fanUserName,Notification notification) throws SQLException
    {
         String [] key ={fanUserName,notification.getDetails(),notification.getDate()};
         notificationsDaoSql.save(key);
    }

    public boolean addPaymentToPaymentSystem(String teamName , String date , Double amount)
    {
        return externalPaymentsSystem.addPayment(teamName,date,amount);
    }


    public double addTaxRate(double revenueAmount)
    {
        return externalTaxSystem.getTaxRate(revenueAmount);
    }

    public void sendNotificationToSystemManager(Notification notification) throws SQLException {
        SystemManagerDaoSql systemManagerDaoSql=SystemManagerDaoSql.getInstance();
        for (String [] manager:systemManagerDaoSql.getAll())
        {
            sendNotification(manager[0],notification);
        }
    }
}
