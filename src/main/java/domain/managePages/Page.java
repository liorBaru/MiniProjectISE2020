package domain.managePages;


import DB.System;
import domain.manageEvents.Notification;

import java.util.PriorityQueue;
import java.util.Date;

public class Page extends Subject
{

    private pageable owner;
    private PriorityQueue<String> messages;
    private String pageName;
    private int pageID;
    private static int ID;
    private System system;

    public Page (pageable owner)
    {
        this.owner=owner;
        messages = new PriorityQueue<>();
        pageID=ID;
        ID++;
        system=System.getInstance();
        system.addPage(this);
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName)
    {
        this.pageName = pageName;
    }

    /**
     * gal
     * add new message to the page and send new notification to all followers
     * @param data
     */
    public void addDataToPage(String data)
    {
        messages.add(data);
        String details = pageName +" add new notification";
        Notification notification =new Notification(details);
        notifyObservers(notification);
    }

    public int getPageID()
    {
        return pageID;
    }


}
