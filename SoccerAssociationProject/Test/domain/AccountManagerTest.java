package domain;

import DB.System;
import domain.manageUsers.Guest;
import domain.manageUsers.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountManagerTest
{
    private System system =System.getInstance();
    private User fan;
    static boolean flag=true;

    @Before
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
    public void createRealAccount1Integration() throws Exception {
        User user = system.createNewFanUser("user","userName","Password1");
        assertTrue(user.getAccount().getUserName().equals("userName"));
    }


    @Test
    public void createInvalidPasswordAccount2Integration()
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
    public void createDuplicateAccount3Integration()
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
    public void createAccountUsernameNotGood4Integration()
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
    public void changePasswordSuccess5Integration() throws Exception
    {
        fan.updatePassword("FanUser12","FanUser123");
        assertTrue(fan.getAccount().accountVerification("FanUser123"));
        fan.updatePassword("FanUser123","FanUser12");
    }

    @Test
    public void changePasswordFailure6Integration()
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
    public void loginSuccess7Integration() throws Exception
    {
        User user =fan.login("fanUser","FanUser12");
        assertTrue(user.equals(fan));

    }
    @Test
    public void loginFailure8Integration()
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
    public void register9Integration() throws Exception
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