package domain.manageUsers;
import DataAccess.NotificationsDaoSql;
import DataAccess.System;
import DataAccess.UsersDaoSql;
import domain.manageEvents.Notification;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class User extends Guest
{
    protected String name;
    protected Account account;
    protected String kind;

    private static UsersDaoSql usersDaoSql;
    private static NotificationsDaoSql notificationsDaoSql;

    public User (String name,Account account)
    {
        system=System.getInstance();
        this.account=account;
        account.setUser(this);
        this.name=name;
    }

    protected User() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) throws SQLException {
        this.name=name;
        update();
    }
    protected abstract void update() throws SQLException;
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
    public void addNotification(Notification notification) throws SQLException {
        if(notification!=null)
        {
            String [] param = new String [3];
            param[0]=this.getAccount().getUserName();
            param[1]=notification.getDetails();
            param[2]=notification.getDate();
            notificationsDaoSql.save(param);
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
        PriorityQueue<Notification> notifications = new PriorityQueue<>();
        String [] key ={this.account.getUserName()};
        for (String[] notificationString:notificationsDaoSql.get(key))
        {
            Notification notification = new Notification(notificationString);
            notifications.add(notification);
        }
        return notifications;
    }

    public abstract boolean removeUser() throws Exception;

    public Account getAccount(){ return account;}

    public String getKind() {return kind; }




}
