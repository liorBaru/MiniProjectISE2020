package domain.Asset;



import main.DB.IntegrationTests;
import main.DB.UnitTests;
import main.domain.Asset.*;
import main.domain.manageTeams.Team;
import main.domain.manageUsers.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
    List<String> positions = new LinkedList<>();
    positions.add("GK");
    player=new Player(new Account("Banhaim","Bb123456"),"banHamin", new Date(1991,5,14),positions);
    team.addAsset(player);
    team.addAsset(boardMember);
    coach=new Coach(new Account("TalTal","Tal123456"),"Tal Haim","training");
    team.addAsset(coach);

    }
    @Test
    @Category({ IntegrationTests.class})
    public void removePlayerFalse1Integration() {
        boardMember.permissions.put(permission.removePlayer,false);
        assertFalse(boardMember.removePlayer(player));
    }

    @Test
    @Category({ IntegrationTests.class})
    public void removePlayerTrue2Integration() {
        boardMember.permissions.put(permission.removePlayer,true);
        assertTrue(boardMember.removePlayer(player));
    }

    @Test
    @Category({ UnitTests.class})
    public void addCouchFalse1Unit() {
        boardMember.permissions.put(permission.addCoach,false);
        assertFalse(boardMember.addCouch(coach));
    }

    @Test
    @Category({UnitTests.class})
    public void addCouchTrue2Unit() {
        boardMember.permissions.put(permission.addCoach,true);
        assertTrue(boardMember.addCouch(coach));
    }

    @Test
    @Category({UnitTests.class})
    public void removeCoachFalse3Unit() {
        boardMember.permissions.put(permission.removeCoach,false);
        assertFalse(boardMember.removeCoach(coach));
    }
    @Test
    @Category({UnitTests.class})
    public void removeCoachTrue4Unit() {
        boardMember.permissions.put(permission.removeCoach,true);
        assertFalse(boardMember.removeCoach(coach));
    }

    @Test
    @Category({UnitTests.class})
    public void addFinancialActionFalse5Unit() {
        boardMember.permissions.put(permission.addFinancial,false);
        assertFalse(boardMember.addFinancialAction("buyPlayer",-2000));
    }

    @Test
    @Category({UnitTests.class})
    public void addFinancialActionTrue6Unit() {
        boardMember.permissions.put(permission.addFinancial,true);
        assertTrue(boardMember.addFinancialAction("buyPlayer",-2000));
    }

    @Test
    @Category({UnitTests.class})
    public void addAssetsFalse7Unit() {
        boardMember.permissions.put(permission.addAsset,false);
        assertFalse(boardMember.addAssets("filed"));
    }
    @Test
    @Category({UnitTests.class})
    public void addAssetsTrue8Unit() {
        boardMember.permissions.put(permission.addAsset,true);
        assertTrue(boardMember.addAssets("filed"));
    }

    @Test
    @Category({UnitTests.class})
    public void updateTeamPageTrue9Unit() {
        boardMember.permissions.put(permission.updateTeamPage,true);
        assertTrue(boardMember.updateTeamPage("New Player is on the Way"));
    }

    @Test
    @Category({UnitTests.class})
    public void updateTeamPageFalse10Unit() {
        boardMember.permissions.put(permission.updateTeamPage,false);
        assertFalse(boardMember.updateTeamPage("New Player is on the Way"));
    }

    @Test
    @Category({IntegrationTests.class})
    public void removeTeam3Integration() throws Exception {
        boardMember.removeTeam(team);
        assertNull(boardMember.team);
    }

}