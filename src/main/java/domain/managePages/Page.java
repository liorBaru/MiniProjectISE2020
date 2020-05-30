package domain.managePages;

import DataAccess.PageFollowersDaoSql;
import DataAccess.PageMessagesDaoSql;
import DataAccess.PagesDaoSql;
import DataAccess.System;
import domain.Asset.TeamMember;
import domain.manageEvents.Notification;
import domain.manageTeams.Team;

import java.awt.print.Pageable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

public class Page extends Subject
{

    private static PagesDaoSql pagesDaoSql=PagesDaoSql.getInstance();
    private static PageMessagesDaoSql pageMessagesDaoSql=PageMessagesDaoSql.getInstance();
    private static PageFollowersDaoSql pageFollowersDaoSql=PageFollowersDaoSql.getInstance();

    private pageable owner;
    private PriorityQueue<String> messages;
    private String pageName;
    private int pageID;
    private static int ID=15;

    public Page (pageable owner) throws SQLException {
        this.owner=owner;
        messages = new PriorityQueue<>();
        pageID=ID;
        ID++;
        String [] key ={String.valueOf(pageID),owner.getPageOwnerName(),""};
        pagesDaoSql.save(key);
    }

    public Page(int pageID, String pageName)
    {
        this.pageID=pageID;
        this.pageName=pageName;
    }

    public void deletePage() throws SQLException
    {
        String [] key ={String.valueOf(this.pageID)};
        pagesDaoSql.delete(key);
    }
    public void setOwner(pageable owner)
    {
        this.owner=owner;
    }

    public static Page createPageFromDB(String pageID) throws Exception {
        String[] key ={pageID};
        List<String[]> pages=pagesDaoSql.get(key);
        if(pages==null || pages.isEmpty())
        {
            throw new Exception("Page not found");
        }
        String[] pageDetails=pages.get(0);
        String pageName="";
        if(pageDetails[1]!=null)
        {
            pageName=pageDetails[1];
        }
        Page page = new Page(Integer.valueOf(pageID),pageName);
        return page;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) throws SQLException {
        String[] params={String.valueOf(pageID),pageName};
        pagesDaoSql.update(params);
    }

    /**
     * gal
     * add new message to the page and send new notification to all followers
     * @param data
     */
    public void addDataToPage(String data) throws Exception
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String sDate=dateFormat.format(date);
        String [] key={String.valueOf(pageID),data,sDate};
        pageMessagesDaoSql.save(key);
        String details = pageName +" add new notification";
        Notification notification =new Notification(details);
        notifyObservers(notification);
    }
    @Override
    public void notifyObservers(Notification notification)
    {
        String[] key={"pageID",String.valueOf(pageID)};
        for (String[] fan:pageFollowersDaoSql.get(key))
        {
            System system =System.getInstance();
            try {
                //system.sendNotification(fan[0],notification);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
    public int getPageID()
    {
        return pageID;
    }


}
