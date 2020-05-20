package service;
import main.DB.AcceptanceTests;
import main.DB.RegressionTests;
import main.DB.System;
import main.domain.Asset.Coach;
import main.domain.Asset.Fan;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

public class TeamMemberControllerTest
{

/**
    System system;
    Coach coach;
    Fan fan;
    @Before
    public void setUp()
    {
        system=System.getInstance();
        try
        {
            coach= system.createNewCoachUser("boss","1234gapG","adminu","training");
            fan=system.createNewFanUser("boss","userNuuuy","gghsU7768");
        }
        catch (Exception e)
        {


        }
        try
        {
           coach = (Coach) system.login("adminu", "1234gapG");
           fan = (Fan) system.login("userNuuuy", "gghsU7768");
        }
        catch(Exception e)
        {

       }
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void uploadDataIntegration1()
    {
        int size =fan.readNotification().size();
        try
        {
            fan.followPage(coach.getPage().getPageID());
            coach.uploadDataToPage("test");
            assertTrue(fan.readNotification().size()>size);
            fan.unfollowPage(coach.getPage().getPageID());
        }
        catch (Exception e)
        {

        }
    }
    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void uploadDataEmptyIntegration2()
    {
        int size =fan.readNotification().size();
        try
        {
            fan.followPage(coach.getPage().getPageID());
            coach.uploadDataToPage("");
            assertTrue(fan.readNotification().size()==size);
            fan.unfollowPage(coach.getPage().getPageID());
        }
        catch (Exception e)
        {

        }
    }

**/
}