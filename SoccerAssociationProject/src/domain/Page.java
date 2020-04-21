package domain;


import java.util.PriorityQueue;

public class Page extends Subject
{

    private pageable owner;
    private PriorityQueue<String> messages;
    private String pageName;
    private int pageID;
    private static int ID;

    public Page (pageable owner, String pageName)
    {
        this.owner=owner;
        messages = new PriorityQueue<>();
        pageID=ID;
        ID++;
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
