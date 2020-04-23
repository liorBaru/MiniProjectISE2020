package service;
import domain.Asset.Coach;
import domain.Asset.Fan;
import domain.Asset.Owner;
import domain.Asset.TeamMember;
import DB.System;
import domain.manageUsers.Account;
import domain.manageTeams.Team;
import domain.manageUsers.AccountManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FanControllerTest {

    System system;
    Fan userFan ;
    Owner owner ;
    TeamMember coach;

    public void setUp() throws Exception
    {
        List<Owner> ownerList=new ArrayList<>();
        system =System.getInstance();
        owner=new Owner(new Account("admin","12341234"),"Bill",null,null,null);
        ownerList.add(owner);
        Team team=new Team(ownerList,"M.C");
        owner.setTeam(team);
        coach = new Coach(new Account("admin","12341234"),"boss");
        userFan = new Fan("gal",new Account("galbo","gAlb1234"));
   }



//   public void setUp2() throws Exception {
//
//       AccountManager accountManager = new AccountManager(system);
//       Fan fan = system.createNewFanUser("fan", "fanUser", "FanUser12");
//
//   }

    @Test
    public void followPage() throws Exception
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
    public void followPageNotExsistPage() throws Exception
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
    public void followPageAlreadyFollower() throws Exception
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
    public void unFollowPage() throws Exception
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
    public void unFollowPageFanNotFollow() throws Exception
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
    public void unFollowPageNotExistsPage() throws Exception
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
    public void sendComplaintSuccess()
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


}