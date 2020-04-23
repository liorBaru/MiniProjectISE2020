package DB;

import domain.AccountMangerStub;
import domain.Asset.Fan;
import domain.Asset.SystemManager;
import domain.manageUsers.Account;
import domain.manageUsers.AccountManager;
import domain.manageUsers.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class SystemTest {
    System system=System.getInstance();
    AccountMangerStub accountManager=new AccountMangerStub(system);
    String userName="MatanG";
    String password="Ga123456";
    String name="Matan";

    @Test
    public void addBoardMember1TestUnit(){

        Account excepted=accountManager.account;
        SystemManager systemManager = new SystemManager(name,excepted);
        system.getSystemManagers().add(systemManager);
        Account account=accountManager.getAccount(userName);
        assertEquals(excepted,account);
    }
    @Test
    public void addBoardMember1TestIntegration() throws Exception {

       Account account= system.addBoardMember("gal123","Gal12345","gal");
       assertEquals(system.getSystemManagers().get(0).getAccount(),account);

    }
    @Test
    public void createNewFanUser2TestUnit() throws Exception {
        Account account = accountManager.createAccount(userName,password);
        User newUser = new Fan(name,account);
        account.setUser(newUser);
        assertEquals(newUser.getAccount(),accountManager.account);
    }

    @Test
    public void createAccountSuccess3Unit() throws Exception {
        User user = system.createNewFanUser("user","userName","Password1");
        assertTrue(user.getAccount().getUserName().equals("userName"));
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



}