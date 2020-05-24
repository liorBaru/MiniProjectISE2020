package DataAccess;

import domain.manageEvents.Notification;
import domain.manageUsers.Account;
import domain.manageUsers.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class SystemTest
{
    System system;

    @Before
    @Test
    @Category(UnitTests.class)
    public void initSystemUnit3()
    {
        try
        {
            System.initSystem("userAdmain","Password1","chen");
            assertTrue(system!=null);
            Account account=system.getAccountManager().getAccount("userAdmin");
            system.getAccountManager().removeAccount(account);

        }
        catch (Exception e)
        {
            system=System.getInstance();
        }
    }

    @Test
    @Category({UnitTests.class,RegressionTests.class})
    public void sendNotificationTest1()
    {
        try {
            Notification notification = new Notification("details");
            system.sendNotification("fanTest",notification);
            User user=system.getAccountManager().login("fanTest","Galb1234");
            assertTrue(user.readNotification().contains(notification));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    @Category(UnitTests.class)
    public void sendNotificationTest2()
    {
        String message="";
        try {
            Notification notification = new Notification("details");
            system.sendNotification("fanTestW",notification);
            User user=system.getAccountManager().login("fanTest","Galb1234");
        }
        catch (Exception e)
        {
            message=e.getMessage();
            e.printStackTrace();
        }
        assertEquals("message",message);
    }

    @Test
    @Category(UnitTests.class)
    public void sendNotificationTest3()
    {
        String message="";
        try {
            Notification notification = new Notification("");
            system.sendNotification("fanTest",notification);
            User user=system.getAccountManager().login("fanTest","Galb1234");
        }
        catch (Exception e)
        {
            message=e.getMessage();
            e.printStackTrace();
        }
        assertEquals("message",message);
    }
}