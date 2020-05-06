package service;
import DB.AcceptanceTests;
import DB.RegressionTests;
import domain.manageUsers.Account;
import domain.manageLeagues.IFA;
import domain.Asset.Owner;
import DB.System;
import domain.manageLeagues.League;
import domain.manageLeagues.Season;
import domain.manageTeams.Team;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.*;

import static org.junit.Assert.*;

public class IFAControllerTest {

    System system =System.getInstance();

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void newLeague1acceptance() {
        int legaueLevel=2;
        String leagueName="Ligan ha aal";
        League leagueTest=system.addLeague(leagueName,legaueLevel);
        assertTrue(system.getLeagues().contains(leagueTest));
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void newLeague2acceptance() { //wrong input level test
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
    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void newLeagueTest3acceptance() { //existing league test
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

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void newSeasonTest4acceptance() { //create season succsesfully
        int year=2022;
        boolean start=false;
        Season season=system.addSeason(year);
        assertTrue(system.getSeasons().contains(season));
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void newSeasonTest5acceptance() { //failure with wrong year
        int year=-15;
        boolean start=false;
        String message="";
        try {
            Season season = system.addSeason(year);
        }
        catch (InputMismatchException e)
        {
            message=e.getMessage();
        }
        assertEquals("Wrong input",message);
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void addSeasonToLeagueTest6acceptance() { //find successfuly the season of league
        system.addSeason(2019);
        system.addLeague("Ligat ha al",1);
        League league1=system.getLeagues().get(0);
        Season season1=system.getSeasons().get(0);
        String message="";
        try {
          league1.addSeasonToLeague(season1);
        }
        catch (InputMismatchException e)
        {
            message=e.getMessage();
        }
        assertTrue(system.getLeagues().get(0).getSeasonInfos().containsKey(season1));
    }

  ///////--------------------------------------------------------------------------------------------lior part

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void addPlayer7acceptance() throws Exception{
        Account lAcoount = new Account("Gal", "Abc12345");
        IFA ifa = new IFA("Gal" ,lAcoount);  //String name, Account account

        Date date = new Date();
        String pname = "gal";
        String password ="Gal330973845";
        String userName ="galTheKing";
        List<String> positions=new LinkedList<>();
        positions.add("GK");
        ifa.addPlayer(pname, date,password, userName,positions);
        assertEquals("gal",system.getPlayer("gal").getName());
        assertEquals("galTheKing",system.getPlayer("gal").getAccount().getUserName());
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void addCoach8acceptance() throws Exception{
        // IFA ifa, String cName,String password, String userName
        Account lAcoount = new Account("matan22", "Abc12345");// userName, String password
        IFA ifa = new IFA("matan" ,lAcoount);  //String name, Account account
        int rand_int1 = new Random().nextInt(1000);
        String cname = "Nir";
        String password ="Nir333845";
        String userName ="NirLevi"+rand_int1;
        ifa.addCoach(cname,password, userName,"training");
        String userName2 = system.getCoach("Nir").getAccount().getUserName();
        assertEquals("NirLevi"+rand_int1,userName2);
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void addReferee9acceptance() throws Exception{
        Account lAcoount = new Account("liorb", "33097377");// userName, String password
        IFA ifa = new IFA("lior" ,lAcoount);  //String name, Account account
        String rName = "ori";
        String password ="Ori330973845";
        String userName ="OriLonsteine";
        String type = "Main";
        ifa.addReferee(rName, password, userName, type,"training");
        assertEquals("ori",system.getRefree(rName).getName());
        assertEquals("OriLonsteine",system.getAccountManager().getAccount(userName).getUserName());
    }


    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void addIFA10acceptance() throws Exception{
        // addIFA(IFA ifaManager, String ifaName, String password, String userName
        IFA ifaManager=system.createNewIFAUser("liorgg","330973Gb","liorbjhh");
        String ifaName = "ori";
        String password ="Ori330973845";
        String userName ="OriLonsteinr";
        ifaManager.addNewIFA(ifaName, password, userName);
        assertEquals("ori",system.getIFA(ifaName).getName());
        assertEquals("OriLonsteinr",system.getAccountManager().getAccount(userName).getUserName());
    }


    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void addOwner11acceptance() throws Exception{
        // addIFA(IFA ifaManager, String ifaName, String password, String userName
        Account Acoount1 = new Account("liorb", "33097377");// userName, String password
        IFA ifaManager = new IFA("lior" ,Acoount1);  //String name, Account account

        String OwnerName = "ori";
        String password ="Ori330973845";
        String userName ="OriLonsteinrr";
        ifaManager.addOwner(OwnerName, password, userName);
        assertEquals("ori",system.getOwner(userName).getName());
        assertEquals("OriLonsteinrr",system.getAccountManager().getAccount(userName).getUserName());
    }

    //createTeam(IFA ifaManager, String teamName, Owner owner
    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void addTeam12acceptance() throws Exception{

        IFA ifaManager = system.createNewIFAUser("lior","330973gB","liorbj");
        Owner owner =system.createNewOwnerUser("ori","Ori330973845","OriLonstein");
        String teamName = "hapoel";
        ifaManager.addTeam(owner.getAccount().getUserName(), teamName);
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