package domain.manageLeagues;

import domain.manageUsers.Account;
import domain.manageUsers.User;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public class IFA extends User

{
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

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addPlayer(String pName, Date birthDay,String password, String userName,List<String>positions)throws Exception{
        system.createNewPlayerUser(pName,birthDay,password,userName,positions);
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public void addCoach(String cName,String password, String userName,String training)throws Exception{
        system.createNewCoachUser(cName,password, userName,training);
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
    public void addReferee(String rName,String password, String userName, String type,String training) throws Exception{
        system.createNewRefereeUser(rName, password, userName, type,training);

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
    public void addTeam(String owner, String TName)throws Exception{
        system.createTeam(owner, TName);
    }

    public Account getAccount(){
        return account;
    }
}
