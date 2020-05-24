package domain.Asset;


import DataAccess.ComplaintDaoSql;
import DataAccess.IntegrationTests;
import DataAccess.UnitTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.TreeMap;

import static org.junit.Assert.*;
public class FanTest
{
    private Fan fan;

    @Before
    public void setUp()
    {
        try
        {
            String [] key ={"fanTest"};
            fan=Fan.createFanFromDB(key);
        }
        catch (Exception e)
        {

        }
    }


    @Test
    @Category({IntegrationTests.class})
    public void followPageAlreadyFollower1Integration()
    {
        String message="";
        TreeMap<Integer,String> pages =fan.showPages();
        int id=pages.firstKey();
        try
        {
            fan.followPage(id);
            fan.followPage(id);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        try
        {
            fan.unfollowPage(id);
            assertEquals("wrong parameters",message);
        }
        catch (Exception e)
        {

        }

    }

    @Test
    @Category({IntegrationTests.class})
    public void followPage2Integration()
    {
        try
        {
            TreeMap<Integer,String> pages =fan.showPages();
            int id=pages.firstKey();
            fan.followPage(id);
            assertTrue(fan.getFollwingPages().containsKey(id));
            fan.unfollowPage(id);
            assertTrue(fan.getFollwingPages().isEmpty());
        }
        catch (Exception e)
        {

        }
    }



    @Test
    @Category({IntegrationTests.class})
    public void followPageDontExists3Integration()
    {
        int id=-5;
        String message="";
        try
        {
            fan.followPage(id);
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("object not found",message);
    }

    @Test
    @Category(UnitTests.class)
    public void fillingComplaintGood()
    {
        try
        {
            fan.fillingComplaint("complaint");
            ComplaintDaoSql complaintDaoSql=ComplaintDaoSql.getInstance();
            boolean flag=false;
            for (String [] complaint:complaintDaoSql.getAll())
            {
                if(complaint[3].equals("complaint"))
                {
                    flag=true;
                }
            }
            assertTrue(flag);
        }
        catch (Exception e)
        {

        }
    }

    @Test
    @Category(UnitTests.class)
    public void fillingComplaintFailure()
    {
        String message="";
        try
        {
            fan.fillingComplaint("");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        assertEquals("cant write empty complaint",message);
    }




}