package service;
import domain.*;
import domain.System;

import java.util.InputMismatchException;


import domain.*;
import domain.System;
import java.util.Date;

/**
 * lior class
 */
public class IFAController {

    System system;


    /**
     * chech if the user nane and password is legal
     * @param password
     * @param userName
     * @return
     */
    public boolean isPassAndUserNIsLegal(String password, String userName){
        if(password!=null && userName!=null){
            if(userName.length()>=6 && password.length()>=8 ){ //add pass contain capital small and number ??
                return true;
            }
        }
        return false;
    }



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
            league.addSeasonToLeague(league, season);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input");
        }
    }
}
