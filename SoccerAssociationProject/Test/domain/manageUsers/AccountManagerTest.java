package domain.manageUsers;

import DB.IntegrationTests;
import DB.RegressionTests;
import DB.System;
import DB.UnitTests;
import domain.manageUsers.Account;
import domain.manageUsers.AccountManager;
import domain.manageUsers.Guest;
import domain.manageUsers.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class AccountManagerTest
{
    private System system =System.getInstance();
    private User fan;
    private AccountManager accountManager;
    static boolean flag=true;


    @Before
    public void setUpUnit() throws Exception {
        accountManager = new AccountManager(system);
    }

//    @Before
//    public void setUp() throws Exception {
//       if(flag)
//       {
//           accountManager= new AccountManager(system);
//           fan = system.createNewFanUser("fan","fanUser","FanUser12");
//           flag=false;
//       }
//       else
//       {
//           fan=system.login("fanUser","FanUser12");
//       }
//    }


    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void createAccountSuccess1Unit() throws Exception {
        assertNotNull(accountManager.createAccount("userName","PassUser12"));
    }

    @Test
    @Category({UnitTests.class})
    public void createAccountFailure2Unit() throws Exception {

        String message="";
        try
        {
            accountManager.createAccount("userName",null);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"wrong arguments");

    }

    @Test
    @Category({UnitTests.class})
    public void createAccountFailure3Unit() throws Exception {

        String message="";
        try
        {
            accountManager.createAccount(null,"Pass123");

        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"wrong arguments");
    }

    @Test
    @Category({UnitTests.class})
    public void createInvalidPasswordAccount4Unit() throws Exception {
        String message="";
        try
        {
            accountManager.createAccount("userName","Pass123");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("Invalid password",message);
    }

    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void createDuplicateAccount5Unit()
    {
        String message="";
        try
        {
            accountManager.createAccount("userName","PassUser12");
            accountManager.createAccount("userName","PassUser12");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"Invalid username, userName already exists please try different username");
    }


    @Test
    @Category({UnitTests.class})
    public void createAccountUsernameNotGood6Unit()
    {
        String message="";
        try
        {
            accountManager.createAccount("user","Password1");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"Invalid username, username must be at least 6 characters");
    }


    @Test
    @Category({UnitTests.class})
    public void createAccountPassFailed7Unit() throws Exception {
        int size=accountManager.getUserNames().size();

        String message="";
        try
        {
            accountManager.createAccount("userName"+size+1,"pass");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"Invalid password");
    }

    @Test
    @Category({UnitTests.class})
    public void createAccountPassFailed8Unit() throws Exception {
        int size=accountManager.getUserNames().size();

        String message="";
        try
        {
            accountManager.createAccount("userName"+size+1,null);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"wrong arguments");
    }

    @Test
    @Category({UnitTests.class})
    public void createAccountPassFailed9Unit() throws Exception {
    int size=accountManager.getUserNames().size();

    String message="";
    try
    {
        accountManager.createAccount("userName"+size+1,"pass");
    }
    catch (Exception e)
    {
        message=e.getMessage();
    }
    assertEquals(message,"Invalid password");
}

    @Test
    @Category({UnitTests.class})
    public void createAccountPassFailed10Unit() throws Exception {
        int size=accountManager.getUserNames().size();
        String message="";
        try
        {
            accountManager.createAccount("userName"+size+1,"1234PAS");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"Invalid password");
    }


    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void changePasswordSuccess12Unit() throws Exception {
        accountManager= new AccountManager(system);
        fan = system.createNewFanUser("fan","fanUser","FanUser12");
        assertTrue(accountManager.changePassword("FanUser12","FanUser123",fan));
    }

    @Test
    @Category({UnitTests.class})
    public void changePasswordFailed13Unit() throws Exception {
        accountManager.createAccount("userName", "passUser123");
        String message = "";
        try {
            accountManager.changePassword("userName", "passUser123", fan);
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals(message, "no user");
    }

    @Test
    @Category({UnitTests.class})
    public void changePasswordFailed14Unit() throws Exception {
        String message = "";
        try {
            accountManager.changePassword("FanUser12", "FanUser123", null);
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals(message, "no user");
    }



    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void loginSuccess15Unit() throws Exception {
        setUpUnit();
        accountManager.createAccount("userName", "FanUser12");
        User u=(accountManager.login("userName", "FanUser12"));
        assertNull(u);
    }

    @Test
    @Category({UnitTests.class})
    public void loginFailed16Unit() throws Exception {
        String message="";
        try
        {
            setUpUnit();
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        try
        {
            accountManager.login("noGood", "FanUser12");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message," wrong userName or password, please try again");

    }


    @Test
    @Category({UnitTests.class})
    public void loginFailed17Unit() throws Exception {
        String message="";
        try
        {
            setUpUnit();
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        try
        {
            accountManager.login(null, "FanUser12");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message," wrong userName or password, please try again");

    }

    @Test
    @Category({UnitTests.class})
    public void loginFailed18Unit() throws Exception {
        String message="";
        try
        {
            setUpUnit();
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        try
        {
            accountManager.login("userName", null);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message," wrong userName or password, please try again");

    }


    @Test
    @Category({UnitTests.class})
    public void removeAccountSuccess19Unit() throws Exception {
        int size= accountManager.getUserNames().size();
        accountManager.createAccount(size+1+"userName","passWord123");
        String userName=size+1+"userName";
        assertTrue(accountManager.removeAccount(accountManager.getAccount(userName)));
    }

    @Test
    @Category({UnitTests.class})
    public void removeAccountFailed11Unit() throws Exception {
        int size= accountManager.getUserNames().size();
        String userName=size+1+"userName";
        assertFalse(accountManager.removeAccount(accountManager.getAccount(userName)));
    }

    @Test
    @Category({UnitTests.class})
    public void removeAccountFailed20Unit() throws Exception {
        assertFalse(accountManager.removeAccount(null));
    }


    @Test
    @Category({IntegrationTests.class})
    public void createInvalidPasswordAccount1Integration()
    {
        String message="";
        try
        {
            system.createNewFanUser("User1","userName1","password1");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
       assertEquals("Invalid password",message);
    }



    @Test
    @Category({IntegrationTests.class})
    public void changePasswordFailure2Integration()
    {
        String message="";
        try
        {
            fan.updatePassword("FanUser452","FanUser123");
        }
        catch(Exception e)
        {
            message=e.getMessage();
        }
       assertEquals(message,null);
    }


}