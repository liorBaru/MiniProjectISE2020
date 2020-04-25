package domain.manageUsers;

import DB.System;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuestTest {

    private System system =System.getInstance();
    private Guest guest;
    private AccountManager accountManager;


    @Before
    public void setUp() throws Exception {
        guest = new Guest();
        accountManager = new AccountManager(system);
        accountManager.createAccount("userName", "passUser123");
    }

    @Test
    public void loginSuccess1Unit()
    {
        try {
            assertNotNull(guest.login("userName", "passUser123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (expected = Exception.class)
    public void loginFailed2Unit() throws Exception {

        guest.login(null, "passUser123");
    }

    @Test
    public void registerSuccess3Unit() throws Exception {
        User u=(guest.register("matanGad2020","matan2020Gad2020", "passUser123"));
      assertNotNull(u);
    }

    @Test (expected = Exception.class)
    public void registerFailed4Unit() throws Exception {
        guest.register(null,"userName", "passUser123");
    }

}