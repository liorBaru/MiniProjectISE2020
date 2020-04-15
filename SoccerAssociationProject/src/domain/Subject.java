package domain;

import java.util.List;

public abstract class Subject
{

    protected List<User> followers;

    public boolean addFollwer(User user)
    {
        if(user!=null && followers.contains(user)==false)
        {
            followers.add(user);
            return true;
        }
        return false;
    }
    public boolean removeFollwer(User user)
    {
        if(user!=null && followers.contains(user))
        {
            followers.remove(user);
            return true;
        }
        return false;
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
