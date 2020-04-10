package domain;

import java.util.LinkedList;
import java.util.List;


public class System
{
    private static System system;
    private List<League> leagues;
    private List<Team> teams;
    private List<SystemManager> systemManagers;
    private AccountManager accountManager;




    public static void initSystem(String userName, String password, String name)
    {
        Account account =new Account(userName,password);
        SystemManager systemManager = new SystemManager(account,name);
        system=getInstance();
        system.systemManagers.add(systemManager);
    }

    public User login(String userName, String password)
    {
        if(userName!=null && password!=null)
        {
            return accountManager.getUser(userName,password);
        }
        return null;
    }

    public User register(String userName, String password, String name)
    {
        if(userName!=null && password!=null && name!=null)
        {
            if(userName.length()>5 && password.length()>5)
            {
                if(accountManager.containsUserName(userName)==false)
                {
                    Account account =new Account(userName,password);
                    User user = new Fan(name,account);
                    return user;
                }
                {
                    //throw name already exsist , try diffrent Name
                }
            }
            // throw illigal Name/password
        }
        return null;
    }


    private System ()
    {
        leagues = new LinkedList<>();
        teams = new LinkedList<>();
        systemManagers= new LinkedList<>();
        accountManager = new AccountManager();
    }

    public static System getInstance()
    {
        if(system!=null)
        {
            return system;
        }
        else
        {
            return system=new System();
        }
    }
}
