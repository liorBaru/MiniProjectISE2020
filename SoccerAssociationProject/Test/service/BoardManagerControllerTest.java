package service;

import domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardManagerControllerTest {
    Owner owner;
    @org.junit.Before
    public void setUp(){
        List<Owner> ownerList=new ArrayList<>();
        owner=new Owner("bob1","bob12345","bob","Owner",null,null,null);
        ownerList.add(owner);
        Team team=new Team(ownerList,"Hapoal Tel-aviv");
        owner.setTeam(team);
    }

    /**
     * @author matan
     *  acceptance test UC 6.1a
     */
    @org.junit.Test
   public void addAssets() {
        Field filed=new Field("Tady");
        BoardManagerController bmc = new BoardManagerController();
        bmc.addAssets(filed,owner);
        assertTrue(owner.getTeam().getAssetsOfTeam().contains(filed));
    }

    /**
     * @author matan
     * acceptance test UC 6.1b
     */

    @org.junit.Test(expected = ArithmeticException.class)
    public void removeAssets() {
        TrainField trainField=new TrainField("buyit vagan");
        BoardManagerController bmc = new BoardManagerController();
        bmc.removeAssets(trainField,owner);
    }
    /**
     * @author matan
     * acceptance test UC 6.2
     */
    @Test
    public void appointmentNewOwner() {
        Owner ownerNew=new Owner("Matan","12341234","matan gadasi","another owner",null,null,null);
        BoardManagerController bmc = new BoardManagerController();
        bmc.appointmentNewOwner(owner,ownerNew);
        assertTrue(owner.getTeam().getOwners().contains(ownerNew));
    }
    /**
     * @author matan
     * acceptance test UC 6.3
     */

    @Test
    public void removeOwnerAppointment() {
        Owner ownerRemove=new Owner("Matan","12341234","matan gadasi","another owner",null,null,null);
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
    public void testAppointTeamManger() {
        Account account=new Account("guyLevi","12341234");
        TeamManager teamManager=new TeamManager(account,"guy levi",null,"manger",null,2000,null);
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
    @org.junit.Test
    public void testRemoveAppointTeamMangerA() {
        Account account=new Account("guyLevi","12341234");
        TeamManager teamManager=new TeamManager(account,"guy levi",null,"manger",null,2000,null);
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
    @org.junit.Test(expected = ArithmeticException.class)
    public void testRemoveAppointTeamMangerB() {
        Account account=new Account("guyLevi","12341234");
        TeamManager teamManager=new TeamManager(account,"guy levi",null,"manger",null,2000,null);
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
    public void openTeam1a() throws Exception {
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
    public void openTeam1b()throws Exception{
        BoardManagerController bmc = new BoardManagerController();
        bmc.openTeam(owner);
    }
    /**
     * @author matan
     * acceptance test UC 6.6.2.a
     */
    @Test
    public void closeTeam2a() throws Exception {
        BoardManagerController bmc = new BoardManagerController();
        bmc.closeTeam(owner);
        assertFalse(owner.getTeam().isStatus());
    }
    /**
     * @author matan
     * acceptance test UC 6.6.2.b
     */
    @Test(expected = Exception.class)
    public void closeTeam2b()throws Exception{
        BoardManagerController bmc = new BoardManagerController();
        owner.getTeam().setStatus(false);
        bmc.closeTeam(owner);
    }
    /**
     * @author matan
     * acceptance test UC 6.7a
     */
    @Test
    public void reportIncomeOrOutcome() {
        FinancialAction financialAction=new FinancialAction("Buy new player",-50000,owner);
        BoardManagerController bmc = new BoardManagerController();
        bmc.reportIncomeOrOutcome(owner,financialAction);
        assertTrue(owner.getTeam().getFinancialActions().contains(financialAction));

    }
}