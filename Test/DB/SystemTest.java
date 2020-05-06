package DB;

import domain.manageUsers.AccountMangerStub;
import main.DB.IntegrationTests;
import main.DB.RegressionTests;
import main.DB.System;
import main.DB.UnitTests;
import main.domain.Asset.Fan;
import main.domain.Asset.SystemManager;
import main.domain.manageUsers.Account;
import main.domain.manageUsers.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Random;

import static org.junit.Assert.*;

public class SystemTest
{
    System system;
    AccountMangerStub accountManager;
    static boolean flag=false;
    String userName="MatanG";
    String password="Ga123456";
    String name="Matan";

    @Test
    @Category({ UnitTests.class})
    public void addBoardMember1Unit(){

        Account excepted=accountManager.account;
        SystemManager systemManager = new SystemManager(name,excepted);
        system.getSystemManagers().add(systemManager);
        Account account=accountManager.getAccount(userName);
        assertEquals(excepted,account);
    }

    @Before
    @Test
    public void initSystemUnit3()
    {
        try
        {
            System.initSystem("userAdmain","Password1","chen");
            system=System.getInstance();
            accountManager=new AccountMangerStub(system);
            assertTrue(system!=null);
        }
        catch (Exception e)
        {
            system=System.getInstance();
            accountManager=new AccountMangerStub(system);
        }
    }



    @Test
    @Category({ UnitTests.class})
    public void createNewFanUser2Unit() throws Exception {
        Account account = accountManager.createAccount(userName,password);
        User newUser = new Fan(name,account);
        account.setUser(newUser);
        assertEquals(newUser.getAccount(),accountManager.account);
    }

    @Test
    @Category({ UnitTests.class, RegressionTests.class})
    public void createAccountSuccess3Unit() throws Exception {
        int rand_int1 = new Random().nextInt(1000);
        User user = system.createNewFanUser("user",rand_int1+"userName","Password1");
        assertTrue(user.getAccount().getUserName().equals(rand_int1+"userName"));
    }


    @Test
    @Category({ IntegrationTests.class, RegressionTests.class})
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
    @Category({ IntegrationTests.class, RegressionTests.class})
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



}