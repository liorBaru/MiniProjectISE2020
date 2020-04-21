package domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccountManagerTest
{
    private System system =System.getInstance();
    private User fan;
    static boolean flag=true;

    public void setUp() throws Exception {
       if(flag)
       {
           fan = system.createNewFanUser("fan","fanUser","FanUser12");
           flag=false;
       }
       else
       {
           fan=system.login("fanUser","FanUser12");
       }

    }
    @Test
    public void createRealAccount() throws Exception {
        User user = system.createNewFanUser("user","userName","Password1");
        assertTrue(user.account.getUserName().equals("userName"));
    }
    @Test
    public void createInvalidPasswordAccount()
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
    public void createDuplicateAccount()
    {
        String message="";
        try
        {
            system.createNewFanUser("name","userName2","Password1");
            system.createNewFanUser("name","userName2","Password1");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"Invalid username, userName already exists please try different username");
    }
    @Test
    public void createAccountUsernameNotGood()
    {
        String message="";
        try
        {
            system.createNewFanUser("name","user","Password1");
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
        try
        {
            setUp();
        }
        catch (Exception e)
        {

        }
        fan.updatePassword("FanUser12","FanUser123");
        assertTrue(fan.account.accountVerification("FanUser123"));
        fan.updatePassword("FanUser123","FanUser12");
    }

    @Test
    public void changePasswordFailure()
    {
        String message="";
        try
        {
            setUp();
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
        try
        {
            setUp();
        }
        catch (Exception e)
        {

        }
        User user =fan.login("fanUser","FanUser12");
        assertTrue(user.equals(fan));

    }
    @Test
    public void loginFailure()
    {
        String message="";
        try
        {
            setUp();
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
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
    public void register() throws Exception
    {
        Guest guest = new Guest();
        guest.register("guest","guestUser","guestUser1");
        String message="";
        try
        {
            guest.register("guest","guestUser","guestUser1");
        }

        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"Invalid username, userName already exists please try different username");
    }



}