package domain.Asset;

import DB.IntegrationTests;
import domain.manageEvents.Notification;
import domain.managePages.Page;
import domain.manageTeams.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import DB.System;

import java.util.Random;

import static org.junit.Assert.*;

public class FanTest
{
    private static boolean flag=true;
    Fan fan;
    Team team;
    Page page;
    System system;

    @Before
    public void setUp()
    {
        system=System.getInstance();
        if(flag)
        {
            try
            {
                system.createNewOwnerUser("name","ownerP1234","usernameOwner");
                system.createNewFanUser("matan","matang","Matan1234");
                system.createTeam("usernameOwner","mc");
            }
            catch (Exception e)
            {

            }
            flag=false;
        }
        team=system.getTeam("mc");
        page=team.getPage();
        try
        {
            fan=(Fan) system.login("matang","Matan1234");
        }
        catch (Exception e)
        {

        }

    }

    public FanTest() throws Exception {
    }

    @Test
    @Category({IntegrationTests.class})
    public void followPageTrue1Integration() throws Exception {
        fan.followPage(page.getPageID());
        Notification n1=new Notification("check");
        page.notifyObservers(n1);
        assertTrue(fan.readNotification().contains(n1));
    }

    @Test
    @Category({IntegrationTests.class})
    public void followPageFalse2Integration() throws Exception {
        Page page2=new Page(team);
        fan.followPage(page2.getPageID());
        Notification n1=new Notification("check");
        page.notifyObservers(n1);
        assertTrue(fan.readNotification().contains(n1));
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

        Coach coach=system.createNewCoachUser("coach","coachGal1","coachUserName","training");
        fan.followPage(coach.getPage().getPageID());
        fan.unfollowPage(coach.getPage().getPageID());
        Notification n1=new Notification("check");
        coach.getPage().notifyObservers(n1);
        assertTrue(fan.readNotification().contains(n1)==false);
    }

}