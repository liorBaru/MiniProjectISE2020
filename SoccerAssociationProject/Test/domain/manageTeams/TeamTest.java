package domain.manageTeams;

import DB.IntegrationTests;
import DB.RegressionTests;
import DB.UnitTests;
import domain.Asset.*;
import domain.manageEvents.Notification;
import domain.manageUsers.Account;
import domain.manageUsers.AccountStub;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {

    private Team team;
    private  Owner Owner;
    private StaffMember staffMember;

    public void setUpUnit(){

        OwnerStub Owner1= new OwnerStub();
        List<Owner> owners= new ArrayList<>();
        owners.add(Owner1);
        team= new Team(owners,"team");

    }

    public void setUpIntegration(){
        Owner= new Owner(new Account("owner1","passOwner1"),"owner");
        List<Owner> owners= new ArrayList<>();
        owners.add(Owner);
        staffMember= new Coach(new Account("Coach", "passCoach123"),"Coach");
        team= new Team(owners,"team");
        team.addStaffMember(staffMember);
    }

    @Test
    @Category({UnitTests.class})
    public void setStatusSuccess1Unit() throws Exception {
        setUpUnit();
        assertTrue(team.isStatus());
        team.setStatus(false);
        assertFalse(team.isStatus());
    }

    @Test (expected = Exception.class)
    @Category({UnitTests.class})
    public void setStatusFailed2Unit() throws Exception {
        setUpUnit();
        assertTrue(team.isStatus());
        team.setStatus(true);
    }

    @Test (expected = Exception.class)
    @Category({UnitTests.class})
    public void setStatusFailed3Unit() throws Exception {
        setUpUnit();
        assertTrue(team.isStatus());
        team.setStatus(false);
        assertFalse(team.isStatus());
        team.setStatus(false);
    }

    @Test
    @Category({UnitTests.class})
    public void addFinancialActionSuccess4Unit()  {
        setUpUnit();
        BoardMember boardMember= new OwnerStub();
        FinancialActionStub financialActionStub= new FinancialActionStub(boardMember);
        team.addFinancialAction(financialActionStub);
        assertTrue(team.getFinancialActions().contains(financialActionStub));
    }

    @Test
    @Category({UnitTests.class})
    public void addFinancialActionFailed5Unit()  {
        setUpUnit();
        assertFalse(team.addFinancialAction(null));
    }

    @Test
    @Category({UnitTests.class})
    public void addAssetSuccess6Unit()  {
        setUpUnit();
        Asset assetStub= new OwnerStub();
        team.addAsset(assetStub);
        assertTrue(team.getAssetsOfTeam().contains(assetStub));
    }

    @Test (expected = Exception.class)
    @Category({UnitTests.class})
    public void addAssetFailed7Unit()  {
        setUpUnit();
        team.addAsset(null);
    }

    @Test (expected = Exception.class)
    @Category({UnitTests.class})
    public void addAssetFailed8Unit() throws Exception {
        setUpUnit();
        Asset assetStub= new OwnerStub();
        team.setStatus(false);
        team.addAsset(assetStub);
    }

    @Test
    @Category({UnitTests.class})
    public void removeAssetSuccess9Unit() {
        setUpUnit();
        Asset asset= new OwnerStub();
        team.addAsset(asset);
        team.removeAsset(asset);
        assertFalse(team.getAssetsOfTeam().contains(asset));
    }

    @Test
    @Category({IntegrationTests.class})
    public void setStatusSuccess1Integration() throws Exception {
        setUpIntegration();
        assertTrue(team.isStatus());
        team.setStatus(false);
        assertFalse(team.isStatus());
        String lastNotificationToOwner= this.Owner.getAccount().getUser().readNotification().peek().getDetails();
        assertTrue(lastNotificationToOwner.contains("The Team: " +team.getName() + " is inactive"));
        String lastNotificationToStaff= this.staffMember.getAccount().getUser().readNotification().peek().getDetails();
        assertTrue(lastNotificationToStaff.contains("The Team: " +team.getName() + " is inactive"));
    }

    @Test (expected = Exception.class)
    @Category({IntegrationTests.class})
    public void setStatusFailed2Integration() throws Exception {
        setUpIntegration();
        assertTrue(team.isStatus());
        team.setStatus(true);
    }

    @Test (expected = Exception.class)
    @Category({IntegrationTests.class})
    public void setStatusFailed3Integration() throws Exception {
        setUpIntegration();
        assertTrue(team.isStatus());
        team.setStatus(false);
        assertFalse(team.isStatus());
        team.setStatus(false);
    }

    @Test
    @Category({IntegrationTests.class, RegressionTests.class})
    public void addFinancialActionSuccess4Integration()  {
        setUpIntegration();
        FinancialAction financialAction= new FinancialAction("Game revenue",5000,this.Owner);
        team.addFinancialAction(financialAction);
        assertTrue(team.getFinancialActions().contains(financialAction));
    }


    @Test
    @Category({IntegrationTests.class})
    public void addFinancialActionFailed5Integration()  {
        setUpIntegration();
        assertFalse(team.addFinancialAction(null));
    }


    @Test
    @Category({IntegrationTests.class, RegressionTests.class})
    public void addAssetSuccess6Integration()  {
        setUpIntegration();
        Asset asset= this.Owner;
        team.addAsset(asset);
        assertTrue(team.getAssetsOfTeam().contains(asset));
    }

    @Test (expected = Exception.class)
    @Category({IntegrationTests.class})
    public void addAssetFailed7Integration()  {
        setUpIntegration();
        team.addAsset(null);
    }

    @Test (expected = Exception.class)
    @Category({IntegrationTests.class})
    public void addAssetFailed8Integration() throws Exception {
        setUpIntegration();
        Asset asset= this.Owner;
        team.setStatus(false);
        team.addAsset(asset);
    }

    @Test
    @Category({IntegrationTests.class})
    public void addStaffMemberSuccess9Integration() throws Exception {
        setUpIntegration();
        team.addStaffMember(this.staffMember);
        team.getStaffMembers().contains(this.staffMember);
    }


    @Test
    @Category({IntegrationTests.class})
    public void setCloseSuccess10Integration()  {
        setUpIntegration();
        Date date= new Date();
        Notification notification= new Notification(" is inactive",date);
        team.setClose(notification);
        for (StaffMember staffMem : team.getStaffMembers()) {
            Notification staffMemNotification= staffMem.readNotification().peek();
             assertTrue(notification.equals(staffMemNotification));
        }
    }

    @Test
    @Category({IntegrationTests.class})
    public void removeAssetSuccess11Integration() {
        setUpIntegration();
        Asset asset= this.Owner;
        team.addAsset(asset);
        team.removeAsset(asset);
        assertFalse(team.getAssetsOfTeam().contains(asset));

    }

    @Test (expected = Exception.class)
    @Category({IntegrationTests.class})
    public void removeAssetFailed12Integration() {
        setUpIntegration();
        Asset asset= this.Owner;
        team.removeAsset(asset);
        team.getAssetsOfTeam().contains(asset);

    }
    @Test (expected = Exception.class)
    @Category({IntegrationTests.class})
    public void removeAssetFailed13Integration() {
        setUpIntegration();
        team.removeAsset(null);
    }

    @Test
    @Category({IntegrationTests.class})
    public void removeTeamMangerFailed14Integration() {
        setUpIntegration();
        BoardMember bm= this.Owner;
        List<String> permissions= new ArrayList<>();
        permissions.add("removeTeamManger");
        TeamManager tm= new TeamManager(new AccountStub(),"TM",this.team,bm,500,permissions);
        team.addStaffMember(tm);
        team.removeTeamManger(tm);
    }


    @Test (expected = Exception.class)
    @Category({IntegrationTests.class})
    public void removeTeamMangerFailed15Integration() {
        team.removeTeamManger(null);
    }

    @Test (expected = Exception.class)
    @Category({IntegrationTests.class})
    public void removeTeamMangerFailed16Integration() {
        setUpIntegration();
        BoardMember bm= this.Owner;
        List<String> permissions= new ArrayList<>();
        permissions.add("removeTeamManger");
        TeamManager tm= new TeamManager(new AccountStub(),"TM",this.team,bm,500,permissions);
        team.removeTeamManger(tm);
    }
}