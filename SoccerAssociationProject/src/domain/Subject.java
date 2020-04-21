package domain;

import java.util.LinkedList;
import java.util.List;

public abstract class Subject
{

    protected List<User> followers;

    public Subject()
    {
        followers=new LinkedList<>();
    }

    public boolean addFollower(User user)throws Exception
    {
        if(user!=null && followers.contains(user)==false)
        {
            followers.add(user);
            return true;
        }
        throw new Exception(user.getName()+" is already following");
    }

    public boolean removeFollower(User user) throws Exception {
        if(user!=null && followers.contains(user))
        {
            followers.remove(user);
            return true;
        }
        throw new Exception(user.getName()+" is not  follower.");
    }

    public void notifyObservers(Notification notification)
    {
        if(notification!=null)
        {
            for (User follower:followers)
            {
                follower.addNotification(notification);
            }
        }
    }
}
