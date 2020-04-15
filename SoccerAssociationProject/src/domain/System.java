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


    public Account addBoardMember(String userName)
    {
        return accountManager.getAccount(userName);
    }

    public void addRefree(Refree refree)
    {
        if(refree!=null)
        {
            refrees.add(refree);
        }
    }

    public Account getTeamMemberAccount(String userName)
    {
        return accountManager.getAccount(userName);
    }

    public Account getRefreeAccount(String userName)
    {
        return accountManager.getAccount(userName);
    }



    public void createNewFanUser(String name, String userName,String password)throws Exception
    {
         Account account = accountManager.createAccount(userName,password);
         User newUser = new Fan(name,account);
         account.setUser(newUser);
    }

    public User login (String username, String password) throws Exception
    {
        return accountManager.login(username,password);
    }

    public static void initSystem(String userName, String password, String name) throws Exception {
        try {
            system=getInstance();
            system.connectToDB();
            Account account = system.accountManager.createAccount(userName,password);
            SystemManager systemManager = new SystemManager(name,account);
            system.systemManagers.add(systemManager);
            system.connectToIFA();
            system.connectToTaxLaW();
        }
        catch (Exception e)
        {
            system=null;
            throw e;
        }
    }

    private void connectToIFA() throws Exception {
      // throw new Exception("cant connect to IFA systems");
    }

    private void connectToTaxLaW() throws Exception
    {

        //throw new Exception("cant connect to tax law system");

    }
    private void connectToDB () throws Exception {

        //throw new Exception("cant connect to dataBase");
    }

    private System ()
    {
        leagues = new LinkedList<>();
        teams = new LinkedList<>();
        systemManagers= new LinkedList<>();
        accountManager = new AccountManager(this);
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

    public void addPlayer(Player player)
    {
        if(player!=null)
        {
            players.add(player);
        }
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

}
