package main.DB;
import main.domain.Asset.*;
import main.domain.managePages.Page;
import main.domain.manageUsers.Account;
import main.domain.manageUsers.AccountManager;
import main.domain.Asset.Refree.LineRefree;
import main.domain.Asset.Refree.MainRefree;
import main.domain.Asset.Refree.Refree;
import main.domain.Asset.Refree.VarRefree;
import main.domain.manageUsers.User;
import main.domain.manageEvents.Complaint;
import main.domain.manageEvents.Notification;
import main.domain.manageLeagues.IFA;
import main.domain.manageLeagues.League;
import main.domain.manageLeagues.Season;
import main.domain.manageTeams.Team;

import java.util.*;


public class System
{
    private static System system;
    private AccountManager accountManager;
    private List<SystemManager> systemManagers;
    private List<IFA> ifaList;
    private List<League> leagues;
    private List<Season> seasons;
    private List<Team> teams;
    private List<Team> closedTeams;
    private List<Page> pages;
    private List<Asset> assetsExists;
    private List<Coach> coaches;
    private List<Owner> owners;
    private List<Player> players;
    private List<Refree> refrees;
    private List<Complaint> complaints;

    private System ()
    {
        leagues = new LinkedList<>();
        teams = new LinkedList<>();
        systemManagers= new LinkedList<>();
        accountManager = new AccountManager(this);
        pages=new LinkedList<>();
        assetsExists = new LinkedList<>();
        players = new LinkedList<>();
        seasons = new LinkedList<>();
        systemManagers = new LinkedList<>();
        complaints = new LinkedList<>();
        closedTeams = new LinkedList<>();
        refrees = new LinkedList<>();
        ifaList = new LinkedList<>();
        coaches=new LinkedList<>();
        owners=new LinkedList<>();
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

    public static void initSystem(String userName, String password, String name) throws Exception {
        try
        {
            system=getInstance();
           // system.connectToDB();
            Account account = system.accountManager.createAccount(userName,password);
            SystemManager systemManager = new SystemManager(name,account);
            system.systemManagers.add(systemManager);
           // system.connectToIFA();
          // system.connectToTaxLaW();
        }
        catch (Exception e)
        {
            system=null;
            throw e;
        }
    }


    public List<Team> getTeams()
    {
        return teams;
    }
    public List<IFA> getIfaList()
    {
        return this.ifaList;
    }
    public List<SystemManager> getSystemManagers()
    {
        return systemManagers;
    }


    public Fan createNewFanUser(String name, String userName, String password)throws Exception
    {
         Account account = accountManager.createAccount(userName,password);
         Fan newUser = new Fan(name,account);
         return newUser;
    }

    public User login (String username, String password) throws Exception
    {
        return accountManager.login(username,password);
    }

    public  SystemManager createNewSysteamManager(String userName,String password,String name) throws Exception
    {
        Account account=accountManager.createAccount(userName,password);
        SystemManager systemManager = new SystemManager(name,account);
        systemManagers.add(systemManager);
        return systemManager;
    }





    /**
     * @author: David Zaltsman
     * @desc: add new league to system. USECASE 9.1 -> if wrong deatials return appropriate message.
     * @param name - name of league
     * @param level - level of the league
     */
    public League addLeague(String name, int level)
    {
        if(level<0 || checkLeagueExist(level) ){
            throw new InputMismatchException("Wrong input");
        }
        League league=new League(name,level);
        this.leagues.add(league);
        return league;
    }

    /**
     * @author: David Zaltsman
     * @desc: add new Season to system. USECASE 9.2.1 -> if wrong deatials return appropriate message.
     * @param year- year of the season
     */
    public Season addSeason(int year)
    {
        if(year<1995 ){
            throw new InputMismatchException("Wrong input");
        }
        Season season=new Season(year);
        this.seasons.add(season);
        return season;
    }

    /**
     * @author: David Zaltsman
     * @desc: private function => check if the league is already exists
     * @param level- level of leage
     */
    //check if the league is alreay exits
    private boolean checkLeagueExist(int level) {
        for (League league: this.leagues)
        {
            if(league.getLevel()==level)
                return true;
        }
        return false;
    }

    public void closeTeam(Notification notification)
    {
        for (SystemManager systemManager:systemManagers)
        {
            systemManager.addNotification(notification);
        }
    }

    /**
     * gal
     * add page to pages and user to page followers
     * @param pageID
     * @return
     * @throws Exception
     */
    public boolean followPage(int pageID, Fan user) throws Exception {
        for (Page page:pages)
        {
            if(page.getPageID()==pageID)
            {
                page.addFollower(user);
                user.addPage(page);
                return true;
            }
        }
        throw new Exception("page dont exists, please try again");
    }

    /**
     * gal
     * remove user from page followers
     * @param page
     * @param user
     * @return
     */
    public boolean unFollowPage(Page page, Fan user) throws Exception {
        if(page!=null && user!=null)
        {
           return page.removeFollower(user);
        }
        return false;
    }

    public void addPage(Page page)
    {
        pages.add(page);
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

    /**
     * gal
     * write the complaint to DB
     * @param complaint
     */
    public void addComplaint(Complaint complaint)
    {
        if(complaint!=null)
        {
            complaints.add(complaint);
        }
    }

    public void changePassword(String oldPassword,String newPassword,User user) throws Exception {
        accountManager.changePassword(oldPassword,newPassword,user);
    }


    public boolean removeUser(String userName) throws Exception {
        Account account = accountManager.getAccount(userName);
        User user = account.getUser();
        if(user!=null)
        {
            user.removeUser();
            return accountManager.removeAccount(account);

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

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void createTeam(String ownerName, String Tname)throws Exception
    {
        Owner owner = system.getOwner(ownerName);

        if(owner.getTeam()!=null)
        {
            throw new Exception("the owner as alreadyTeam");
        }
        Team team = system.getTeam(Tname);
        if(team!=null)
        {
            throw new Exception("there is already team call " +team.getName()+" in the system");
        }
        List<Owner> owners =new LinkedList<>();
        owners.add(owner);
        team = new Team(owners, Tname);
        owner.setTeam(team);
        teams.add(team);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Player createNewPlayerUser(String pName, Date birthDay, String password, String userName,List<String> positions)throws Exception {
        Account pAccount = accountManager.createAccount(userName,password);
        List<String> position = new LinkedList<>();
        Player newUser = new Player(pAccount,pName, birthDay,positions);
        pAccount.setUser(newUser);
        players.add(newUser);
        return newUser;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Coach createNewCoachUser(String cName,String password, String userName,String training)throws Exception {
        Account cAccount = accountManager.createAccount(userName,password);
        Coach newUser = new Coach(cAccount,cName,training);
        cAccount.setUser(newUser);
        coaches.add(newUser);
        return newUser;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Refree createNewRefereeUser(String rName,String password, String userName, String type,String training)throws Exception {

        Account rAccount = accountManager.createAccount(userName,password);
        if(type=="Main")
        {
            Refree newUser = new MainRefree(rName,training,rAccount);
            rAccount.setUser(newUser);
            refrees.add ( newUser);
            return newUser;
        }
        else if(type=="Var")
        {
            Refree newUser = new VarRefree(rName,training,rAccount);
            rAccount.setUser(newUser);
            refrees.add (newUser);
            return newUser;
        }
        else if(type=="Line")
        {
            Refree newUser = new LineRefree(rName,training,rAccount);
            rAccount.setUser(newUser);
            refrees.add ( newUser);
            return newUser;
        }
        throw new Exception("Invalid type:"+type);
    }


    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public IFA createNewIFAUser(String ifaName, String password, String userName)throws Exception
    {
        Account ifaAccount = accountManager.createAccount(userName,password);
        IFA newUser = new IFA( ifaName,ifaAccount);
        ifaAccount.setUser(newUser);
        ifaList.add(newUser);
        return newUser;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Owner createNewOwnerUser(String ownerName,String password, String userName)throws Exception
    {
        Account ownerAccount = accountManager.createAccount(userName,password);
        Owner newUser = new Owner( ownerAccount, ownerName);
        ownerAccount.setUser(newUser);
        owners.add( (Owner)newUser);
        return newUser;
}


    public List<Season> getSeasons() {
        return seasons;
    }


    public List<League> getLeagues() {
        return leagues;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
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



    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Refree getRefree(String name)
    {
        for (Refree referee:refrees)
        {
            if(referee.getName()==name)
            {
                return referee;
            }
        }
        return null;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public IFA getIFA(String name)
    {
        for (IFA ifa:ifaList)
        {
            if(ifa.getName()==name)
            {
                return ifa;
            }
        }
        return null;
    }

    public Owner getOwner(String username) throws Exception {
        for (Owner owner:owners)
        {
            if(owner.getAccount().getUserName()==username)
            {
                return owner;
            }
        }
       throw new Exception("owner "+username+" is not an owner in the system");
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Coach getCoach(String name)
    {
        for (Coach coach:coaches)
        {
            if(coach.getName()==name)
            {
                return coach;
            }
        }
        return null;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Team getTeam(String teamName)
    {
        for (Team team:teams)
        {
            if(team.getName()==teamName)
            {
                return team;
            }
        }
        return null;
    }

    public League getLeague(League league)
    {
        Iterator<League> leaguesIterator = leagues.iterator();
        while (leaguesIterator.hasNext()) {
           if(leaguesIterator.next().equals(league))
               return leaguesIterator.next();
        }
        return null;
    }

    public AccountManager getAccountManager()
    {
        return this.accountManager;
    }


    /**
     * gal,
     * get all new complaint
     * system manager
     * @return
     */
    public List<Complaint> getNewComplaints()
    {
        List<Complaint> newComplaints=new LinkedList<>();
        for (Complaint complaint:complaints)
        {
            if(complaint.getStatus()==true)
            {
                newComplaints.add(complaint);
            }
        }
        return newComplaints;
    }
    /**
     * answer to complaint by systemManager
     * @param complaintID
     * @param answer
     * @throws Exception
     */
    public void ansComplaint(int complaintID,String answer) throws Exception
    {
        Complaint complaint =system.getComplaintByID(complaintID);
        if(complaint.getStatus())
        {
            complaint.setAnswer(answer);
            return;
        }
        throw new Exception("complaint has been answer already");
    }

    private Complaint getComplaintByID(int complaintID) throws Exception
    {
        for (Complaint complaint:complaints)
        {
            if(complaint.getComplaintID()==complaintID)
            {
                return complaint;
            }
        }
        throw new Exception("Complaint dont exists");
    }

    /**
     * close team by system manager
     * @param teamName
     * @throws Exception
     */

    public void closeTeamBySystemManager(String teamName) throws Exception {
        for (Team team:teams)
        {
            if(team.getName()==teamName)
            {
                String details ="Team : "+teamName+" has been closed by the systemManager";
                Notification notification = new Notification(details);
                team.setClose(notification);
                teams.remove(team);
                closedTeams.add(team);
                return;
            }
        }
        throw new Exception("Team "+teamName+" is not active team in the system");
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



}
