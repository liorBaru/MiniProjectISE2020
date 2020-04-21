package domain;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class User extends Guest
{
    protected String name;
    protected Account account;
    protected PriorityQueue<Notification> notifications;

    @Override
    public boolean equals(Object object)
    {
        User user = (User)object;
        if(user.account.getUserName()==this.account.getUserName())
        {
            return true;
        }
        return false;
    }

    public User ( String name,Account account)
    {
        this.account=account;
        account.setUser(this);
        this.name=name;
        system=System.getInstance();
        notifications=new PriorityQueue<>();
    }

    public abstract void removeUser() throws Exception;

    public String getName() {
        return name;
    }


    public void logout() {};

    public void addNotification(Notification notification)
    {
        if(notification!=null)
        {
            notifications.add(notification);
        }
    }


    public List<String> showPersonalDetails()
    {
        LinkedList<String> userDetails = new LinkedList<>();
        String sName = "Name: " +name;
        userDetails.addFirst(sName);
        String userName = "Username: " +account.getUserName();
        userDetails.addFirst(userName);
        return userDetails;
    }

    public void updateName(String name)
    {
        if(name.isEmpty()==false)
        {
            this.name=name;
        }
    }

    public boolean updatePassword(String oldPassword, String newPassword) throws Exception {
        system.changePassword(oldPassword,newPassword,this);
        return false;
    }



    public PriorityQueue<Notification> readNotification()
    {
        return notifications;
    }




    public Account getAccount(){ return account;}



}
