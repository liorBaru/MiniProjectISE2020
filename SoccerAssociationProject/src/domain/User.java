package domain;
import java.util.PriorityQueue;

public abstract class User extends Guest
{
    protected String name;
    protected Account account;
    protected PriorityQueue<Notification> notifications;
    protected System system;


    public User (Account account, String name)
    {
        this.account=account;
        this.name=name;
        system=system.getInstance();
        notifications=new PriorityQueue<>();
    }



    public void logout(){};
    public String showPersonalDetails(){return this.name;}
    public  void updateDetailes(){}
    public void addNotification(Notification notification){}
    public PriorityQueue<Notification> readNotification(){return null;}







}
