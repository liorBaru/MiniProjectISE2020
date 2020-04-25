package service;
import domain.manageLeagues.IFA;
import domain.Asset.Owner;
import domain.manageLeagues.League;
import domain.manageLeagues.LeagueCalcolator;
import domain.manageLeagues.Season;

import java.util.Date;
import java.util.InputMismatchException;


public class IFAController
{
    private IFA Ifa;
    public void IFAController(IFA ifa)
    {
        Ifa=ifa;
    }
    /**
     * @author: David Zaltsman
     * @desc: add new league
     * @param name
     * @param level
     */
    public void newLeague(String name, int level) {
        try {
            Ifa.addLeague(name, level);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input");
        }
    }

    /**
     * @author: David Zaltsman
     * @desc: add new season
     * @param year
     * @param start
     */
    public void newSeason(int year, boolean start) {
        //TODO:
        // 1.fix use case 9.2.1 year-string to year-int.
        try {
            Ifa.addSeason(year, start);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input");
        }
    }
    /**
     * @author: David Zaltsman
     * @desc: add season to league
     * @param league - the league that we want to add her season
     * @param season - the season that we want to add
     */
    public void addSeasonToLeague(League league, Season season) {
        //TODO:
        // 1.add to U.C 9.2.2 the functionality of adding seasonInfo
        try {
            Ifa.addSeasonToLeague(league, season);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input");
        }
    }

    public void updatePolicyToLeague(League league, Season season , LeagueCalcolator leaguePolicy) {
        //TODO:
        // 1.add to U.C 9.5 parameter to function : Season season , LeagueCalculator leaguePolicy
        try {
            Ifa.updatePolicyToLeague(league, season,leaguePolicy);
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
    public boolean addPlayer(String pName, Date birthDay, String password, String userName) throws Exception{
        if( pName!=null && birthDay!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                Ifa.addPlayer(pName, birthDay,password, userName);
            }
        }
        return false;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public boolean addCoach( String cName,String password, String userName) throws Exception{
        if(cName!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                Ifa.addCoach(cName,password, userName);
            }
        }
        return false;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public boolean addReferee( String rName,String password, String userName, String type) throws Exception{
        if( rName!=null  && password!=null && userName!=null && type!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                Ifa.addReferee(rName,password, userName, type);
            }
        }
        return false;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public boolean addIFA( String ifaName, String password, String userName) throws Exception{
        if( ifaName!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                Ifa.addNewIFA( ifaName, password, userName);
            }
        }
        return false;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public boolean addOwner(String OwnerName, String password, String userName) throws Exception{
        if( OwnerName!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                Ifa.addOwner( OwnerName, password, userName);
            }
        }
        return false;
    }

    /**
     * @author: Lior Baruchovich
     * @desc:
     * @param
     * @param
     */
    public boolean addTeam(String teamName, Owner owner) throws Exception{
        if( teamName!=null  && owner!=null){
            Ifa.addTeam( owner, teamName);
        }
        return false;
    }

    /**
     * @author: Lior Baruchovich
     * @desc: chech if the user nane and password is legal
     * @param
     * @param
     */
    public boolean isPassAndUserNIsLegal(String password, String userName){
        if(password!=null && userName!=null){
            if(userName.length()>=6 && password.length()>=8 ){ //add pass contain capital small and number ??
                return true;
            }
        }
        return false;
    }

}
