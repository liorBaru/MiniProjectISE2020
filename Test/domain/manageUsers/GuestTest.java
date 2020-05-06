package domain.manageUsers;

import main.DB.System;
import main.DB.UnitTests;
import main.domain.manageUsers.AccountManager;
import main.domain.manageUsers.Guest;
import main.domain.manageUsers.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class GuestTest {

    private System system;
    private Guest guest = new Guest();;
    private AccountManager accountManager;
    private static boolean flag=true;

    @Override
    public String toString() {
        return super.toString();
    }

    @Before
    public void setUp() throws Exception
    {
        if(flag)
        {
            system =System.getInstance();
            accountManager =system.getAccountManager();
            system.createNewFanUser("fan","userName12345", "passUser123");
            flag=false;
        }

    }

    @Test
    @Category({UnitTests.class})
    public void loginSuccess1Unit()
    {
        try
        {
            assertNotNull(guest.login("userName12345", "passUser123"));
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
        guest.login(null, "passUser123");
    }

    @Test
    @Category({UnitTests.class})
    public void registerSuccess3Unit() throws Exception
    {
        User u=(guest.register("matanGad2020","matan2020Gad2020", "passUser123"));
        assertNotNull(u);
    }

    @Test (expected = Exception.class)
    @Category({UnitTests.class})
    public void registerFailed4Unit() throws Exception
    {
        guest.register(null,"userName", "passUser123");
    }

}