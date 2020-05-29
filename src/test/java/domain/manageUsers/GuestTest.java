package domain.manageUsers;

import DataAccess.System;
import DataAccess.UnitTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class GuestTest {

    private Guest guest = new Guest();;

    @Override
    public String toString() {
        return super.toString();
    }

    @Test
    @Category({UnitTests.class})
    public void loginSuccess1Unit()
    {
        try
        {
            assertNotNull(guest.login("fanTest", "Galb1234"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test (expected = Exception.class)
    @Category({UnitTests.class})
    public void loginFailed2Unit() throws Exception
    {
        String message="";
        try
        {
            guest.login(null, "passUser123");
        }
        catch (Exception e)
        {
           message=e.getMessage();
           e.printStackTrace();
        }
        assertEquals("message",message);
    }

    @Test
    @Category({UnitTests.class})
    public void registerSuccess3Unit() throws Exception
    {
        try
        {
            boolean bool=(guest.register("matanGad2020","matan2020Gad2020", "passUser123"));
            assertTrue(bool);
            System system=System.getInstance();
            AccountManager accountManger=system.getAccountManager();
            accountManger.removeAccount(accountManger.getAccount("matan2020Gad2020"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test (expected = Exception.class)
    @Category({UnitTests.class})
    public void registerFailed4Unit()
    {
        String message="";
        try
        {
            guest.register(null,"userName", "passUser123");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            message=e.getMessage();
        }
        assertEquals("message",message);
    }

}