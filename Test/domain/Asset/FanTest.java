package domain.Asset;


import main.DB.IntegrationTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;
public class FanTest
{

    public void createFanFromDBGood()
    {

    }

    public void createFanFromDBFailure()
    {

    }

    public void unfollowPageGood(int pageID)
    {

    }

    public void unfollowPageNotGoodPage()
    {

    }

    public void unfollowPageNotGoodUser()
    {

    }

    @Test
    @Category({IntegrationTests.class})
    public void followPageTrue1Integration() throws Exception
    {

    }

    @Test
    @Category({IntegrationTests.class})
    public void followPageFalse2Integration() throws Exception {

    }

    @Test(expected =Exception.class)
    @Category({IntegrationTests.class})
    public void followPageDontExists3Integration() throws Exception {
    }

    public void showPagesGood()
    {

    }
    public void showPagesNotGood()
    {

    }

    public void fillingComplaintGood(String deatails)
    {

    }
    public void fillingComplaintFailure(String deatails)
    {

    }

    public boolean removeUser()
    {
        return true;
    }



}