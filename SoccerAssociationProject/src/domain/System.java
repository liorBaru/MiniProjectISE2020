package domain;

import java.util.LinkedList;
import java.util.List;


public class System
{
    private static System system;
    private List<League> leagues;
    private List<Team> teams;
    private List<SystemManager> systemManagers;


    public static void initSystem(String userName, String password, String name)
    {
        SystemManager systemManager = new SystemManager(userName,password,name);
        system=getInstance();
        system.systemManagers.add(systemManager);


    }


    private System ()
    {
        leagues = new LinkedList<>();
        teams = new LinkedList<>();
        systemManagers= new LinkedList<>();
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
