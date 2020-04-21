package service;

import domain.*;

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
        owner=new Owner(new Account("admin","12341234"),"Bill",null,null,null);
        ownerList.add(owner);
        team=new Team(ownerList,"M.C");
        owner.setTeam(team);
        coach = new Coach(new Account("galcoach","cDasj3454"),"coach",team,null,12550,"coach");
        fan = new Fan("fan",new Account("gggggg","gfgdagad3"));
    }
    /*
    @org.junit.Test
   public void addAssets() {
        Field filed=new Field("Tady");
        BoardMember bm=new
        BoardManagerController bmc = new BoardManagerController();
        bmc.addAssets(filed,owner);
        assertTrue(owner.getTeam().getAssetsOfTeam().contains(filed));
    }
*/

    /*
    @org.junit.Test
    public void removeAssets() {
        Field filed=new Field("Tady");
        BoardManagerController bmc = new BoardManagerController();
        assertFalse(owner.getTeam().getAssetsOfTeam().contains(filed));
    }
*/
    @org.junit.Test
    public void testRemoveAssets() {
    }


    public void uploadDataToPage() throws Exception {
        fan.followPage(0);
        coach.uploadDataToPage("checkData");
        for (Notification notification:fan.readNotification())
        {
            assertEquals(notification.getDetails(),"checkData");
        }
        fan.unfollowPage(0);
    }

    public void uploadEmptyDataToPage() throws Exception
    {
        fan.followPage(0);
        coach.uploadDataToPage("");
        assertTrue(fan.readNotification().isEmpty());
        fan.unfollowPage(0);
    }
}