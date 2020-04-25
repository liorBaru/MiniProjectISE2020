package domain.Asset;


import DB.RegressionTests;
import domain.manageTeams.Team;
import domain.manageUsers.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BoardMemberTest {
    BoardMember boardMember;
    Owner owner;
    Team team;
    Player player;
    Coach coach;


    @Before
    public void setUp() throws Exception {
    owner=new Owner(new Account("matanG","G123456"),"matan");
    List<Owner> ownerList=new ArrayList<>();
    ownerList.add(owner);
    List<String> list=new ArrayList<>();
    boardMember=new TeamManager(new Account("namename","Name12345"),"name",null,null,20000,list);
    team =new Team( ownerList,"MC");
    boardMember.setTeam(team);
    player=new Player(new Account("Banhaim","Bb123456"),"banHamin", new Date(1991,5,14));
    team.addAsset(player);
    team.addAsset(boardMember);
    coach=new Coach(new Account("TalTal","Tal123456"),"Tal Haim");
    team.addAsset(coach);

    }
    @Category(RegressionTests.class)
    @Test
    public void removePlayerFalse1Integration() {
        boardMember.permissions.put(permission.removePlayer,false);
        assertFalse(boardMember.removePlayer(player));
    }

    @Test
    public void removePlayerTrue2Integration() {
        boardMember.permissions.put(permission.removePlayer,true);
        assertTrue(boardMember.removePlayer(player));
    }

    @Test
    public void addCouchFalse() {
        boardMember.permissions.put(permission.addCoach,false);
        assertFalse(boardMember.addCouch(coach));
    }
    @Test
    public void addCouchTrue() {
        boardMember.permissions.put(permission.addCoach,true);
        assertTrue(boardMember.addCouch(coach));
    }

    @Test
    public void removeCoachFalse() {
        boardMember.permissions.put(permission.removeCoach,false);
        assertFalse(boardMember.removeCoach(coach));
    }
    @Test
    public void removeCoachTrue() {
        boardMember.permissions.put(permission.removeCoach,true);
        assertFalse(boardMember.removeCoach(coach));
    }

    @Test
    public void addFinancialActionFalse() {
        boardMember.permissions.put(permission.addFinancial,false);
        assertFalse(boardMember.addFinancialAction("buyPlayer",-2000));
    }
    @Test
    public void addFinancialActionTrue() {
        boardMember.permissions.put(permission.addFinancial,true);
        assertTrue(boardMember.addFinancialAction("buyPlayer",-2000));
    }

    @Test
    public void addAssetsFalse() {
        boardMember.permissions.put(permission.addAsset,false);
        assertFalse(boardMember.addAssets("filed"));
    }
    @Test
    public void addAssetsTrue() {
        boardMember.permissions.put(permission.addAsset,true);
        assertTrue(boardMember.addAssets("filed"));
    }

    @Test
    public void updateTeamPageTrue() {
        boardMember.permissions.put(permission.updateTeamPage,true);
        assertTrue(boardMember.updateTeamPage("New Player is on the Way"));
    }
    @Test
    public void updateTeamPageFalse() {
        boardMember.permissions.put(permission.updateTeamPage,false);
        assertFalse(boardMember.updateTeamPage("New Player is on the Way"));
    }

    @Test
    public void removeTeam() throws Exception {
        boardMember.removeTeam(team);
        assertNull(boardMember.team);
    }

}