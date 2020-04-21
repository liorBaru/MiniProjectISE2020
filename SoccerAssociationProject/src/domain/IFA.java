package domain;

import java.util.Date;
import java.util.List;

public class IFA extends User

{
    private static List<Refree> refrees;

    public IFA(String name, Account account)
    {
        super(name,account);
    }

    @Override
    public void removeUser() throws Exception {
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
    public void addRefree(String rName,String password, String userName, String type) throws Exception{
        system.createNewRefreeUser(rName, password, userName, type);

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
