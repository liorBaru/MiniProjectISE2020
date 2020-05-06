package main.domain.manageUsers;
import main.DB.System;
import main.domain.manageEvents.Notification;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class User extends Guest
{
    protected String name;
    protected Account account;
    protected PriorityQueue<Notification> notifications;

    public User ( String name,Account account)
    {
        system=System.getInstance();
        this.account=account;
        account.setUser(this);
        this.name=name;
        notifications=new PriorityQueue<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object)
    {
        if( object instanceof  User)
        {
            User user = (User)object;
            if(user.account.getUserName().equals(this.account.getUserName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * gal
     * add notification to user
     * @param notification
     */
    public void addNotification(Notification notification)
    {
        if(notification!=null)
        {
            notifications.add(notification);
        }
    }

    /**
     *  show user personal detailes
     * @return
     */
    public List<String> showPersonalDetails()
    {
        LinkedList<String> userDetails = new LinkedList<>();
        String sName = "Name: " +name;
        userDetails.addFirst(sName);
        String userName = "Username: " +account.getUserName();
        userDetails.addFirst(userName);
        return userDetails;
    }
    /**
     * gal
     * change user password
     * @param oldPassword
     * @param newPassword
     * @throws Exception
     */
    public void updatePassword(String oldPassword, String newPassword) throws Exception
    {
        system.changePassword(oldPassword,newPassword,this);
    }

    /**
     * gal,
     * read notification
     * @return
     */
    public PriorityQueue<Notification> readNotification()
    {
        return notifications;
    }

    public abstract void removeUser() throws Exception;

    public Account getAccount(){ return account;}



}
