package domain;
import java.util.PriorityQueue;

public abstract class User extends Guest
{
    protected String name;
    protected Account account;
    protected PriorityQueue<Notification> notifications;

    public User ( String name,Account account)
    {
        this.account=account;
        account.setUser(this);
        this.name=name;
        system=System.getInstance();
        notifications=new PriorityQueue<>();
    }

    public abstract void removeUser();
    public void logout() {};
    public String showPersonalDetails(){return this.name;}
    public void updateDetailes(){}

    public void addNotification(Notification notification)
    {
        if(notification!=null)
        {
            notifications.add(notification);
        }
    }

    public String getName() {
        return name;
    }

    public PriorityQueue<Notification> readNotification()
    {
        return notifications;
    }


}//class
