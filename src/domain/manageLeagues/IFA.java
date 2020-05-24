package domain.manageLeagues;

import DataAccess.IfaDaoSql;
import DataAccess.LeaguesDaoSql;
import DataAccess.SeasonInfoDaoSql;
import DataAccess.TeamDaoSql;
import domain.Asset.Coach;
import domain.Asset.Owner;
import domain.Asset.Player;
import domain.Asset.Refree.LineRefree;
import domain.Asset.Refree.MainRefree;
import domain.Asset.Refree.Refree;
import domain.Asset.Refree.VarRefree;
import domain.manageTeams.Team;
import domain.manageUsers.Account;
import domain.manageUsers.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

public class IFA extends User
{
    private static TeamDaoSql teamDauSql;
    private static IfaDaoSql ifaDaoSql;
    private static LeaguesDaoSql leaguesDaoSql;
    private static SeasonInfoDaoSql seasonInfoDaoSql;
    private static int yearMin=2019;

    public IFA(String name, Account account) throws SQLException {
        super(name,account);
        kind="IFA";
        String [] key={account.getUserName(),name};
        ifaDaoSql.save(key);
    }

    public IFA(String[] params)
    {
        this.account=system.getAccountManager().getAccount(params[0]);
        this.name=params[1];
    }

    @Override
    public LinkedList<String> showPersonalDetails()
    {
        LinkedList<String> userDetails= super.showPersonalDetails();
        userDetails.addFirst("IFA");
        return userDetails;
    }

    public static User getIFADromDB(String[] key) throws Exception {
        List<String[]> ifaList=ifaDaoSql.get(key);
        if(ifaList==null || ifaList.isEmpty())
        {
            throw new Exception("wrong arguments");
        }
        String[] ifaString=ifaList.get(0);
        return createIFAFromDB(ifaString);
    }

    public static IFA createIFAFromDB(String[] ifaString) throws Exception {

        IFA ifa = new IFA(ifaString);
        return ifa;
    }

    @Override
    protected void update() throws SQLException {
        String[] key={account.getUserName(),name};
        ifaDaoSql.update(key);
    }



    /**
     * remove IFA from the system by system manger
     * @throws Exception
     */
    @Override
    public boolean removeUser() throws Exception
    {
        throw new Exception("cant delete this type of user");
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addPlayer(String pName, Date birthDay,String password, String userName,List<String>positions)throws Exception
    {
        Account pAccount = system.getAccountManager().createAccount(userName,password,"Player");
        Player newUser = new Player(pAccount,pName, birthDay,positions);
        pAccount.setUser(newUser);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addCoach(String cName,String password, String userName,String training)throws Exception{
        Account cAccount = system.getAccountManager().createAccount(userName,password,"Coach");
        Coach newUser = new Coach(cAccount,cName,training);
        cAccount.setUser(newUser);
    }


    public void addLeague(String name, int level) {
        try
        {
            if(level<=0){
                throw new InputMismatchException("Wrong League level, league level most be unique and bigger than 0");
            }
            League league=new League(name,level);
        }
        catch (Exception e) {
            throw new InputMismatchException("Wrong input");
        }
    }

    public void addSeasonToLeague(String league, int season) throws Exception {
        if(league.isEmpty() || season<yearMin)
            throw new Exception("worng arguments");
        String[] key={league,String.valueOf(season)};
        seasonInfoDaoSql.save(key);
    }

    public void updatePolicyToLeague(String league, int season , LeagueCalculator leaguePolicy) throws Exception {
        if(leaguePolicy==null||season<yearMin || league.isEmpty())
            throw new Exception("Invalid arguments");
        String [] key={league,String.valueOf(season),leaguePolicy.getName()};
        seasonInfoDaoSql.update(key);
    }


    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addNewIFA( String ifaName, String password, String userName) throws Exception{
        Account account=system.getAccountManager().createAccount(userName,password,"IFA");
        IFA ifa = new IFA(ifaName,account);
        account.setUser(ifa);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addReferee(String rName,String password, String userName, String type,String training) throws Exception{
        Account account=system.getAccountManager().createAccount(userName,password,"Refree");
        Refree refree=null;
        if(type.equals("Var"))
        {
            refree = new VarRefree(rName,training,account);
        }
        else if(type.equals("Line"))
        {
            refree = new LineRefree(rName,training,account);
        }
        else if(type.equals("Main"))
        {
            refree = new MainRefree(rName,training,account);
        }
        else
        {
            throw new Exception("wrong arguments");
        }
        account.setUser(refree);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addOwner(String oName,String password, String userName)throws Exception{
        Account account=system.getAccountManager().createAccount(userName,password,"Owner");
        Owner owner = new Owner(account,oName);
        account.setUser(owner);
    }


    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addTeam(String ownerName, String TName)throws Exception
    {
        String [] key={ownerName};
        Owner owner=Owner.getOwnerFromDB(key);
        if(owner.getTeam()!=null)
        {
            throw new Exception("the owner as already team");
        }
        List<Owner> owners = new LinkedList<>();
        owners.add(owner);
        Team team = new Team(owners, TName);
        owner.setTeam(team);
    }
}

