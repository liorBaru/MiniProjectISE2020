package domain.service;

import domain.Asset.Fan;
import domain.manageUsers.Guest;
import domain.manageUsers.User;

import java.util.List;

public class FanController extends GuestController
{

    private Fan userFan;

    public FanController(User user)
    {
        if(user.getKind().equals("Fan"))
        {
            this.userFan=(Fan)user;
        }
    }

    public void setUserFan(Fan userFan) {
        this.userFan = userFan;
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

    public boolean followGame(int gameID) throws Exception {
        return userFan.followGame(gameID);
    }


    public boolean unFollowGame(int gameID) throws Exception
    {
        return userFan.unfollowGame(gameID);
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
    public void sendComplaint(String details) throws Exception
    {
        if(details.isEmpty()==false)
        {
            userFan.fillingComplaint(details);
        }
    }


}
