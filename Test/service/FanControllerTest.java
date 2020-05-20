package service;
import main.DB.AcceptanceTests;
import main.DB.RegressionTests;
import main.domain.Asset.Coach;
import main.domain.Asset.Fan;
import main.domain.Asset.Owner;
import main.domain.Asset.TeamMember;
import main.DB.System;
import main.domain.manageUsers.Guest;
import main.domain.manageUsers.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.Assert.*;

public class FanControllerTest {

    /**
    System system;
    Fan userFan ;
    Owner owner ;
    TeamMember coach;

    @Before
    public void setUp() throws Exception
    {
        try
        {
            system =System.getInstance();
            owner=system.createNewOwnerUser("admin","1234123gB","BillBam");
            system.createTeam(owner.getAccount().getUserName(),"team");
            coach= system.createNewCoachUser("boss","1234gaGG","adminR","training");
            userFan=system.createNewFanUser("boss","userNewr","gghsU7768");
        }
        catch (Exception e)
        {
            owner=(Owner) system.login("BillBam","1234123gB");
            coach=(Coach)system.login("adminR","1234gaGG");
            userFan=(Fan)system.login("userNewr","gghsU7768");
        }

   }


    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void loginFailure1acceptance()
    {
        String message="";
        try
        {
            setUp();
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        try
        {
            User user =userFan.login("fffdsd","FanUser12");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message," wrong userName or password, please try again");
    }


    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void followPage2acceptance() throws Exception
    {
        try
        {
            setUp();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        userFan.followPage(coach.getPage().getPageID());
        assertTrue(userFan.showPages().contains(coach.getPage()));
        String message="";
        try
        {
            assertTrue(coach.getPage().addFollower(userFan));
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(userFan.getName()+" is already following",message);
        userFan.unfollowPage(coach.getPage().getPageID());
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void followPageNotExistPage3acceptance() throws Exception
    {
        try
        {
            setUp();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String message="";
        try
        {
            userFan.followPage(-1);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"page dont exists, please try again");
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void followPageAlreadyFollower4acceptance() throws Exception
    {
        try
        {
            setUp();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String message="";
        try
        {
            userFan.followPage(coach.getPage().getPageID());
            userFan.followPage(coach.getPage().getPageID());
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(userFan.getName()+" is already following",message);
        userFan.unfollowPage(coach.getPage().getPageID());
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void unFollowPage5acceptance() throws Exception
    {
        try
        {
            setUp();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        userFan.followPage(coach.getPage().getPageID());
        userFan.unfollowPage(coach.getPage().getPageID());
        assertFalse(userFan.showPages().contains(coach.getPage().getPageID()));
        String message ="";
        try {
            userFan.unfollowPage(coach.getPage().getPageID());
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(userFan.getName()+" is not following the chosen page",message);
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void unFollowPageFanNotFollow6acceptance() throws Exception
    {
        try
        {
            setUp();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        String message ="";
        try
        {
            userFan.unfollowPage(coach.getPage().getPageID());
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(userFan.getName() +" is not following the chosen page",message);
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void unFollowPageNotExistsPage7acceptance() throws Exception
    {
        try
        {
            setUp();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        String message ="";
        try
        {
            userFan.unfollowPage(coach.getPage().getPageID());
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(userFan.getName() +" is not following the chosen page",message);
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void sendComplaintSuccess8acceptance() throws SQLException {
        try
        {
            setUp();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        userFan.fillingComplaint("this is Complaint");
        assertTrue(system.getNewComplaints().isEmpty()==false);
        userFan.fillingComplaint("");
        assertTrue(system.getNewComplaints().size()>=1);
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void register9acceptance() throws Exception
    {
        Guest guest = new Guest();
        guest.register("guest","guestUser3","guestUser1");
        String message="";
        try
        {
            guest.register("guest","guestUser3","guestUser1");
        }

        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("Invalid username, userName already exists please try different username",message);
    }


    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void changePasswordSuccess10acceptance() throws Exception
    {
        userFan.updatePassword("gghsU7768","FanUser123");
        assertTrue(userFan.getAccount().accountVerification("FanUser123"));
        userFan.updatePassword("FanUser123","gghsU7768");
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void loginSuccess11acceptance() throws Exception
    {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(10000);

        User user= system.createNewFanUser("fan",rand_int1+"fanUser","FanUser12");
        User user2 =user.login(rand_int1+"fanUser","FanUser12");
        assertTrue(user.equals(user2));

    }
    */
}