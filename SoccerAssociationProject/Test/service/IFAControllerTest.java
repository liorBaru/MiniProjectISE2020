package service;
import domain.*;
import domain.System;

import javax.rmi.CORBA.Stub;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class IFAControllerTest {
    System system =System.getInstance();
    @org.junit.Test
    public void newLeagueTest1() {
        int legaueLevel=2;
        String leagueName="Ligan ha aal";
        League leagueTest=system.addLeague(leagueName,legaueLevel);
        assertTrue(system.getLeagues().contains(leagueTest));
    }

    @org.junit.Test
    public void newLeagueTest2() { //wrong input level test
        //TODO:
        // 1.change mivhanei kabal - test 9.1.b change the input of league to leaguelevel
        int legaueLevel=-5;
        String message="";
        String leagueName="Premier League";
        try {
            League leagueTest = system.addLeague(leagueName, legaueLevel);
        }
        catch (InputMismatchException e)
        {
            message=e.getMessage();
        }
        assertEquals("Wrong input",message);
    }
    @org.junit.Test
    public void newLeagueTest3() { //existing league test
        int legaueLevel=1;
        String message="";
        String leagueName="Chanpionship League";
        try {
            League leagueTest = system.addLeague(leagueName, legaueLevel);
        }
        catch (InputMismatchException e)
        {
            message=e.getMessage();
        }
        assertEquals("Wrong input",message);
    }
    @org.junit.Test
    public void newSeasonTest1() { //create season succsesfully
        int year=2022;
        boolean start=false;
        Season season=system.addSeason(year,start);
        assertTrue(system.getSeasons().contains(season));
    }
    @org.junit.Test
    public void newSeasonTest2() { //failure with wrong year
        int year=-15;
        boolean start=false;
        String message="";
        try {
            Season season = system.addSeason(year, start);
        }
        catch (InputMismatchException e)
        {
            message=e.getMessage();
        }
        assertEquals("Wrong input",message);
    }

    @org.junit.Test
    public void addSeasonToLeagueTest1() { //find successfuly the season of league
        system.addSeason(2019,false);
        system.addLeague("Ligat ha al",1);
        League league1=system.getLeagues().get(0);
        Season season1=system.getSeasons().get(0);
        String message="";
        try {
          league1.addSeasonToLeague(league1,season1);
        }
        catch (InputMismatchException e)
        {
            message=e.getMessage();
        }
        assertTrue(system.getLeagues().get(0).getSeasonInfos().containsKey(season1));
    }

    /*
    @org.junit.Test
    public void () { //create season succsesfully
        int year=2022;
        boolean start=false;
        Season season=system.addSeason(year,start);
        assertTrue(system.getSeasons().contains(season));
    }

     */
}