package main.domain.managePages;

import main.DB.System;
import main.domain.manageEvents.Notification;

import java.util.PriorityQueue;

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
