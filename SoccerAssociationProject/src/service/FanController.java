package service;

import domain.Fan;

import java.util.List;

public class FanController
{
    private Fan userFan;
    public void FanController(Fan fan)
    {
        userFan=fan;
    }

    /**
     * gal
     * read user notifications
     */
    public void readNotification()
    {
       userFan.readNotification();
    }
    /**
     * gal
     * show user personal Details
     * @return
     */
    public List<String> showPersonalDetails()
    {
        return userFan.showPersonalDetails();
    }

    /**
     * gal
     * follow new page
     * @param pageID
     * @return
     * @throws Exception
     */
    public boolean followPage(int pageID) throws Exception {
        return userFan.followPage(pageID);
    }

    /**
     * gal
     * un follow page
     * @param pageID
     * @return
     * @throws Exception
     */
    public boolean unFollowPage(int pageID) throws Exception
    {
         return userFan.unfollowPage(pageID);
    }



    /**
     * gal
     * send complaint to system manager
     * @param details
     */
    public void sendComplaint(String details)
    {
        if(details.isEmpty()==false)
        {
            userFan.fillingComplaint(details);
        }
    }


}
