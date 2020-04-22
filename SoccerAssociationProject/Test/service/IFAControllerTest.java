package service;
import domain.*;
import domain.System;

import javax.rmi.CORBA.Stub;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

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

  ///////--------------------------------------------------------------------------------------------lior part

    @org.junit.Test
    public void addPlayer() throws Exception{
        Account lAcoount = new Account("liorb", "33097377");
        IFA ifa = new IFA("lior" ,lAcoount);  //String name, Account account

        Date date = new Date();
        String pname = "ori";
        String password ="Ori330973845";
        String userName ="OriLonstein";
        ifa.addPlayer(pname, date,password, userName);
        assertEquals("ori",system.getPlayer("ori").getName());
        assertEquals("OriLonstein",system.getPlayer("ori").getAccount().getUserName());
    }

    @org.junit.Test
    public void addCoach() throws Exception{
        // IFA ifa, String cName,String password, String userName
        Account lAcoount = new Account("liorb", "33097377");// userName, String password
        IFA ifa = new IFA("lior" ,lAcoount);  //String name, Account account

        String cname = "ori";
        String password ="Ori330973845";
        String userName ="OriLonstein";
        ifa.addCoach(cname,password, userName);
        assertEquals("ori",system.getCoach("ori").getName());
        String userName2 = system.getCoach("ori").getAccount().getUserName();
        assertEquals("OriLonstein",userName2);
    }

    @org.junit.Test
    public void addReferee() throws Exception{

        Account lAcoount = new Account("liorb", "33097377");// userName, String password
        IFA ifa = new IFA("lior" ,lAcoount);  //String name, Account account

        String rName = "ori";
        String password ="Ori330973845";
        String userName ="OriLonstein";
        String type = "Main";
        ifa.addReferee(rName, password, userName, type);
        assertEquals("ori",system.getRefree(rName).getName());
        assertEquals("OriLonstein",system.getRefreeAccount(userName).getUserName());
    }


    @org.junit.Test
    public void addIFA() throws Exception{
        // addIFA(IFA ifaManager, String ifaName, String password, String userName
        Account Acoount1 = new Account("liorb", "33097377");// userName, String password
        IFA ifaManager = new IFA("lior" ,Acoount1);  //String name, Account account

        String ifaName = "ori";
        String password ="Ori330973845";
        String userName ="OriLonstein";
        ifaManager.addNewIFA(ifaName, password, userName);
        assertEquals("ori",system.getIFA(ifaName).getName());
        assertEquals("OriLonstein",system.getIFAAccount(userName).getUserName());
    }


    @org.junit.Test
    public void addOwner() throws Exception{
        // addIFA(IFA ifaManager, String ifaName, String password, String userName
        Account Acoount1 = new Account("liorb", "33097377");// userName, String password
        IFA ifaManager = new IFA("lior" ,Acoount1);  //String name, Account account

        String OwnerName = "ori";
        String password ="Ori330973845";
        String userName ="OriLonstein";
        ifaManager.addOwner(OwnerName, password, userName);
        assertEquals("ori",system.getOwner(OwnerName).getName());
        assertEquals("OriLonstein",system.getOwnerAccount(userName).getUserName());
    }

    //addTeam(IFA ifaManager, String teamName, Owner owner
    @org.junit.Test
    public void addTeam() throws Exception{
        Account Acoount1 = new Account("liorb", "33097377");// userName, String password
        IFA ifaManager = new IFA("lior" ,Acoount1);  //String name, Account account

        Account Acoountowner = new Account("OriLonstein", "Ori330973845");// userName, String password
        Owner owner = new Owner(Acoountowner, "ori");
        String teamName = "hapoel";
        ifaManager.addTeam(owner, teamName);

        assertEquals("hapoel",system.getTeam(teamName).getName());

        Team team = system.getTeam(teamName);
        List<Owner> ownerList = team.getOwners();
        String userNameOwner ="";
        for (Owner owners:ownerList)
        {
            if(owners.getAccount().getUserName() == owner.getAccount().getUserName())
            {
                userNameOwner = owners.getAccount().getUserName();
                break;
            }
        }
        assertEquals(owner.getAccount().getUserName(),userNameOwner);
    }


}//class