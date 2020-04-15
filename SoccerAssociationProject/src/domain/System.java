package domain;

import java.util.LinkedList;
import java.util.List;


public class System
{
    private static System system;
    private List<League> leagues;
    private List<Team> teams;
    private List<Page> pages;
    private List<Asset> assetsExists;
    private List<Player> players;
    private List<SystemManager> systemManagers;
    private AccountManager accountManager;
    private List<Complaint> complaints;
    private List<Team> closedTeams;
    private List<Refree> refrees;
    private List<Coach> Coaches;
    //////////////////////////////////lior add
    private List<IFA> IFAes;

    public Account addBoardMember(String userName)
    {
        return accountManager.getAccount(userName);
    }

    public static void initSystem(String userName, String password, String name)
    {
        AccountManager accountManager = new AccountManager();
        accountManager.createAccount();
        SystemManager systemManager = new SystemManager(userName,password,name);
        system=getInstance();
        system.systemManagers.add(systemManager);
        //TODO:
        // Using try\catch to announce the user if some problem occurs.
        // 1.connect to DB
        // 2.connect to accounting IFA
        // 3.connect to tax law

    }

    private System ()
    {
        leagues = new LinkedList<>();
        teams = new LinkedList<>();
        systemManagers= new LinkedList<>();
        IFAes = new LinkedList<>();
    }

    public boolean closeTeamBySystemManager(String teamName)
    {
        for (Team team:teams)
        {
            if(team.getName()==teamName)
            {
                String details ="Team : "+teamName+" has been closed by the systemManager";
                Notification notification = new Notification(details);
                team.setClose(notification);
                closedTeams.add(team);
                return true;
            }
        }
        return false;
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

    public void closeTeam(Notification notification)
    {
        for (SystemManager systemManager:systemManagers)
        {
            systemManager.addNotification(notification);
        }
    }

    public List<Page> findPage(String pageName)
    {
        List<Page> releventPages = new LinkedList<>();
        for (Page page:pages)
        {
            if(page.getPageName().equals(pageName))
            {
                releventPages.add(page);
            }

        }
        return releventPages;
    }

    public void addComplaint(Complaint complaint)
    {
        if(complaint!=null)
        {
            complaints.add(complaint);
        }
    }

    public List<Complaint> getComplaints()
    {
        return complaints;
    }

    public boolean ansComplaint(Complaint complaint,String answer)
    {
        if(complaint.getStatus())
        {
            complaint.setAnswer(answer);
            return true;
        }
        return false;
    }

    public boolean removeUser(String userName)
    {
        Account account = accountManager.getAccount(userName);
        User user = account.getUser();
        if(user!=null)
        {
            user.removeUser();
            return true;
        }
        return false;
    }

    public Field addField(String name,Team team)
    {
        for (Asset asset : assetsExists)
        {
            if(asset.getName()==name && asset instanceof Field)
            {
                asset.setTeam(team);
                return (Field) asset;
            }
        }
        Field field = new Field(name);
        field.setTeam(team);
        assetsExists.add(field);
        return field;
    }
    //--------------------------------------------------------------------------------- getters
    public Player getPlayer(String name)
    {
        for (Player player:players)
        {
            if(player.getName()==name)
            {
                return player;
            }
        }
        return null;
    }
    public Account getTeamMemberAccount(String userName)
    {
        return accountManager.getAccount(userName);
    }

    public Account getRefreeAccount(String userName)
    {
        return accountManager.getAccount(userName);
    }

    public Account getIFAAccount(String name)
    {
        for (IFA ifa:IFAes)
        {
            if(ifa.getName()==name)
            {
                return ifa.getAccount();
            }
        }
        return null;
    }
    //-----------------------------------------------------------------------------------  add
    public void addPlayer(Player player)
    {
        if(player!=null)
        {
            players.add(player);
        }
    }
    public void addCoach(Coach coach)
    {
        if(coach!=null)
        {
            Coaches.add(coach);
        }
    }
    public void addRefree(Refree refree)
    {
        if(refree!=null)
        {
            refrees.add(refree);
        }
    }
    public void addIFA(IFA ifa)
    {
        if(ifa!=null)
        {
            IFAes.add(ifa);
        }
    }

}//class
