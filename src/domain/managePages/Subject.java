package domain.managePages;

import domain.manageUsers.User;
import domain.manageEvents.Notification;

import java.util.LinkedList;
import java.util.List;

public abstract class Subject
{

    protected List<User> followers;

    public Subject()
    {
        followers=new LinkedList<>();
    }





    /**
     * gal
     * remove follower from followers list if exists
     * @param user
     * @return
     * @throws Exception
     */
    public boolean removeFollower(User user) throws Exception {
        if(user!=null && followers.contains(user))
        {
            followers.remove(user);
            return true;
        }
        throw new Exception(user.getName()+" is not  follower.");
    }

    /**
     * gal
     * send notification to all followers
     * @param notification
     */

    public abstract void notifyObservers(Notification notification);

}
