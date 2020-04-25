package service;
import DB.AcceptanceTests;
import DB.RegressionTests;
import domain.Asset.Coach;
import domain.Asset.Fan;
import domain.Asset.Owner;
import domain.Asset.TeamMember;
import DB.System;
import domain.manageUsers.Account;
import domain.manageTeams.Team;
import domain.manageUsers.AccountManager;
import domain.manageUsers.Guest;
import domain.manageUsers.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class FanControllerTest {

    System system;
    Fan userFan ;
    Owner owner ;
    TeamMember coach;

    @Before
    public void setUp() throws Exception
    {
        List<Owner> ownerList=new ArrayList<>();
        system =System.getInstance();
        owner=new Owner(new Account("admin","12341234"),"Bill",null,null,null);
        ownerList.add(owner);
        Team team=new Team(ownerList,"M.C");
        owner.setTeam(team);
        coach = new Coach(new Account("admin","12341234"),"boss");
        userFan = new Fan("fan",new Account("fanUser","FanUser12"));
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
    public void sendComplaintSuccess8acceptance()
    {
        try
        {
            setUp();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        userFan.fillingComplaint("this is Complaint");
        assertFalse(system.getComplaints().isEmpty());
        userFan.fillingComplaint("");
        assertTrue(system.getComplaints().size()<2);
    }

    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void register9acceptance() throws Exception
    {
        Guest guest = new Guest();
        guest.register("guest","guestUser","guestUser1");
        String message="";
        try
        {
            guest.register("guest","guestUser","guestUser1");
        }

        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(message,"Invalid username, userName already exists please try different username");
    }


    @Test
    @Category({RegressionTests.class, AcceptanceTests.class})
    public void changePasswordSuccess10acceptance() throws Exception
    {
        userFan.updatePassword("FanUser12","FanUser123");
        assertTrue(userFan.getAccount().accountVerification("FanUser123"));
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
}