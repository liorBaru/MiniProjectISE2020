package domain.manageTeams;

import domain.Asset.*;
import domain.manageUsers.Account;
import org.junit.Test;

import java.util.ArrayList;
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
    public void setStatusSuccess1Unit() throws Exception {
        setUpUnit();
        assertTrue(team.isStatus());
        team.setStatus(false);
        assertFalse(team.isStatus());
    }

    @Test (expected = Exception.class)
    public void setStatusFailed2Unit() throws Exception {
        setUpUnit();
        assertTrue(team.isStatus());
        team.setStatus(true);
    }

    @Test (expected = Exception.class)
    public void setStatusFailed3Unit() throws Exception {
        setUpUnit();
        assertTrue(team.isStatus());
        team.setStatus(false);
        assertFalse(team.isStatus());
        team.setStatus(false);
    }

    @Test
    public void addFinancialActionSuccess4Unit()  {
        setUpUnit();
        BoardMember boardMember= new OwnerStub();
        FinancialActionStub financialActionStub= new FinancialActionStub(boardMember);
        team.addFinancialAction(financialActionStub);
        assertTrue(team.getFinancialActions().contains(financialActionStub));
    }

    @Test
    public void addFinancialActionFailed5Unit()  {
        setUpUnit();
        assertFalse(team.addFinancialAction(null));
    }

    @Test
    public void addAssetSuccess6Unit()  {
        setUpUnit();
        Asset assetStub= new OwnerStub();
        team.addAsset(assetStub);
        assertTrue(team.getAssetsOfTeam().contains(assetStub));
    }

    @Test (expected = Exception.class)
    public void addAssetFailed7Unit()  {
        setUpUnit();
        team.addAsset(null);
    }

    @Test (expected = Exception.class)
    public void addAssetFailed8Unit() throws Exception {
        setUpUnit();
        Asset assetStub= new OwnerStub();
        team.setStatus(false);
        team.addAsset(assetStub);
    }




    @Test
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
    public void setStatusFailed2Integration() throws Exception {
        setUpIntegration();
        assertTrue(team.isStatus());
        team.setStatus(true);
    }

    @Test (expected = Exception.class)
    public void setStatusFailed3Integration() throws Exception {
        setUpIntegration();
        assertTrue(team.isStatus());
        team.setStatus(false);
        assertFalse(team.isStatus());
        team.setStatus(false);
    }

    @Test
    public void addFinancialActionSuccess4Integration()  {
        setUpIntegration();
        FinancialAction financialAction= new FinancialAction("Game revenue",5000,this.Owner);
        team.addFinancialAction(financialAction);
        assertTrue(team.getFinancialActions().contains(financialAction));
    }

    @Test
    public void addFinancialActionFailed5Integration()  {
        setUpIntegration();
        assertFalse(team.addFinancialAction(null));
    }


    @Test
    public void addAssetSuccess6Integration()  {
        setUpIntegration();
        Asset asset= this.Owner;
        team.addAsset(asset);
        assertTrue(team.getAssetsOfTeam().contains(asset));
    }

    @Test (expected = Exception.class)
    public void addAssetFailed7Integration()  {
        setUpIntegration();
        team.addAsset(null);
    }

    @Test (expected = Exception.class)
    public void addAssetFailed8Integration() throws Exception {
        setUpIntegration();
        Asset asset= this.Owner;
        team.setStatus(false);
        team.addAsset(asset);
    }

    @Test
    public void addStaffMemberSuccess9Integration() throws Exception {
        setUpIntegration();
        team.addStaffMember(this.staffMember);
        team.getStaffMembers().contains(this.staffMember);
    }
}