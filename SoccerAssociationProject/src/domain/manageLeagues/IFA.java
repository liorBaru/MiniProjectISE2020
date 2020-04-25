package domain.manageLeagues;

import domain.manageUsers.Account;
import domain.Asset.Owner;
import domain.Asset.Refree.LineRefree;
import domain.Asset.Refree.MainRefree;
import domain.Asset.Refree.Refree;
import domain.Asset.Refree.VarRefree;
import domain.manageUsers.User;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public class IFA extends User

{
    private static List<Refree> refrees;

    public IFA(String name, Account account)
    {
        super(name,account);
    }

    /**
     * remove IFA from the system by system manger
     * @throws Exception
     */
    @Override
    public void removeUser() throws Exception
    {
        system.removeUser(this.name);
    }

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
    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addPlayer(String pName, Date birthDay,String password, String userName)throws Exception{
        system.createNewPlayerUser(pName, birthDay,password, userName);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addCoach(String cName,String password, String userName)throws Exception{
        system.createNewCoachUser(cName,password, userName);
    }


    public void addLeague(String name, int level) {
        try {
            system.addLeague(name, level);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input");
        }
    }

    public void addSeason(int year) {
        try {
            system.addSeason(year);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input");
        }
    }

    public void addSeasonToLeague(League league, Season season) {
        try {
            League curLeague= system.getLeague(league);
            if(curLeague!=null){
                curLeague.addSeasonToLeague(season);
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input");
        }
    }

    public void updatePolicyToLeague(League league, Season season , LeagueCalculator leaguePolicy) {
        //TODO:
        // 1.add to U.C 9.5 paramater to function : Season season , LeagueCalculator leaguePolicy
        try {
            League curLeague= system.getLeague(league);
            if(curLeague!=null){
                curLeague.updatePolicyToLeague(season,leaguePolicy);
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input");
        }
    }


    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addNewIFA( String ifaName, String password, String userName) throws Exception{
        system.createNewIFAUser(ifaName, password, userName);
    }
    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addReferee(String rName,String password, String userName, String type) throws Exception{
        system.createNewRefereeUser(rName, password, userName, type);

    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addOwner(String oName,String password, String userName)throws Exception{
        system.createNewOwnerUser(oName,password, userName);
    }


    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addTeam(Owner owner, String TName)throws Exception{
        system.addTeam(owner, TName);
    }

    public Account getAccount(){
        return account;
    }
}
