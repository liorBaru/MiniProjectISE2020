package domain;
import java.util.Date;
import java.util.InputMismatchException;
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
    private List<Season> seasons;
    private List<SystemManager> systemManagers;
    private AccountManager accountManager;
    private List<Complaint> complaints;
    private List<Team> closedTeams;
    private List<Refree> refrees;

    private List<IFA> IFAes;
    private List<Coach> Coaches;
    private List<Owner> Owners;

    private System ()
    {
        leagues = new LinkedList<>();
        teams = new LinkedList<>();
        systemManagers= new LinkedList<>();
        accountManager = new AccountManager(this);
        IFAes = new LinkedList<>();
        Coaches = new LinkedList<>();
        Owners = new LinkedList<>();
    }


    public Account addBoardMember(String userName,String password, String name) {
        SystemManager systemManager = new SystemManager(name,new Account(userName, password));
        system = getInstance();
        system.systemManagers.add(systemManager);
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
     * @param start- if start ture so we cant modify and changes at seasoninfo
     */
    public Season addSeason(int year, Boolean start)
    {
        if(year<1995 ){
            throw new InputMismatchException("Wrong input");
        }
        Season season=new Season(year,start);
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
    //---------------------------------------------------------------------------------------------lior part
    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void createNewPlayerUser(String pName, Date birthDay, String password, String userName)throws Exception {
        Account pAccount = accountManager.createAccount(userName,password);
        User newUser = new Player(pAccount,pName, birthDay);
        pAccount.setUser(newUser);
        system.addPlayer( (Player) newUser);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void createNewCoachUser(String cName,String password, String userName)throws Exception {
        Account cAccount = accountManager.createAccount(userName,password);
        User newUser = new Coach(cAccount,cName);
        cAccount.setUser(newUser);
        system.addCoach( (Coach) newUser);

    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void createNewRefreeUser(String rName,String password, String userName, String type)throws Exception {
        Account rAccount =system.getRefreeAccount(userName);
        if(rAccount==null){
            rAccount = accountManager.createAccount(userName,password);
        }

        if(type=="Main")
        {
            User newUser = new MainRefree(rName,rAccount);
            rAccount.setUser(newUser);
            system.addRefree( (Refree) newUser);
        }
        else if(type=="Var")
        {
            User newUser = new VarRefree(rName,rAccount);
            rAccount.setUser(newUser);
            system.addRefree( (Refree)newUser);
        }
        else
        {
            User newUser = new LineRefree(rName,rAccount);
            rAccount.setUser(newUser);
            system.addRefree( (Refree)newUser);
        }
    }


    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void createNewIFAUser(String ifaName, String password, String userName)throws Exception {
        Account ifaAccount =system.getIFAAccount(userName);
        if(ifaAccount==null){
            ifaAccount = accountManager.createAccount(userName,password);
        }
        User newUser = new IFA( ifaName,ifaAccount);
        ifaAccount.setUser(newUser);
        system.addIFA( (IFA)newUser);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void createNewOwnerUser(String oName,String password, String userName)throws Exception {
        Account ownerAccount =system.getOwnerAccount(userName);
        if(ownerAccount==null){
            ownerAccount = accountManager.createAccount(userName,password);
        }
        User newUser = new Owner( ownerAccount, oName);
        ownerAccount.setUser(newUser);
        system.addOwner( (Owner)newUser);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addTeam(Owner owner, String Tname)throws Exception {
        Team tean = new Team(owner, Tname);
        owner.addTeam(tean);
        system.addTeam( tean );
    }
    //--------------------------------------------------------------------------------- getters

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
    public Account getRefreeAccount(String userName)
    {
        return accountManager.getAccount(userName);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
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

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public Account getOwnerAccount(String name)
    {
        for (Owner owner:Owners)
        {
            if(owner.getName()==name)
            {
                return owner.getAccount();
            }
        }
        return null;
    }
    //-----------------------------------------------------------------------------------  add

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addPlayer(Player player)
    {
        if(player!=null)
        {
            players.add(player);
        }
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addCoach(Coach coach)
    {
        if(coach!=null)
        {
            Coaches.add(coach);
        }
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addRefree(Refree refree)
    {
        if(refree!=null)
        {
            refrees.add(refree);
        }
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addIFA(IFA ifa)
    {
        if(ifa!=null)
        {
            IFAes.add(ifa);
        }
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addOwner(Owner owner)
    {
        if(owner!=null)
        {
            Owners.add(owner);
        }
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addTeam(Team team)
    {
        if(team!=null)
        {
            teams.add(team);
        }
    }
}//class
