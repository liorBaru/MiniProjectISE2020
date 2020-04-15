package domain;

import java.util.Date;
import java.util.List;

public class IFA extends User

{

    public IFA(String name, Account account)
    {
        super(name,account);
    }

    @Override
    public void removeUser()
    {
        system.removeUser(this.name);
    }

    public boolean createNewLeugue(String name, int level){return false;}
    public boolean addSeasonToLeague(String LeagueName, int year){return false;}
    public boolean addReferee( User user){return false;}
    public void addRefereeToLeague(String refree, String League){}
    public boolean removeRefree(String refree){return false;}
    public boolean updatePointsPolicy(LeagueCalcolator calcolator, String league, int season){return false;}
    public boolean updateGamePolicy(GameScheduale scheduale, String league, int year){return false;}
    public boolean startSeason(String league, int season){return false;}


    public boolean createRefree(String userName, String name, String type, String training)
    {
        Account account =system.getRefreeAccount(userName);
        if(account!=null)
        {
            if(type=="Main")
            {
                Refree refree = new MainRefree(name,training,account);
                system.addRefree(refree);
            }
            else if(type=="Var")
            {
                Refree refree = new VarRefree(name,training,account);
                system.addRefree(refree);
            }
            else
            {
                Refree refree = new LineRefree(name,training,account);
                system.addRefree(refree);
            }
            return true;
        }
        return false;
    }

    //---------------------------------------------------------------------------------------------------
    public void addPlayer(String pName, Date birthDay,String password, String userName){
        Account pAccount = new Account(userName, password);
        Player player= new Player(pAccount,pName, birthDay);

        pAccount.setUser(player);
        system.addPlayer(player);
    }
    public void addCoach(String cName, Date birthDay,String password, String userName){
        Account cAccount = new Account(userName, password);
        Coach coach= new Coach( cAccount,cName, birthDay);

        cAccount.setUser(coach);
        system.addCoach(coach);
    }
    public void addNewIFA( String ifaName, String password, String userName){
        Account ifaAccount =system.getIFAAccount(userName);
        if(ifaAccount==null){
            ifaAccount = new Account(userName, password);
        }
        IFA ifa= new IFA( ifaName,ifaAccount);

        ifaAccount.setUser(ifa);
        system.addIFA(ifa);
    }

    public boolean addRefree(String rName,Date birthDay,String password, String userName, String type)
    {
        Account rAccount =system.getRefreeAccount(userName);
        if(rAccount==null){
            rAccount = new Account(userName, password);
        }

        if(type=="Main")
        {
            Refree refree = new MainRefree(rName,rAccount, birthDay);
            rAccount.setUser(refree);
            system.addRefree(refree);
        }
        else if(type=="Var")
        {
            Refree refree = new VarRefree(rName,rAccount, birthDay);
            rAccount.setUser(refree);
            system.addRefree(refree);
        }
        else
        {
            Refree refree = new LineRefree(rName,rAccount, birthDay);
            rAccount.setUser(refree);
            system.addRefree(refree);
        }
        return true;
    }



/*
    public boolean addTeamMemberToTeam(String userName, String name, Date birthDate, int contract, double salary, Team team)
    {
        Account account =system.getTeamMemberAccount(userName);
        if(account!=null)
        {
            User user =account.getUser();
            if(user==null)
            {
                Player player = new Player(account,name,team,contract,salary,birthDate);
                system.addPlayer(player);
                team.addAsset(player);
                team.addStaffMember(player);
                return true;
            }
            else if(user instanceof Player)
            {
                ((Player)user).setTeam(team,contract,salary);
                team.addStaffMember((Player)user);
                team.addAsset((Player)user);
                return true;
            }
            else if(user instanceof Owner)
            {
                Owner owner =(Owner)user;
                if(owner.getTeam()==team)
                {
                    // to do
                    return true;
                }
            }
        }
        return false;
    }
*/


}//lass

