package domain;

import org.junit.Assert;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;

import static org.junit.Assert.*;

public class AccountManagerTest
{
    System system;
    AccountManager accountManager;
    User fan;
    public void setUp() throws Exception {
        system =System.getInstance();
        AccountManager accountManager = new AccountManager(system);
        Account account = accountManager.createAccount("fanUser","FanUser12");
        fan = new Fan("fan",account);
    }
    @Test
    public void createRealAccount() throws Exception {
        accountManager.createAccount("userName","Password1");
        assertTrue(accountManager.getAccount("userName")!=null);
    }
    @Test
    public void createInvalidPasswordAccount()
    {
        String message="";
        try
        {
            accountManager.createAccount("userName1","password1");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
       assertEquals(message,"Invalid password");
    }
    @Test
    public void createDuplicateAccount()
    {
        String message="";
        try
        {
            accountManager.createAccount("userName2","Password1");
            accountManager.createAccount("userName2","Password1");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"Invalid username, userName already exists please try different username");
    }

    public void createAccountUsernameNotGood()
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
    public void changePasswordSuccess() throws Exception
    {
        fan.updatePassword("FanUser12","FanUser123");
        assertTrue(accountManager.getAccount("fanUser").accountVerification("FanUser123"));
        fan.updatePassword("FanUser123","FanUser12");
    }

    @Test
    public void changePasswordFailure()
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
       assertEquals(message,"wrong password");
    }

    @Test
    public void loginSuccess() throws Exception
    {
        User user =fan.login("fanUser","FanUser12");
        assertTrue(user.equals(fan));

    }
    @Test
    public void loginFailure()
    {
        String message="";
        try
        {
            User user =fan.login("fffdsd","FanUser12");

        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message," wrong userName or password, please try again");
    }
    @Test
    public void register() throws Exception {
        Guest guest = new Guest();
        guest.register("guest","guestUser","guestUser1");
        assertTrue(accountManager.getAccount("guestUser")!=null);
    }



}