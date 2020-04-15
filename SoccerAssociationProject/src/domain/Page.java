package domain;

import java.util.LinkedList;
import java.util.List;

public class Page extends Subject
{

    private pageable owner;
    private List<String> messages;
    private String pageName;

    public Page (pageable owner)
    {
        this.owner=owner;
        messages = new LinkedList<>();
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void addDataToPage(String data)
    {
        messages.add(data);
        String details = pageName +" add new notification";
        Notification notification =new Notification(details);
        notifyObservers(notification);
    }



}
