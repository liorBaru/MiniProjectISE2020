package service;
import domain.*;
import domain.System;

import java.util.InputMismatchException;


public class IFAController {
    System system=System.getInstance();

    /**
     * @author: David Zaltsman
     * @desc: aadd new league
     * @param name
     * @param level
     */
    public void newLeague(String name, int level) {
        try {
            system.addLeague(name, level);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input");
        }
    }

    /**
     * @author: David Zaltsman
     * @desc: aadd new season
     * @param year
     * @param start
     */
    public void newSeason(int year, boolean start) {
        //TODO:
        // 1.fix use case 9.2.1 year-string to year-int.
        try {
            system.addSeason(year, start);
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
        // 1.add to U.C 9.2.2 the funcionallity of adding seasonInfo
        try {
            league.addSeasonToLeague(league, season);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input");
        }
    }

    public void updatePolicyToLeague(League league, Season season , LeagueCalcolator leaguePolicy) {
        //TODO:
        // 1.add to U.C 9.5 paramater to function : Season season , LeagueCalculator leaguePolicy
        try {
            league.updatePolicyToLeague(league, season,leaguePolicy);
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
    public boolean addPlayer(IFA ifa, String pName, Date birthDay,String password, String userName) throws Exception{
        if(ifa!=null && pName!=null && birthDay!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                ifa.addPlayer(pName, birthDay,password, userName);
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
    public boolean addCoach(IFA ifa, String cName,String password, String userName) throws Exception{
        if(ifa!=null && cName!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                ifa.addCoach(cName,password, userName);
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
    public boolean addRefree(IFA ifa, String rName,String password, String userName, String type) throws Exception{
        if(ifa!=null && rName!=null  && password!=null && userName!=null && type!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                ifa.addRefree(rName,password, userName, type);
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
    public boolean addIFA(IFA ifaManager, String ifaName, String password, String userName) throws Exception{
        if(ifaManager!=null && ifaName!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                ifaManager.addNewIFA( ifaName, password, userName);
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
    public boolean addOwner(IFA ifaManager, String OwnerName, String password, String userName) throws Exception{
        if(ifaManager!=null && OwnerName!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                ifaManager.addOwner( OwnerName, password, userName);
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
    public boolean addTeam(IFA ifaManager, String teamName, Owner owner) throws Exception{
        if(ifaManager!=null && teamName!=null  && owner!=null){
            ifaManager.addTeam( owner, teamName);
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
