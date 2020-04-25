package domain.Asset;

import DB.IntegrationTests;
import DB.UnitTests;
import domain.manageEvents.Notification;
import domain.managePages.Page;
import domain.manageTeams.Team;
import domain.manageUsers.Account;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class FanTest {
    Fan fan=new Fan("matan",new Account("matang","Matan1234"));
    List<Owner> ownerList=new ArrayList<>();
    Team team=new Team(ownerList,"mc");
    Page page=team.getPage();

    @Test
    @Category({IntegrationTests.class})
    public void followPageTrue1Integration() throws Exception {
        fan.followPage(page.getPageID());
        Notification n1=new Notification("check",new Date());
        page.notifyObservers(n1);
        assertTrue(fan.readNotification().contains(n1));
    }

    @Test
    @Category({IntegrationTests.class})
    public void followPageFalse2Integration() throws Exception {
        Page page2=new Page(team);
        fan.followPage(page2.getPageID());
        Notification n1=new Notification("check",new Date());
        page.notifyObservers(n1);
        assertFalse(fan.readNotification().contains(n1));
    }

    @Test(expected =Exception.class)
    @Category({IntegrationTests.class})
    public void followPageDontExists3Integration() throws Exception {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(10000);
        int newPageId= rand_int1+151;
         fan.followPage(newPageId);
    }

    @Category({IntegrationTests.class})
    @Test(expected =Exception.class)
    public void unfollowPagePageNotExists4Integration() throws Exception {
        fan.unfollowPage(150);
    }

    @Category({IntegrationTests.class})
    @Test
    public void unfollowPagePageFollow5Integration() throws Exception {
        fan.followPage(0);
        fan.unfollowPage(0);
        Notification n1=new Notification("check",new Date());
        page.notifyObservers(n1);
        assertFalse(fan.readNotification().contains(n1));
    }

}