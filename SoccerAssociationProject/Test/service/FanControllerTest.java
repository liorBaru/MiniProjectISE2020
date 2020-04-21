package service;
import domain.*;
import domain.System;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FanControllerTest {

    System system;
    Fan userFan ;
    Owner owner ;
    Page page;
    Coach coach;

    public void setUp() throws Exception
    {
        List<Owner> ownerList=new ArrayList<>();
        system =System.getInstance();
        owner=new Owner(new Account("admin","12341234"),"Bill",null,null,null,null);
        ownerList.add(owner);
        Team team=new Team(ownerList,"M.C");
        owner.setTeam(team);
        coach = new Coach(new Account("admin","12341234"),"boss",team,null,1500,"training");
        page = new Page(coach);
        system.addPage(page);
        userFan = new Fan("gal",new Account("galbo","gAlb1234"));
   }

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
        userFan.followPage(1);
        assertTrue(userFan.showPages().contains(page));
        String message="";
        try
        {
            assertTrue(page.addFollower(userFan));
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(userFan.getName()+" is already following",message);
        userFan.unfollowPage(1);
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
            userFan.followPage(1);
            userFan.followPage(1);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals(userFan.getName()+" is already following",message);
        userFan.unfollowPage(1);
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
        userFan.followPage(1);
        userFan.unfollowPage(1);
        assertFalse(userFan.showPages().contains(page));
        String message ="";
        try {
            userFan.unfollowPage(1);
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
            userFan.unfollowPage(0);
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
            userFan.unfollowPage(1);
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