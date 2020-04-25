package service;

import DB.AcceptanceTests;
import DB.RegressionTests;
import domain.Asset.*;
import domain.manageUsers.Account;
import domain.manageEvents.Notification;
import domain.manageTeams.FinancialAction;
import domain.manageTeams.Team;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardManagerControllerTest {
    Owner owner;
    Team team;
    Coach coach;
    Fan fan;
    @org.junit.Before
    public void setUp() throws Exception
    {
        List<Owner> ownerList=new ArrayList<>();
        owner=new Owner(new Account("bob1","bob12345"),"bob",null,null,null);
        ownerList.add(owner);
        team=new Team(ownerList,"M.C");
        Team team=new Team(ownerList,"Hapoal Tel-aviv");
        owner.setTeam(team);
        coach = new Coach(new Account("galcoach","cDasj3454"),"coach");
        fan = new Fan("fan",new Account("gggggg","gfgdagad3"));
    }

    /**
     * @author matan
     *  acceptance test UC 6.1a
     */
    @org.junit.Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void addAssets1acceptance() {
        Field filed=new Field("Tady");
        BoardManagerController bmc = new BoardManagerController();
        bmc.addAssets(filed,owner);
        assertTrue(owner.getTeam().getAssetsOfTeam().contains(filed));
    }

    /**
     * @author matan
     * acceptance test UC 6.1b
     */

    @Test(expected = ArithmeticException.class)
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void removeAssets2acceptance() {
        Field trainField=new Field("buyit vagan");
        BoardManagerController bmc = new BoardManagerController();
        bmc.removeAssets(trainField,owner);
    }
    /**
     * @author matan
     * acceptance test UC 6.2
     */
    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void appointmentNewOwner3acceptance() {
        Owner ownerNew=new Owner( new Account("Matan","12341234"),"matan gadasi",null,null,null);
        BoardManagerController bmc = new BoardManagerController();
        bmc.appointmentNewOwner(owner,ownerNew);
        assertTrue(owner.getTeam().getOwners().contains(ownerNew));
    }
    /**
     * @author matan
     * acceptance test UC 6.3
     */

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void removeOwnerAppointment4acceptance() {
        Owner ownerRemove=new Owner( new Account("Matan","12341234"),"matan gadasi",null,null,null);
        BoardManagerController bmc = new BoardManagerController();
        bmc.appointmentNewOwner(owner,ownerRemove);
        bmc.removeOwnerAppointment(owner,ownerRemove);
        assertFalse(owner.getTeam().getOwners().contains(ownerRemove));
    }
    /**
     * @author matan
     * acceptance test UC 6.4
     */

    @org.junit.Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void testAppointTeamManger5acceptance() {
        Account account=new Account("guyLevi","12341234");
        TeamManager teamManager=new TeamManager(account,"guy levi",null,null,2000,null);
        BoardManagerController bmc = new BoardManagerController();
        List<String> permissionList=new ArrayList<>();
        permissionList.add("addPlayer");
        permissionList.add("removePlayer");
        bmc.appointTeamManger(owner,teamManager,permissionList,2000);
        assertTrue(teamManager.getTeam()==owner.getTeam());
    }

    /**
     * @author matan
     * acceptance test UC 6.5a
     */
    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void testRemoveAppointTeamMangerA6acceptance() {
        Account account=new Account("guyLevi","12341234");
        TeamManager teamManager=new TeamManager(account,"guy levi",null,null,2000,null);
        BoardManagerController bmc = new BoardManagerController();
        List<String> permissionList=new ArrayList<>();
        permissionList.add("addPlayer");
        permissionList.add("removePlayer");
        bmc.appointTeamManger(owner,teamManager,permissionList,2000);
        assertEquals(owner.getTeam(),teamManager.getTeam());
    }
    /**
     * @author matan
     * acceptance test UC 6.5b
     */
    @Test(expected = ArithmeticException.class)
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void testRemoveAppointTeamMangerB7acceptance() {
        Account account=new Account("guyLevi","12341234");
        TeamManager teamManager=new TeamManager(account,"guy levi",null,null,2000,null);
        BoardManagerController bmc = new BoardManagerController();
        List<String> permissionList=new ArrayList<>();
        permissionList.add("addPlayer");
        permissionList.add("removePlayer");
        bmc.removeTeamManger(owner,teamManager);
    }
    /**
     * @author matan
     * acceptance test UC 6.6.1.a
     */
    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void openTeam1a8acceptance() throws Exception {
        BoardManagerController bmc = new BoardManagerController();
        owner.getTeam().setStatus(false);
        bmc.openTeam(owner);
        assertTrue(owner.getTeam().isStatus());
    }
    /**
     * @author matan
     * acceptance test UC 6.6.1.b
     */
    @Test(expected = Exception.class)
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void openTeam1b9acceptance()throws Exception{
        BoardManagerController bmc = new BoardManagerController();
        bmc.openTeam(owner);
    }
    /**
     * @author matan
     * acceptance test UC 6.6.2.a
     */
    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void closeTeam2a10acceptance() throws Exception {
        BoardManagerController bmc = new BoardManagerController();
        bmc.closeTeam(owner);
        assertFalse(owner.getTeam().isStatus());
    }
    /**
     * @author matan
     * acceptance test UC 6.6.2.b
     */
    @Test(expected = Exception.class)
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void closeTeam2b11acceptance()throws Exception{
        BoardManagerController bmc = new BoardManagerController();
        owner.getTeam().setStatus(false);
        bmc.closeTeam(owner);
    }
    /**
     * @author matan
     * acceptance test UC 6.7a
     */
    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void reportIncomeOrOutcome12acceptance() {
        FinancialAction financialAction=new FinancialAction("Buy new player",-50000,owner);
        BoardManagerController bmc = new BoardManagerController();
        bmc.reportIncomeOrOutcome(owner,financialAction);
        assertTrue(owner.getTeam().getFinancialActions().contains(financialAction));

    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void uploadDataToPage13acceptance() throws Exception {
        fan.followPage(0);
        coach.uploadDataToPage("checkData");
        for (Notification notification:fan.readNotification())
        {
            assertEquals(notification.getDetails(),"checkData");
        }
        fan.unfollowPage(0);
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void uploadEmptyDataToPage14acceptance() throws Exception
    {
        fan.followPage(0);
        coach.uploadDataToPage("");
        assertTrue(fan.readNotification().isEmpty());
        fan.unfollowPage(0);
    }
}