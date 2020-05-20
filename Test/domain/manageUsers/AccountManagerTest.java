package domain.manageUsers;

import main.DB.RegressionTests;
import main.DB.UnitTests;
import main.domain.manageUsers.Account;
import main.domain.manageUsers.AccountManager;
import main.domain.manageUsers.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class AccountManagerTest
{

    private AccountManager accountManager;

    @Before
    public void setUpUnit() throws Exception {
        accountManager = new AccountManager();
    }

    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void createAccountSuccess1Unit() throws Exception
    {
        accountManager.createAccount("userName","PassUser12","Fan");
        boolean flag=false;
        Account account=accountManager.getAccount("username");
        if (account!=null)
        {
            flag=true;
        }
        assertTrue(flag);
        accountManager.removeAccount(account);
        account=accountManager.getAccount("username");
        flag=false;
        if (account==null)
        {
            flag=true;
        }
        assertTrue(flag);
    }

    @Test
    @Category({UnitTests.class})
    public void createAccountFailure2Unit() throws Exception {

        String message="";
        try
        {
            accountManager.createAccount("userName",null,"Fan");
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
            accountManager.createAccount(null,"Pass123","Fan");

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
            accountManager.createAccount("userName","Pass123","Fan");
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
        Account account=null;
        try
        {
            account=accountManager.createAccount("userName","PassUser12","Fan");
            accountManager.createAccount("userName","PassUser12","Fan");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"Invalid username, userName already exists please try different username");
        accountManager.removeAccount(account);
    }


    @Test
    @Category({UnitTests.class})
    public void createAccountUsernameNotGood6Unit()
    {
        String message="";
        try
        {
            accountManager.createAccount("user","Password1","Fan");
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
        String message="";
        try
        {
            accountManager.createAccount("userName","pass","Fan");
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
        String message="";
        try
        {
            accountManager.createAccount("userName",null,"Fan");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"wrong arguments");
    }

    @Test
    @Category({UnitTests.class})
    public void createAccountTypeFailed9Unit() throws Exception {
    int size=accountManager.getUserNames().size();

    String message="";
    try
    {
        accountManager.createAccount("userName","passWord12","type");
    }
    catch (Exception e)
    {
        message=e.getMessage();
    }
    assertEquals(message,"Invalid arguments");
}

    @Test
    @Category({UnitTests.class})
    public void createAccountPassFailed10Unit() throws Exception {
        int size=accountManager.getUserNames().size();
        String message="";
        try
        {
            accountManager.createAccount("userName"+size+1,"1234PAS","Fan");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"Invalid password");
    }


    @Test
    @Category({UnitTests.class, RegressionTests.class})
    public void changePasswordSuccess12Unit() throws Exception
    {
        User user=accountManager.getUser("fanTest","Fan");
        assertTrue(accountManager.changePassword("Galb1234","FanUser12",user));
        accountManager.changePassword("FanUser12","Galb1234",user);
    }

    @Test
    @Category({UnitTests.class})
    public void changePasswordFailed13Unit() throws Exception {
        Account account=accountManager.createAccount("userName", "passUser123","Fan");
        User user=accountManager.getUser("userName","Fan");
        String message = "";
        try {
            accountManager.changePassword("userName", "passUser123",user);
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals(message, "no user");
        accountManager.removeAccount(account);
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
        Account account=accountManager.createAccount("userName", "FanUser12","Fan");
        User u=(accountManager.login("userName", "FanUser12"));
        assertNull(u);
        accountManager.removeAccount(account);
    }

    @Test
    @Category({UnitTests.class})
    public void loginFailed16Unit() {
        String message="";
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
    public void loginFailed17Unit(){
        String message="";
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
    public void removeAccountSuccess19Unit(){
        try {
            accountManager.createAccount("userName","passWord123","Fan");
            assertTrue(accountManager.removeAccount(accountManager.getAccount("userName")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
    @Category({UnitTests.class})
    public void removeAccountFailed11Unit() {
        assertFalse(accountManager.removeAccount(accountManager.getAccount("userName")));
    }

    @Test
    @Category({UnitTests.class})
    public void removeAccountFailed20Unit() {
        assertFalse(accountManager.removeAccount(null));
    }

}